package ca.klapstein.baudit.data;

/**
 * Class that represents a Care Provider.
 */
public class CareProvider extends Account {
    private static final String TAG = "CareProvider";

    private PatientTreeSet assignedPatientTreeSet;

    public CareProvider(Username username, Password password, ContactInfo contactInfo){
        this.setUsername(username);
        this.setPassword(password);
        this.setContactInfo(contactInfo);
        this.assignedPatientTreeSet = new PatientTreeSet();
    }

    public PatientTreeSet getAssignedPatientTreeSet() {
        return assignedPatientTreeSet;
    }
}
