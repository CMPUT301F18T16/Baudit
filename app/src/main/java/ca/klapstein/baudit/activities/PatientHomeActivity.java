package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.presenters.PatientHomePresenter;
import ca.klapstein.baudit.views.HomeView;
import ca.klapstein.baudit.views.ProblemRowView;

/**
 * Activity for listing {@code Problem}s.
 *
 * @see ca.klapstein.baudit.data.Problem
 */
public class PatientHomeActivity extends AppCompatActivity implements HomeView {

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
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setTitle(R.string.home);

        presenter = new PatientHomePresenter(this, getApplicationContext());

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        View navHeaderView = navigationView.inflateHeaderView(R.layout.drawer_header);
        navHeaderUsername = navHeaderView.findViewById(R.id.nav_header_username);
        navHeaderEmail = navHeaderView.findViewById(R.id.nav_header_email);

        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    drawerLayout.closeDrawers();

                    switch (menuItem.getItemId()) {
                        case (R.id.nav_edit_account):
                            startActivity(new Intent(
                                PatientHomeActivity.this,
                                EditAccountActivity.class
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
        updateProblemCountText();

        FloatingActionButton fab = findViewById(R.id.patient_home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        PatientHomeActivity.this,
                        ProblemActivity.class
                );
                intent.putExtra("problemId", -1);
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
        updateProblemCountText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.patient_home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.patient_home_view_map:
                Intent intent = new Intent(
                    PatientHomeActivity.this,
                    MapAllProblemsActivity.class
                );
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateProblemCountText() {
        problemCountText.setText(String.format(
            getResources().getString(R.string.problem_count),
            presenter.getProblemCount()
        ));
    }

    @Override
    public void updateUsernameDisplay(String username) {
        navHeaderUsername.setText(username);
    }

    @Override
    public void updateEmailDisplay(String email) {
        navHeaderEmail.setText(email);
    }


    private class ProblemListAdapter extends RecyclerView.Adapter<ProblemViewHolder> {

        @Override @NonNull
        public ProblemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            CardView v = (CardView) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_problem, viewGroup, false);
            return new ProblemViewHolder(v); //Wrap it in a ViewHolder.
        }

        @Override
        public void onBindViewHolder(@NonNull final ProblemViewHolder viewHolder, int i) {
            Problem problem = presenter.getProblemAt(i);
            viewHolder.setProblemTitleText(problem.getTitle());
            viewHolder.setProblemDateText(problem.getTimeStamp());
            viewHolder.setProblemDescriptionText(problem.getDescription());
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(
                        PatientHomeActivity.this,
                        ProblemActivity.class
                    );
                    intent.putExtra("problemId", viewHolder.getAdapterPosition()); // Test ID
                    // TODO: Need a way to get the problem's ID to add to the intent
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return presenter.getProblemCount();
        }
    }

    private class ProblemViewHolder extends RecyclerView.ViewHolder implements ProblemRowView {

        private CardView cardView;
        private ImageView imageView;
        private TextView titleView;
        private TextView dateView;
        private TextView descriptionView;

        private ProblemViewHolder(CardView card) {
            super(card);
            cardView = card;
            imageView = card.findViewById(R.id.problem_card_image);
            titleView = card.findViewById(R.id.problem_card_title);
            dateView = card.findViewById(R.id.problem_card_date);
            descriptionView = card.findViewById(R.id.problem_card_description);
        }

        @Override
        public void onStart() {
            // Do nothing.
        }

        @Override
        public void setProblemImage(Bitmap bmp) {
            imageView.setImageBitmap(bmp);
        }

        @Override
        public void setProblemTitleText(String title) {
            titleView.setText(title);
        }

        @Override
        public void setProblemDateText(String date) {
            dateView.setText(date);
        }

        @Override
        public void setProblemDescriptionText(String description) {
            descriptionView.setText(description);
        }
    }
}
