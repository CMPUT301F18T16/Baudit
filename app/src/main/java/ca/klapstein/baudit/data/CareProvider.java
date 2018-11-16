package ca.klapstein.baudit.data;

/**
 * Class that represents a Care Provider.
 */
public class CareProvider extends Account {
    private static final String TAG = "CareProvider";

    public CareProvider(Username username, ContactInfo contactInfo, Password password) {
        super(username, contactInfo, password);
    }

    private PatientTreeSet assignedPatientTreeSet;

    public CareProvider(Username username, Password password, ContactInfo contactInfo){
        super(username, contactInfo, password);
        this.assignedPatientTreeSet = new PatientTreeSet();
    }

    /**
     * Get the {@code PatientTreeSet} of the {@code CareProvider}.
     *
     * @return {@code PatientTreeSet}
     */
    public PatientTreeSet getAssignedPatientTreeSet(){
        return this.assignedPatientTreeSet;
    }

}