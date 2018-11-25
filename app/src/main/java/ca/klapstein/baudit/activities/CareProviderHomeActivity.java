package ca.klapstein.baudit.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
                            case (R.id.nav_logout):
                                new AlertDialog.Builder(
                                        CareProviderHomeActivity.this)
                                    .setTitle(R.string.log_out_question)
                                    .setCancelable(true)
                                    .setNegativeButton(R.string.cancel, null)
                                    .setPositiveButton(R.string.log_out,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface mDialogInterface,
                                                                int i) {
                                                presenter.logoutClicked();
                                                startActivity(new Intent(
                                                    CareProviderHomeActivity.this,
                                                    SplashActivity.class
                                                ));
                                                finish();
                                            }
                                        })
                                    .show();
                                return true;
                            default:
                                return true;
                        }
                    }
                });
        // TODO: get patientTreeSet from local storage/remote
        // TODO: logout methods?

        patientRecyclerView = findViewById(R.id.patient_list);
        adapter = new PatientListAdapter();
        patientRecyclerView.setAdapter(adapter);
        patientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted();
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
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
    public void updateUsernameDisplay(String name) {
        navHeaderUsername.setText(name);
    }

    @Override
    public void updateEmailDisplay(String email) {
        navHeaderEmail.setText(email);
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
        }

        @Override
        public int getItemCount() {
            return presenter.getPatientCount();
        }
    }

    private class PatientViewHolder extends RecyclerView.ViewHolder implements PatientRowView {

        CardView mCardView;
        TextView mNameView;

        PatientViewHolder(CardView card) {
            super(card);
            mCardView = card;
            mNameView = card.findViewById(R.id.patient_name);
        }

        @Override
        public void setPatientNameText(String patientName) {
            mNameView.setText(patientName);
        }

        @Override
        public void onStart() {

        }
    }
}
