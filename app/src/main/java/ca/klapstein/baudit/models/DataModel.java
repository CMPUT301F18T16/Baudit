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

    public boolean uniqueID(String username) {
        // TODO: implement from RemoteModel
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

    @Nullable
    public CareProvider getCareProvider(Username username) {
        CareProvider careProvider = null;

        // TODO: attempt to get the care provider from the remote
        try {
            careProvider = new RemoteModel.GetCareProviders().execute(username.getUsernameString()).get().first();
        } catch (ExecutionException | InterruptedException e) {
            Log.e(TAG, "failure getting care provider from remote", e);
        }
        if (careProvider == null) {
            careProvider = PreferencesModel.loadSharedPreferencesCareProvider(context);
        }

        return careProvider;
    }

    public boolean commitCareProvider(CareProvider careProvider) {
        // TODO: save to remote
        new RemoteModel.AddCareProviderTask().execute(careProvider);
        // save to local
        PreferencesModel.saveSharedPreferencesCareProvider(context, careProvider);
        return true;
    }
}
