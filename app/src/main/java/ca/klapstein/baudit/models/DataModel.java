package ca.klapstein.baudit.models;

import ca.klapstein.baudit.data.*;

/**
 * Helper class that interacts with the {@code DatabaseModel} (local) and {@code RemoteModel} (remote)
 * to keep MVP presenters blind to the fact data may be coming from either a remote or local source.
 *
 * @see DatabaseModel
 * @see RemoteModel
 */
public class DataModel {

    private RemoteModel remoteModel;
    private DatabaseModel databaseModel;

    public DataModel() {
        this.remoteModel = new RemoteModel();
        this.databaseModel = new DatabaseModel();
    }

    public Account getUser(String username) {
        return null;
    }

    public boolean commitUser(Account account) {
        return true;
    }

    public ProblemTreeSet getProblems(Patient patient) {
        return null;
    }

    public boolean commitProblem(Problem problem) {
        return true;
    }

    public boolean deleteProblem(Problem problem) {
        return true;
    }

    public PatientTreeSet getPatients() {
        return null;
    }

    public boolean commitPatient(Patient patient) {
        return true;
    }

    public RecordTreeSet getRecords(Problem problem) {
        return null;
    }

    public boolean commitRecord(Record record) {
        return true;
    }

    public boolean deleteRecord(Record record) {
        return true;
    }

    public ProblemTreeSet getProblemTreeSet() {
        return this.databaseModel.getTestProblemTreeSet();
    }
}
