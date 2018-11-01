package ca.klapstein.baudit.Data;

import java.util.Iterator;

/**
 * Class that represents a Patient.
 */
public class Patient extends User implements Comparable<Patient> {
    private static final String TAG = "Patient";
    private ProblemTreeSet problemTreeSet;

    public void Patient(){
        this.problemTreeSet = new ProblemTreeSet();
    }

    // UC-01.02.01: list problems
    public ProblemTreeSet getProblemTreeSet() { return problemTreeSet; }

    /* UC-01.03.01: edit medical problem TODO: replace edited record or done in problem class?
     public void editProblem(Problem editedProblem, Problem problem){} */

    // UC-02.01.01: list medical problem records
    public RecordTreeSet listProblemRecords(Problem problem){ return problem.getRecordTreeSet();}

    // UC-02.02.01: add record to medical problem
    public void addRecordToProblem(Record record, Problem problem){ problem.getRecordTreeSet().add(record); }

    /* UC-02.03.01: edit record of medical problem TODO: get problem from treeset then replace edited record or done in record?
     public void editRecord(Problem problem, Record editedRecord, Record record){ } */

    // UC-02.03.03: remind patient to add photo TODO

    // UC-04.01.01: search for problem or record
    public ProblemTreeSet searchProblem(String searchString){
        ProblemTreeSet results = new ProblemTreeSet();
        return results;
    }

    public RecordTreeSet searchRecords(String searchString){
        RecordTreeSet results = new RecordTreeSet();
        return results;
    }

    @Override
    public int compareTo(Patient p) {
        return this.getUsername().getUsername().compareTo(p.getUsername().getUsername());
    }

}