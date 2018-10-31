package ca.klapstein.baudit.Data;

import java.util.Iterator;

/**
 * Class that represents a Care Provider.
 */
public class CareProvider extends User {
    private static final String TAG = "CareProvider";

    private PatientTreeSet assignedPatientTreeSet;

    // UC-01.02.01: list all medical problems for a related account
    public ProblemTreeSet getProblemTreeSet(Patient patient){ return patient.getProblemTreeSet(); }

    // UC-02.01.01:  list all the records for a related medical problem
    public RecordTreeSet getRecordTreeSet(Patient patient, Problem problem){

        ProblemTreeSet problemTreeSet = patient.getProblemTreeSet();
        RecordTreeSet recordTreeSet = new RecordTreeSet();

        Iterator itr = problemTreeSet.iterator();
        while(itr.hasNext()){
            // TODO: if problem matches get the records
        }

        return recordTreeSet;
    }

    // UC-03.02.01: edit account contact information
    public void editContactInfo(ContactInfo contactInfo){ this.setContactInfo(contactInfo);}

    // UC-06.01.01: list patients assigned to care providers
    public PatientTreeSet getAssignedPatientTreeSet(){ return assignedPatientTreeSet; }

    // UC-07-01.01: assign patient to care providers
    public void assignPatient(Patient patient){ assignedPatientTreeSet.add(patient); }

    // UC-07.02.01: list all patients TODO: not sure how to

    // UC-07.03.01: add comment record to medical problem
    public void addCommentRecord(Record record, Problem problem){
        RecordTreeSet recordTreeSet = problem.getRecordTreeSet();
        recordTreeSet.add(record);
    }

    // UC-03.03.01: login into account
    public boolean attemptLogin(Username username, Password password){
        if (this.getUsername().getUsername().equals(username.getUsername())) {
            if (this.getPassword().getPassword().equals(password.getPassword()))
                return true;
            else
                return false;
        }
        else
            return false;
    }

    // UC-03.04.01: logout of account TODO
    public void logout(){}

    // UC-04.01.01: search for problem or record
    public ProblemTreeSet searchProblem(String searchString){
        ProblemTreeSet matchingProblems = new ProblemTreeSet();

        Iterator itr = assignedPatientTreeSet.iterator();
        while(itr.hasNext()){
            // TODO: iterate patients the problems, search using regex and add to matching problems
        }

        return matchingProblems;
    }

    public RecordTreeSet searchRecords(String searchString){

        RecordTreeSet matchingRecords = new RecordTreeSet();

        // TODO: iterate problems then records, search using regex and add to matching problems

        return matchingRecords;
    }
}

