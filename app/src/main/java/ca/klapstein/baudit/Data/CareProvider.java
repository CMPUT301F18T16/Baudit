package ca.klapstein.baudit.Data;

import java.util.Iterator;

import ca.klapstein.baudit.Activities.LogoutDialog;
import ca.klapstein.baudit.Managers.BauditRemoteManager;

/**
 * Class that represents a Care Provider.
 */
public class CareProvider extends User {
    private static final String TAG = "CareProvider";

    private PatientTreeSet assignedPatientTreeSet;

    public void CareProvider(){
        this.assignedPatientTreeSet = new PatientTreeSet();
    }

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

    // UC-06.01.01: list patients assigned to care providers
    public PatientTreeSet getAssignedPatientTreeSet(){ return assignedPatientTreeSet; }

    // UC-07-01.01: assign patient to care providers
    public void assignPatient(Patient patient){ this.getAssignedPatientTreeSet().add(patient); }

    // UC-07.02.01: list all patients TODO: not sure how to
    // This is implemented in PatientListActivity


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