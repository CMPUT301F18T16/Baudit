package ca.klapstein.baudit.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.CareProvider;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Username;
import com.google.gson.Gson;

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

    public <T extends Account> void setOfflineLoginAccount(@NonNull T account) {
        Log.i(TAG, "setting LoginAccount: " + account.getUsername().toString());

        // clear the login account saved state first
        clearOfflineLoginAccount();

        // Save the specific account type to shared prefs if applicable
        if (account instanceof Patient){
            PreferencesModel.saveSharedPreferencesPatient(context, (Patient) account);
        } else if (account instanceof CareProvider){
            PreferencesModel.saveSharedPreferencesCareProvider(context, (CareProvider) account);
        } else {
            Log.e(TAG, "abstract account type: " + account.getClass().getSimpleName() + " not saving as offline login account");
        }
    }

    public void clearOfflineLoginAccount() {
        Log.i(TAG, "clearing LoginAccount");
        PreferencesModel.saveSharedPreferencesPatient(context, null);
        PreferencesModel.saveSharedPreferencesCareProvider(context, null);
    }

    @Nullable
    public Patient getLoggedInPatient() {
        Patient patient = PreferencesModel.loadSharedPreferencesPatient(context);
        if (patient != null) {
            return getPatient(patient.getUsername());
        }
        return null;
    }

    @Nullable
    public CareProvider getLoggedInCareProvider() {
        CareProvider careProvider = PreferencesModel.loadSharedPreferencesCareProvider(context);
        if (careProvider != null) {
            return getCareProvider(careProvider.getUsername());
        }
        return null;
    }

    @Nullable
    public Account getLoggedInAccount() {
        // attempt to get the account as a patient
        Patient patient = getLoggedInPatient();
        CareProvider careProvider = getLoggedInCareProvider();
        return validateAccountRetrieval(patient, careProvider);
    }

    @Nullable
    private Account validateAccountRetrieval(@Nullable Patient patient, @Nullable CareProvider careProvider) {
        if ((patient != null) && (careProvider != null)) {
            // this is awkward we should not be able to get a Patient **AND** CareProvider from the same login
            // credentials (username/password)
            Log.e(TAG, "retrieved both Patient and CareProvider");
            return null;
        } else if (patient != null) {
            return patient;
        } else if (careProvider != null) { // careprovider **MUST** not be false
            return careProvider;
        } else {
            return null;
        }
    }

    /**
     * Validate whether a given {@code Username} is not already taken within the remote.
     *
     * @param username {@code Username}
     * @return {@code true} if the {@code Username} is not already taken, otherwise {@code false}
     */
    public boolean uniqueID(Username username) {
        return getPatient(username) == null && getCareProvider(username) == null;
    }

    /**
     * Validate that a given username matches to a valid user within the remote ElasticSearch.
     * Or validate that a local authentication token exists within the local.
     *
     * @param username {@code Username}
     * @return {@code Account} that can be potentially cast into either a {@code Patient} or {@code CareProvider}
     */
    @Nullable
    public Account validateLogin(Username username) {
        try {
            Account account = new RemoteModel.ValidateLogin().execute(username.toString()).get();
            if (account == null) {
                return null;
            }
            // attempt to get either a valid Patient or CareProvider from the obtained Account's username
            Patient patient = getPatient(account.getUsername());
            CareProvider careProvider = getCareProvider(account.getUsername());
            return validateAccountRetrieval(patient, careProvider);
        } catch (ExecutionException | InterruptedException e) {
            Log.e(TAG, "failure getting Account from remote", e);
            return null;
        }
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
        Patient remotePatient = null;
        try {
            remotePatient = new RemoteModel.GetPatient().execute(username.toString()).get();
        } catch (ExecutionException | InterruptedException e) {
            Log.e(TAG, "failure getting Patient from remote", e);
        }

        // check local
        Patient localPatient = PreferencesModel.loadSharedPreferencesPatient(context);
        if (localPatient == null) {
            return remotePatient;
        }

        // merge both remote and local
        if (remotePatient != null) {
            localPatient.getProblemTreeSet().addAll(remotePatient.getProblemTreeSet());
        }

        // ensure we are getting the right Patient from local storage
        if (!localPatient.getUsername().equals(username)) {
            localPatient = null;
        }

        Log.d(TAG, "loaded patient json: " + new Gson().toJson(localPatient));
        return localPatient;
    }

    /**
     * Commit the given {@code Patient} into both local and remote storage.
     *
     * @param patient {@code Patient}
     */
    public void commitPatient(Patient patient) {
        // add to local
        PreferencesModel.saveSharedPreferencesPatient(context, patient);

        // add to remote
        new RemoteModel.AddPatientTask().execute(patient);
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
        CareProvider remoteCareProvider = null;
        try {
            remoteCareProvider = new RemoteModel.GetCareProvider().execute(username.toString()).get();
        } catch (ExecutionException | InterruptedException e) {
            Log.e(TAG, "failure getting CareProvider from remote", e);
        }

        // check local
        CareProvider localCareProvider = PreferencesModel.loadSharedPreferencesCareProvider(context);
        if (localCareProvider == null) {
            return remoteCareProvider;
        }

        // ensure we are getting the right CareProvider from local storage
        if (!localCareProvider.getUsername().equals(username)) {
            localCareProvider = null;
        } else if (remoteCareProvider != null) {
            // merge remote and local
            localCareProvider.getAssignedPatientTreeSet().addAll(remoteCareProvider.getAssignedPatientTreeSet());
        }

        Log.d(TAG, "loaded care provider json: " + new Gson().toJson(localCareProvider));
        return localCareProvider;
    }

    /**
     * Commit the given {@code CareProvider} into both local and remote storage.
     *
     * @param careProvider {@code CareProvider}
     */
    public void commitCareProvider(CareProvider careProvider) {
        // save to local
        PreferencesModel.saveSharedPreferencesCareProvider(context, careProvider);

        // save to remote
        new RemoteModel.AddCareProviderTask().execute(careProvider);
    }

    /**
     * Commit an subclass of {@code Account} to both local and remote storage.
     *
     * @param account the account to commit
     * @param <T> A subclass of {@code Account}
     */
    public <T extends Account> void commitAccount(T account) {
        if (account instanceof Patient) {
            commitPatient((Patient) account);
        } else if (account instanceof CareProvider) {
            commitCareProvider((CareProvider) account);
        } else {
            Log.e(TAG, "abstract account type: " + account.getClass().getSimpleName() + " not committing account");
        }
    }
}
