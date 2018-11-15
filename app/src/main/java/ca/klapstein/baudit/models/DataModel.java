package ca.klapstein.baudit.models;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.data.*;

import java.util.concurrent.ExecutionException;

/**
 * Helper class that interacts with the {@code PreferencesModel} (local) and {@code RemoteModel} (remote)
 * to keep MVP presenters blind to the fact data may be coming from either a remote or local source.
 *
 * @see RemoteModel
 * @see PreferencesModel
 */
public class DataModel {

    private static final String TAG = "DataModel";
    private final Context context;

    public DataModel(Context context) {
        this.context = context;
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

    public boolean validateLogin(String username, String password) {
        // TODO: implement from RemoteModel
        return true;
    }

    public boolean uniqueID(String username) {
        // TODO: implement from RemoteModel
        return true;
    }

    public boolean commitProblem(Problem problem) {
        return true;
    }

    public boolean deleteProblem(Problem problem) {
        return true;
    }

    public PatientTreeSet getPatients() {
        PatientTreeSet patientTreeSet = new PatientTreeSet();
        // get from the remote
        try {
            patientTreeSet.addAll(new RemoteModel.GetPatients().execute("").get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            Log.e(TAG, "failure getting patientTreeSet from remote", e);
        }
        // TODO: dedupe figure out newest
        // TODO: get from local
        patientTreeSet.addAll(PreferencesModel.loadSharedPreferencesPatientTreeSet(context));
        return patientTreeSet;
    }

    public boolean commitPatients(PatientTreeSet patientTreeSet) {
        // add patients to the remote
        new RemoteModel.AddPatientTask().execute(
                patientTreeSet.toArray(new Patient[0])
        );

        // add the patients to the local
        PreferencesModel.saveSharedPreferencesPatientTreeSet(context, patientTreeSet);
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
}
