package ca.klapstein.baudit.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import android.util.Log;
import android.view.*;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.CareProviderHomePresenter;
import ca.klapstein.baudit.views.CareProviderHomeView;
import ca.klapstein.baudit.views.PatientRowView;

import static ca.klapstein.baudit.activities.ViewAccountActivity.VIEW_ACCOUNT_USERNAME_EXTRA;

/**
 * Activity for listing {@code Patient}s.
 *
 * @see ca.klapstein.baudit.data.Patient
 */
public class CareProviderHomeActivity extends AppCompatActivity implements CareProviderHomeView {

    public static final String PATIENT_USERNAME_EXTRA = "patientUsername";

    private static final String TAG = "CareProviderHome";

    private final int REQUEST_CODE_QR_SCAN = 101;

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
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        presenter = new CareProviderHomePresenter(this, getApplicationContext());

        drawerLayout = findViewById(R.id.patient_list_drawer_layout);

        patientCountText = findViewById(R.id.patient_count);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View navHeaderView = navigationView.inflateHeaderView(R.layout.drawer_header);
        navHeaderUsername = navHeaderView.findViewById(R.id.nav_header_username);
        navHeaderEmail = navHeaderView.findViewById(R.id.nav_header_email);

        navHeaderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CareProviderHomeActivity.this, ViewAccountActivity.class);
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
                                CareProviderHomeActivity.this,
                                EditCareProviderAccountActivity.class
                            ));
                            break;
                        case (R.id.nav_display_qr_code):
                            startActivity(new Intent(
                                getApplicationContext(),
                                DisplayQRCodeActivity.class
                            ));
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });

        patientRecyclerView = findViewById(R.id.patient_list);
        adapter = new PatientListAdapter();
        patientRecyclerView.setAdapter(adapter);
        patientRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.care_provider_home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanQRCode();
            }
        });
    }

    @Override
    public void updateRemovePatientError() {
        Toast.makeText(this, getResources().getString(R.string.care_provider_remove_patient_failure), Toast.LENGTH_LONG).show();
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
        inflater.inflate(R.menu.care_provider_home_menu, menu);
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

    /**
     * Start an activity to start the camera and scan a QR code.
     */
    @Override
    public void startScanQRCode() {
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes
            startActivityForResult(intent, REQUEST_CODE_QR_SCAN);
        } catch (Exception e) {
            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            startActivity(marketIntent);
        }
    }

    /**
     * Catch the result from the QR code scanning activity. And send its obtained data (if the scan
     * was successful) to the presenter.
     *
     * @param requestCode {@code int}
     * @param resultCode  {@code int}
     * @param data        {@code Intent}
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (resultCode == RESULT_OK) {
                String username = data.getStringExtra("SCAN_RESULT");
                Log.i(TAG, "obtained qr code decoded string: " + username);
                presenter.assignPatient(username);
            } else if (resultCode == RESULT_CANCELED) {
                Log.e(TAG, "obtained invalid qr code activity result: " + resultCode);
            }
        }
    }

    @Override
    public void updateScanQRCodeError() {
        Toast.makeText(
            this,
            getResources().getString(R.string.assign_patient_scan_failure),
            Toast.LENGTH_LONG
        ).show();
    }

    @Override
    public void updatePatientCount(int patientNumber) {
        patientCountText.setText(String.format(
                getResources().getString(R.string.patient_count),
                patientNumber
        ));
    }

    private class PatientListAdapter extends RecyclerView.Adapter<PatientViewHolder> {

        @Override
        @NonNull
        public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            CardView v = (CardView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_patient, viewGroup, false);
            return new PatientViewHolder(v); //Wrap it in a ViewHolder.
        }

        @Override
        public void onBindViewHolder(@NonNull final PatientViewHolder viewHolder, int i) {
            presenter.onBindPatientRowViewAtPosition(viewHolder, i);

            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(
                        CareProviderHomeActivity.this,
                        CareProviderProblemListActivity.class
                    );
                    intent.putExtra(
                        PATIENT_USERNAME_EXTRA,
                        viewHolder.patientUsername.getText().toString()
                    );
                    startActivity(intent);
                }
            });

            viewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu menu = new PopupMenu(getApplicationContext(), viewHolder.cardView);
                    menu.inflate(R.menu.patient_popup_menu);
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.view_patient_account:
                                    Intent intent = new Intent(
                                        getApplicationContext(),
                                        ViewAccountActivity.class
                                    );
                                    intent.putExtra(
                                        VIEW_ACCOUNT_USERNAME_EXTRA,
                                        presenter.getPatientUsername(
                                            viewHolder.getAdapterPosition()
                                        )
                                    );
                                    startActivity(intent);
                                    break;
                                case R.id.remove_patient:
                                    new AlertDialog.Builder(CareProviderHomeActivity.this)
                                        .setTitle(R.string.remove_patient_question)
                                        .setCancelable(true)
                                        .setNegativeButton(R.string.cancel, null)
                                        .setPositiveButton(R.string.remove,
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface di, int i) {
                                                    presenter.removePatient(
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

            registerForContextMenu(viewHolder.cardView);
        }

        @Override
        public int getItemCount() {
            return presenter.getPatientCount();
        }
    }

    private class PatientViewHolder extends RecyclerView.ViewHolder implements PatientRowView {

        private CardView cardView;
        private TextView patientName;
        private TextView patientUsername;
        private TextView patientProblemCount;

        PatientViewHolder(CardView card) {
            super(card);
            cardView = card;
            patientName = card.findViewById(R.id.card_patient_name);
            patientUsername = card.findViewById(R.id.card_patient_username);
            patientProblemCount = card.findViewById(R.id.card_patient_problem_count);
        }

        @Override
        public void onStart() {
            // Do nothing.
        }

        @Override
        public void updatePatientNameText(String firstName, String lastName) {
            String name = firstName + " "  + lastName;
            patientName.setText(name);
        }

        @Override
        public void updatePatientUsernameText(String username) {
            patientUsername.setText(username);
        }

        @Override
        public void updatePatientProblemNum(int problemNum) {
            patientProblemCount.setText(String.valueOf(problemNum));
        }
    }
}
