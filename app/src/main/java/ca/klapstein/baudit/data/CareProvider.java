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
        super(username, password, contactInfo);
        this.assignedPatientTreeSet = new PatientTreeSet();
    }

    public PatientTreeSet getAssignedPatientTreeSet(){
        return this.assignedPatientTreeSet;
    }

}
