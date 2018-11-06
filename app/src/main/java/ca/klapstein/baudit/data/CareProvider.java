package ca.klapstein.baudit.data;

/**
 * Class that represents a Care Provider.
 */
public class CareProvider extends Account {
    private static final String TAG = "CareProvider";

    private PatientTreeSet assignedPatientTreeSet;

    public PatientTreeSet getAssignedPatientTreeSet() {
        return assignedPatientTreeSet;
    }
}