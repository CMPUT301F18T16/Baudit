package ca.klapstein.baudit.data;

/**
 * Class that represents a Patient.
 */
public class Patient extends User implements  Comparable<Patient>{
    private static final String TAG = "Patient";
    private ProblemTreeSet problemTreeSet;

    public Patient(Username username, Password password, ContactInfo contactInfo){
        setUsername(username);
        setPassword(password);
        setContactInfo(contactInfo);
        this.problemTreeSet = new ProblemTreeSet();
    }

    public ProblemTreeSet getProblemTreeSet() { return problemTreeSet; }

    public RecordTreeSet listProblemRecords(Problem problem){ return problem.getRecordTreeSet();}

    public void addRecordToProblem(Record record, Problem problem){ problem.getRecordTreeSet().add(record); }

    @Override
    public int compareTo(Patient p) {
        return  (int)(this.getUsername().getUsername().compareTo(p.getUsername().getUsername()));
    }

}