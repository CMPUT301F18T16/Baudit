package ca.klapstein.baudit.Activities;

import android.support.v7.widget.RecyclerView;
import ca.klapstein.baudit.Adapters.PatientAdapter;
import ca.klapstein.baudit.Data.PatientTreeSet;

public class PatientListActivity {
    private static final String TAG = "PatientListActivity";

    private PatientTreeSet patientTreeSet;
    private PatientAdapter patientAdapter;
    private RecyclerView patientRecyclerView;
}
