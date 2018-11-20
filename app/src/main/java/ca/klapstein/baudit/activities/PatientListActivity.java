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
import ca.klapstein.baudit.presenters.DrawerPresenter;
import ca.klapstein.baudit.presenters.PatientListPresenter;
import ca.klapstein.baudit.views.DrawerView;
import ca.klapstein.baudit.views.PatientListView;
import ca.klapstein.baudit.views.PatientRowView;

/**
 * Activity for listing {@code Patient}s.
 *
 * @see ca.klapstein.baudit.data.Patient
 */
public class PatientListActivity extends AppCompatActivity implements PatientListView, DrawerView {
    private static final String TAG = "PatientListActivity";

    private PatientListPresenter presenter;
    private DrawerPresenter drawerPresenter;
    private RecyclerView patientRecyclerView;
    private PatientListAdapter adapter;
    private DrawerLayout drawerLayout;
    private TextView navHeaderUsername;
    private TextView navHeaderEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        Toolbar toolbar = findViewById(R.id.patient_list_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setTitle(R.string.home);

        presenter = new PatientListPresenter(this, getApplicationContext());
        drawerPresenter = new DrawerPresenter(this, getApplicationContext());
        drawerLayout = findViewById(R.id.patient_list_drawer_layout);

        // TODO: is this overkill?
        drawerLayout.addDrawerListener(
                new DrawerLayout.SimpleDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        drawerPresenter.viewStarted();
                    }
                }
        );

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
                                        PatientListActivity.this,
                                        EditAccountActivity.class
                                ));
                                return true;
                            case (R.id.nav_logout):
                                new LogoutDialog().show(getSupportFragmentManager(), LogoutDialog.TAG);
                                finish();
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
    public void update() {
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        drawerPresenter.viewStarted();
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
    public void setUsername(String name) {
        navHeaderUsername.setText(name);
    }

    @Override
    public void setEmail(String email) {
        navHeaderEmail.setText(email);
    }

    private class PatientListAdapter extends RecyclerView.Adapter<PatientViewHolder> {
        private static final String TAG = "PatientListAdapter";

        @NonNull
        @Override
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
        private static final String TAG = "PatientViewHolder";

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
