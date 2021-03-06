package ca.klapstein.baudit.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.models.DataModel;
import ca.klapstein.baudit.presenters.PatientHomePresenter;
import ca.klapstein.baudit.views.PatientHomeView;
import ca.klapstein.baudit.views.ProblemRowView;

import static ca.klapstein.baudit.activities.MapRecordsActivity.MAP_RECORDS_MODE;
import static ca.klapstein.baudit.activities.MapRecordsActivity.MAP_RECORDS_USERNAME;
import static ca.klapstein.baudit.activities.ProblemActivity.PROBLEM_MODE_EXTRA;
import static ca.klapstein.baudit.activities.ProblemActivity.PROBLEM_POSITION_EXTRA;
import static ca.klapstein.baudit.activities.ViewAccountActivity.VIEW_ACCOUNT_USERNAME_EXTRA;

/**
 * Activity for listing {@code Problem}s.
 *
 * @see ca.klapstein.baudit.data.Problem
 */
public class PatientHomeActivity extends AppCompatActivity implements PatientHomeView {

    private PatientHomePresenter presenter;
    private ProblemListAdapter adapter;
    private DrawerLayout drawerLayout;
    private TextView problemCountText;
    private TextView navHeaderUsername;
    private TextView navHeaderEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        Toolbar toolbar = findViewById(R.id.patient_home_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        getSupportActionBar().setTitle(R.string.home);

        presenter = new PatientHomePresenter(this, getApplicationContext());

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        View navHeaderView = navigationView.inflateHeaderView(R.layout.drawer_header);
        navHeaderUsername = navHeaderView.findViewById(R.id.nav_header_username);
        navHeaderEmail = navHeaderView.findViewById(R.id.nav_header_email);

        navHeaderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHomeActivity.this, ViewAccountActivity.class);
                intent.putExtra(VIEW_ACCOUNT_USERNAME_EXTRA, presenter.getUsername());
                startActivity(intent);
                drawerLayout.closeDrawers();
            }
        });

        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    drawerLayout.closeDrawers();

                    switch (menuItem.getItemId()) {
                        case (R.id.nav_edit_account):
                            startActivity(new Intent(
                                getApplicationContext(),
                                EditPatientAccountActivity.class
                            ));
                            return true;
                        case (R.id.nav_display_qr_code):
                            startActivity(new Intent(
                                getApplicationContext(),
                                DisplayQRCodeActivity.class
                            ));
                            return true;
                        case (R.id.nav_unpair):
                            new DataModel(getApplicationContext()).clearOfflineLoginAccount();
                            startActivity(new Intent(
                                    getApplicationContext(),
                                    StartActivity.class
                            ));
                            return true;
                        default:
                            return true;
                    }
                }
            });

        RecyclerView problemRecyclerView = findViewById(R.id.problem_list);
        adapter = new ProblemListAdapter();
        problemRecyclerView.setAdapter(adapter);
        problemRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        problemCountText = findViewById(R.id.problem_count);

        FloatingActionButton fab = findViewById(R.id.patient_home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProblemActivity.class);
                intent.putExtra(PROBLEM_POSITION_EXTRA, -1);
                intent.putExtra(PROBLEM_MODE_EXTRA, "edit");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.patient_home_menu, menu);

        SearchView problemSearchView =
            (SearchView) menu.findItem(R.id.patient_home_search).getActionView();

        problemSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                updateList();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                updateList();
                if (newText.length() == 0) {
                    presenter.viewStarted();
                }
                return true;
            }
        });

        problemSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                // reset the presenters list of problems, thus, repopulating the list
                presenter.viewStarted();
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.patient_home_view_map:
                Intent intent = new Intent(getApplicationContext(), MapRecordsActivity.class);
                intent.putExtra(MAP_RECORDS_MODE, "all");
                intent.putExtra(MAP_RECORDS_USERNAME, presenter.getUsername());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void updateUsernameDisplay(String username) {
        navHeaderUsername.setText(username);
    }

    @Override
    public void updateEmailDisplay(String email) {
        navHeaderEmail.setText(email);
    }

    @Override
    public void updateAccountLoadError() {
        Toast.makeText(
            this,
            getResources().getString(R.string.patient_account_load_failure),
            Toast.LENGTH_LONG
        ).show();
    }

    @Override
    public void updateDeleteProblemError() {
        Toast.makeText(this, getResources().getString(R.string.delete_problem_failure), Toast.LENGTH_LONG).show();

    }

    @Override
    public void updateProblemNumber(int problemNumber) {
        problemCountText.setText(String.format(
                getResources().getString(R.string.problem_count),
                problemNumber
        ));
    }

    private class ProblemListAdapter extends RecyclerView.Adapter<ProblemViewHolder>
        implements Filterable {

        private final ProblemFilter problemFilter = new ProblemFilter();

        @Override @NonNull
        public ProblemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            CardView v = (CardView) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_problem, viewGroup, false);
            return new ProblemViewHolder(v); // Wrap it in a ViewHolder.
        }

        @Override
        public void onBindViewHolder(@NonNull final ProblemViewHolder viewHolder, int i) {
            presenter.onBindProblemRowViewAtPosition(viewHolder, i);

            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ProblemActivity.class);
                    intent.putExtra(PROBLEM_POSITION_EXTRA, presenter.getTrueProblemIndex(viewHolder.getAdapterPosition()));
                    intent.putExtra(PROBLEM_MODE_EXTRA, "view");
                    startActivity(intent);
                }
            });

            viewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu menu = new PopupMenu(getApplicationContext(), viewHolder.cardView);
                    menu.inflate(R.menu.problem_popup_menu);
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Intent intent = new Intent(
                                getApplicationContext(),
                                ProblemActivity.class
                            );
                            switch (item.getItemId()) {
                                case R.id.edit_problem:
                                    intent.putExtra(
                                        PROBLEM_POSITION_EXTRA,
                                            presenter.getTrueProblemIndex(viewHolder.getAdapterPosition())
                                    );
                                    intent.putExtra(PROBLEM_MODE_EXTRA, "edit");
                                    startActivity(intent);
                                    break;
                                case R.id.delete_problem:
                                    new AlertDialog.Builder(PatientHomeActivity.this)
                                        .setTitle(R.string.delete_problem_question)
                                        .setCancelable(true)
                                        .setNegativeButton(R.string.cancel, null)
                                        .setPositiveButton(R.string.delete,
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface di, int i) {
                                                    presenter.deleteProblemClicked(
                                                            viewHolder.getAdapterPosition()
                                                    );
                                                }
                                            })
                                        .show();
                                    break;
                                default:
                                    break;
                            }
                            return false;
                        }
                    });
                    menu.show();
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return presenter.getProblemCount();
        }

        @Override
        public Filter getFilter() {
            return problemFilter;
        }

        private class ProblemFilter extends Filter {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                presenter.filterProblemsByKeyWords(constraint);
                results.count = presenter.getProblemCount();
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notifyDataSetChanged();
            }
        }
    }

    private class ProblemViewHolder extends RecyclerView.ViewHolder implements ProblemRowView {

        private CardView cardView;
        private TextView titleView;
        private TextView dateView;
        private TextView descriptionView;

        private ProblemViewHolder(CardView card) {
            super(card);
            cardView = card;
            titleView = card.findViewById(R.id.card_problem_title);
            dateView = card.findViewById(R.id.card_problem_date);
            descriptionView = card.findViewById(R.id.card_problem_description);
        }

        @Override
        public void onStart() {
            // Do nothing.
        }

        @Override
        public void updateProblemTitleText(String title) {
            titleView.setText(title);
        }

        @Override
        public void updateProblemDateText(String date) {
            dateView.setText(date);
        }

        @Override
        public void updateProblemDescriptionText(String description) {
            descriptionView.setText(description);
        }
    }
}
