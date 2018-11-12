package ca.klapstein.baudit.activities;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.presenters.PatientHomePresenter;
import ca.klapstein.baudit.views.ProblemListView;
import ca.klapstein.baudit.views.ProblemRowView;

/**
 * Activity for listing {@code Problem}s.
 *
 * @see ca.klapstein.baudit.data.Problem
 */
public class PatientHomeActivity extends AppCompatActivity implements ProblemListView {

    private PatientHomePresenter presenter;
    private ProblemListAdapter adapter;
    private DrawerLayout drawerLayout;
    private TextView problemCountText;

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

        presenter = new PatientHomePresenter(this);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    menuItem.setChecked(true);
                    drawerLayout.closeDrawers();
                    return true;
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
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.notifyStarted();
    }

    @Override
    public void update() {
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateProblemCountText() {
        problemCountText.setText(String.format(
            getResources().getString(R.string.problem_count),
            presenter.getProblemCount()
        ));
    }

    private class ProblemListAdapter extends RecyclerView.Adapter<ProblemViewHolder> {

        @Override @NonNull
        public ProblemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            CardView v = (CardView) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_problem, viewGroup, false);
            return new ProblemViewHolder(v); //Wrap it in a ViewHolder.
        }

        @Override
        public void onBindViewHolder(@NonNull ProblemViewHolder viewHolder, int i) {
            Problem problem = presenter.getProblemAt(i);
            viewHolder.setProblemTitleText(problem.getTitle());
            viewHolder.setProblemDateText(problem.getTimeStamp());
            viewHolder.setProblemDescriptionText(problem.getDescription());
        }

        @Override
        public int getItemCount() {
            return presenter.getProblemCount();
        }
    }

    private class ProblemViewHolder extends RecyclerView.ViewHolder implements ProblemRowView {

        private ImageView imageView;
        private TextView titleView;
        private TextView dateView;
        private TextView descriptionView;

        private ProblemViewHolder(CardView card) {
            super(card);
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
