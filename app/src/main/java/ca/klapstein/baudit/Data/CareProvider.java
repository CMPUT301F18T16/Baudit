package ca.klapstein.baudit.Data;


/**
 * Class that represents a Care Provider.
 */
public class CareProvider extends User {
    private static final String TAG = "CareProvider";

    private PatientTreeSet assignedPatientTreeSet;

    public CareProvider(){
        this.assignedPatientTreeSet = new PatientTreeSet();
    }

    // UC-01.02.01: list all medical problems for a related account
    public ProblemTreeSet getProblemTreeSet(Patient patient) {
        if (this.getAssignedPatientTreeSet().contains(patient))
            return patient.getProblemTreeSet();
        else{
            throw new IllegalArgumentException("Patient not assigned to care provider");
        }
    }

    // UC-02.01.01:  list all the records for a related medical problem
    public RecordTreeSet getRecordTreeSet(Patient patient, Problem problem){ return problem.getRecordTreeSet(); }

    // UC-06.01.01: list patients assigned to care providers
    public PatientTreeSet getAssignedPatientTreeSet(){
        return this.assignedPatientTreeSet;
    }

    // UC-07-01.01: assign patient to care providers
    public void assignPatient(Patient patient){
        this.getAssignedPatientTreeSet().add(patient);
    }

    // UC-07.02.01: list all patients
    // This is implemented in PatientListActivity
}