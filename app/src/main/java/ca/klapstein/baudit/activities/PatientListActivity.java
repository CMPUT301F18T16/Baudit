package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.adapters.PatientAdapter;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.PatientTreeSet;
import ca.klapstein.baudit.views.PatientListView;

/**
 * Activity for listing {@code Patient}s.
 *
 * @see ca.klapstein.baudit.data.Patient
 */
public class PatientListActivity extends AppCompatActivity implements PatientListView {
    private static final String TAG = "PatientListActivity";

    private PatientTreeSet patientTreeSet;
    private PatientAdapter patientAdapter;
    private RecyclerView patientRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        // TODO: get patientTreeSet from local storage/remote
        patientAdapter = new PatientAdapter(patientTreeSet);

        // TODO: init patientRecyclerView
    }

    private void onPatientListItemClick(View view, final int position) {
        // TODO: create add menu
    }

    @Override
    public void addPatient(Patient patient) {

    }

    @Override
    public void editPatient(Patient patient, int position) {

    }

    @Override
    public void setPatientList(PatientTreeSet patientList) {

    }
}
