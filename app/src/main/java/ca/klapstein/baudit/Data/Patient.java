package ca.klapstein.baudit.Data;

import java.util.Iterator;

/**
 * Class that represents a Patient.
 */
public class Patient extends User {
    private static final String TAG = "Patient";
    private ProblemTreeSet problemTreeSet = new ProblemTreeSet();

    // UC-01.01.01: add problem
    public void addProblem(Problem problem){ problemTreeSet.add(problem); }

    // UC-01.02.01: list problems
    public ProblemTreeSet getProblemTreeSet() {
        return problemTreeSet;
    }

    // UC-01.03.01: edit medical problem TODO: replace edited record or done in problem class?
    public void editProblem(Problem editedProblem, Problem problem){}

    // UC-01.04.1: delete problem
    public void deleteProblem(Problem deleteProblem){ problemTreeSet.remove(deleteProblem); }

    // UC-02.01.01: list medical problem records
    public RecordTreeSet listProblemRecords(Problem problem){ return problem.getRecordTreeSet();}

    // UC-02.02.01: add record to medical problem
    public void addRecordToProblem(Record record, Problem problem){ problem.getRecordTreeSet().add(record); }

    // UC-02.03.01: edit record of medical problem TODO: get problem from treeset then replace edited record or done in record?
    public void editRecord(Problem problem, Record editedRecord, Record record){ }

    // UC-03.02.01: edit account contact information
    public void editContactInfo(ContactInfo contactInfo){ this.setContactInfo(contactInfo);}

    // UC-03.03.01: view username or account contact information
    public ContactInfo getContactInfo(){ return this.getContactInfo(); }
    public Username getUsername(){ return this.getUsername(); }

    // UC-02.03.03: remind patient to add photo TODO

    // UC-03.03.01: login into account
    public boolean attemptLogin(Username username, Password password){
        if (this.getUsername().equals(username)) {
            if (this.getPassword().equals(password))
                return true;
            else return false;
        }
        else
            return false;
    }

    // UC-03.04.01: logout of account TODO

    // UC-04.01.01: search for problem or record
    public ProblemTreeSet searchProblem(String searchString){
        ProblemTreeSet matchingProblems = new ProblemTreeSet();

        Iterator itr = problemTreeSet.iterator();
        while(itr.hasNext()){
            // TODO: problemTitle.matches(".*" + searchString + ".*");
            // add to matchingTreeSet
        }

        return matchingProblems;
    }

    public RecordTreeSet searchRecords(String searchString){

        RecordTreeSet matchingRecords = new RecordTreeSet();
        // TODO: iterate problems then records and search using regex

        return matchingRecords;
    }

}