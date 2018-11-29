package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.TextView;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.CareProviderHomePresenter;
import ca.klapstein.baudit.views.HomeView;
import ca.klapstein.baudit.views.PatientRowView;

/**
 * Activity for listing {@code Patient}s.
 *
 * @see ca.klapstein.baudit.data.Patient
 */
public class CareProviderHomeActivity extends AppCompatActivity implements HomeView {

    private CareProviderHomePresenter presenter;
    private RecyclerView patientRecyclerView;
    private PatientListAdapter adapter;
    private DrawerLayout drawerLayout;
    private TextView navHeaderUsername;
    private TextView navHeaderEmail;
    private TextView patientCountText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider_home);

        Toolbar toolbar = findViewById(R.id.patient_list_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setTitle(R.string.home);

        presenter = new CareProviderHomePresenter(this, getApplicationContext());

        drawerLayout = findViewById(R.id.patient_list_drawer_layout);

        patientCountText = findViewById(R.id.patient_count);
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
                                        CareProviderHomeActivity.this,
                                        EditAccountActivity.class
                                ));
                                return true;
                            default:
                                return true;
                        }
                    }
                });

        patientRecyclerView = findViewById(R.id.patient_list);
        adapter = new PatientListAdapter();
        patientRecyclerView.setAdapter(adapter);
        patientRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updatePatientCountText();
    }

    private void updatePatientCountText() {
        patientCountText.setText(String.format(
                getResources().getString(R.string.patient_count),
                presenter.getPatientCount()
        ));
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted();
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
        updatePatientCountText();
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
        Toast.makeText(this, getResources().getString(R.string.care_provider_account_load_failure), Toast.LENGTH_LONG).show();

    }

    private class PatientListAdapter extends RecyclerView.Adapter<PatientViewHolder> {

        @Override @NonNull
        public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            CardView v = (CardView) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_patient, viewGroup, false);
            return new PatientViewHolder(v); //Wrap it in a ViewHolder.
        }

        @Override
        public void onBindViewHolder(@NonNull PatientViewHolder viewHolder, int i) {
            presenter.onBindPatientRowViewAtPosition(viewHolder, i);
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: (EXTRA) launch a choice to either drop supporter the patient (EXTRA)
                    // TODO: launch ability to view patients problems and add records
                }
            });

            registerForContextMenu(viewHolder.cardView);
        }
        @Override
        public int getItemCount() {
            return presenter.getPatientCount();
        }
    }

    private class PatientViewHolder extends RecyclerView.ViewHolder implements PatientRowView {

        CardView cardView;
        TextView patientName;
        TextView patientProblemCount;

        PatientViewHolder(CardView card) {
            super(card);
            cardView = card;
            patientName = card.findViewById(R.id.patient_name);
            patientProblemCount = card.findViewById(R.id.patient_problem_count_num);
        }

        @Override
        public void updatePatientNameText(String patientName) {
            this.patientName.setText(patientName);
        }

        @Override
        public void updatePatientProblemNum(int problemNum) {
            patientProblemCount.setText(String.format(
                    getResources().getString(R.string.problem_count),
                    problemNum
            ));
        }

        @Override
        public void onStart() {

        }
    }
}
