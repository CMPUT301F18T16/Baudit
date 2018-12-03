package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.CareProviderProblemListPresenter;
import ca.klapstein.baudit.views.CareProviderProblemListView;
import ca.klapstein.baudit.views.ProblemRowView;

import static ca.klapstein.baudit.activities.CareProviderHomeActivity.PATIENT_USERNAME_EXTRA;
import static ca.klapstein.baudit.activities.MapRecordsActivity.MAP_RECORDS_MODE;
import static ca.klapstein.baudit.activities.MapRecordsActivity.MAP_RECORDS_USERNAME;
import static ca.klapstein.baudit.activities.ProblemActivity.PROBLEM_MODE_EXTRA;
import static ca.klapstein.baudit.activities.ProblemActivity.PROBLEM_POSITION_EXTRA;

    private static final String TAG = "CareProviderProblemList";
    CareProviderProblemListPresenter presenter;
public class CareProviderProblemListActivity extends AppCompatActivity implements
    CareProviderProblemListView {

    private CareProviderProblemListPresenter presenter;
    private ProblemListAdapter adapter;
    private String patientUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider_problem_list);
        presenter = new CareProviderProblemListPresenter(this, this);

        patientUsername = getIntent().getStringExtra(PATIENT_USERNAME_EXTRA);

        Toolbar toolbar = findViewById(R.id.care_provider_problem_list_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(patientUsername);

        presenter = new CareProviderProblemListPresenter(this, getApplicationContext());

        RecyclerView problemRecyclerView = findViewById(R.id.care_provider_problem_list);
        adapter = new ProblemListAdapter();
        problemRecyclerView.setAdapter(adapter);
        problemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted(patientUsername);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.care_provider_problem_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.care_provider_problem_list_view_map:
                Intent intent = new Intent(getApplicationContext(), MapRecordsActivity.class);
                intent.putExtra(MAP_RECORDS_MODE, "all");
                intent.putExtra(MAP_RECORDS_USERNAME, patientUsername);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class ProblemListAdapter extends RecyclerView.Adapter<ProblemViewHolder> {

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
                    intent.putExtra(PROBLEM_POSITION_EXTRA, viewHolder.getAdapterPosition());
                    intent.putExtra(PROBLEM_MODE_EXTRA, "careprovider");
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
