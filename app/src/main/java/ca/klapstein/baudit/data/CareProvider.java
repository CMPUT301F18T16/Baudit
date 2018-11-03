package ca.klapstein.baudit.data;

/**
 * Class that represents a Care Provider.
 */
public class CareProvider extends User {
    private static final String TAG = "CareProvider";

    private PatientTreeSet assignedPatientTreeSet;

    public CareProvider(Username username, Password password, ContactInfo contactInfo){
        this.setUsername(username);
        this.setPassword(password);
        this.setContactInfo(contactInfo);
        this.assignedPatientTreeSet = new PatientTreeSet();
    }

    public ProblemTreeSet getProblemTreeSet(Patient patient) {
        if (this.getAssignedPatientTreeSet().contains(patient))
            return patient.getProblemTreeSet();
        else{
            throw new IllegalArgumentException("Patient not assigned to care provider");
        }
    }

    public RecordTreeSet getRecordTreeSet(Patient patient, Problem problem) {
        if (this.getAssignedPatientTreeSet().contains(patient))
            return problem.getRecordTreeSet();
        else {
            throw new IllegalArgumentException("Patient not assigned to care provider");
        }
    }

    public PatientTreeSet getAssignedPatientTreeSet(){
        return this.assignedPatientTreeSet;
    }
}
