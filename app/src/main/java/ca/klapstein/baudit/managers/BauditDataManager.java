package ca.klapstein.baudit.managers;

import ca.klapstein.baudit.data.*;

/**
 * Helper class that interacts with the {@code BauditDataBaseManager} (local) and {@code BauditRemoteManager} (remote)
 * to keep MVP presenters blind to the fact data may be coming from either a remote or local source.
 *
 * @see BauditDataBaseManager
 * @see BauditRemoteManager
 */
public class BauditDataManager {
    private static final String TAG = "BauditDataManager";

    private BauditRemoteManager remoteManager;
    private BauditDataBaseManager dataBaseManager;

    public BauditDataManager() {
        this.remoteManager = new BauditRemoteManager();
        this.dataBaseManager = new BauditDataBaseManager();
    }

    public User getUser(String username) {
        return null;
    }

    public boolean commitUser(User user) {
        return true;
    }

    public ProblemTreeSet getProblems(Patient patient) {
        return null;
    }

    public boolean commitProblem(Problem problem) {
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
}
