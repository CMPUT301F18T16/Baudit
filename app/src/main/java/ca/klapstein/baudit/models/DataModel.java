package ca.klapstein.baudit.models;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import ca.klapstein.baudit.data.CareProvider;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.PatientTreeSet;
import ca.klapstein.baudit.data.Username;

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

    public boolean validateLogin(String username, String password) {
        // TODO: implement from RemoteModel
        // TODO: implement offline login method? Cookie/token based
        return RemoteModel.validateLogin(username, password);
    }

    /**
     * Validate whether a given username {@code String} representation is not already
     * taken within both the local and remote.
     *
     * @param username {@code String}
     * @return {@code true} if the username {@code String} representation is not already taken, otherwise {@code false}
     */
    static public boolean uniqueID(String username) {
        // TODO: validate from the local?
        // TODO: implement from RemoteModel
        return true;
    }

    /**
     * Get the {@code Patient} with the specified {@code Username}.
     * <p>
     * If no such {@code Patient} exists with the given {@code Username} return {@code null}.
     *
     * @param username {@code UserName} the username of the {@code Patient} to get
     * @return {@code Patient}
     */
    @Nullable
    public Patient getPatient(Username username) {
        // check remote
        try {
            return new RemoteModel.GetPatient().execute(username.getUsernameString()).get();
        } catch (ExecutionException | InterruptedException e) {
            Log.e(TAG, "failure getting Patient from remote", e);
        }

        // check local
        PatientTreeSet patientTreeSet = PreferencesModel.loadSharedPreferencesPatientTreeSet(context);
        for (Patient patient_i : patientTreeSet) {
            if (patient_i.getUsername().equals(username)) {
                return patient_i;
            }
        }
        return null;
    }

    /**
     * Get all the {@code Patient}s currently registered to Baudit.
     * <p>
     * TODO: this getter should be narrowed down in scope/reach
     *
     * @return {@code PatientTreeSet}
     */
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

    /**
     * Commit the given {@code Patient} into both local and remote storage.
     *
     * @param patient {@code Patient}
     */
    public void commitPatient(Patient patient) {
        // add patients to the remote
        new RemoteModel.AddPatientTask().execute(
                patient
        );

        // get the patientTreeSet from local and update it
        PatientTreeSet patientTreeSet = PreferencesModel.loadSharedPreferencesPatientTreeSet(context);
        if (patientTreeSet.contains(patient)) {
            patientTreeSet.remove(patient);
            patientTreeSet.add(patient);
        }

        // add the patients to the local
        PreferencesModel.saveSharedPreferencesPatientTreeSet(context, patientTreeSet);
    }

    /**
     * Commit the given {@code PatientTreeSet} into both local and remote storage.
     *
     * @param patientTreeSet {@code PatientTreeSet}
     */
    public void commitPatientTreeSet(PatientTreeSet patientTreeSet) {
        // add patients to the remote
        new RemoteModel.AddPatientTask().execute(
                patientTreeSet.toArray(new Patient[0])
        );

        // add the patients to the local
        PreferencesModel.saveSharedPreferencesPatientTreeSet(context, patientTreeSet);
    }

    /**
     * Get the {@code CareProvider} with the specified {@code Username}.
     *
     * If no such {@code CareProvider} exists with the given {@code Username} return {@code null}.
     *
     * @param username {@code UserName} the username of the {@code CareProvider} to get
     * @return {@code CareProvider}
     */
    @Nullable
    public CareProvider getCareProvider(Username username) {
        // check remote
        try {
            return new RemoteModel.GetCareProvider().execute(username.getUsernameString()).get();
        } catch (ExecutionException | InterruptedException e) {
            Log.e(TAG, "failure getting CareProvider from remote", e);
        }

        // check local
        return PreferencesModel.loadSharedPreferencesCareProvider(context);
    }

    /**
     * Commit the given {@code CareProvider} into both local and remote storage.
     *
     * @param careProvider {@code CareProvider}
     */
    public void commitCareProvider(CareProvider careProvider) {
        // TODO: save to remote
        new RemoteModel.AddCareProviderTask().execute(careProvider);
        // save to local
        PreferencesModel.saveSharedPreferencesCareProvider(context, careProvider);
    }
}
