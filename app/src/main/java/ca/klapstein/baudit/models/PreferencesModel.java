package ca.klapstein.baudit.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.CareProvider;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.PatientTreeSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Helper class for accessing the Android's SharedPreferences for use in Baudit.
 * <p>
 * Use {@code Gson} to serialize/deserialize Objects to/from a JSON string for saving/loading
 * in Android's SharedPreferences.
 *
 * @see com.google.gson.Gson
 * @see android.content.SharedPreferences
 * @see android.preference.PreferenceManager
 */
class PreferencesModel {
    private static final String TAG = "BauditPrefManager";

    private static final String PATIENT_TREESET_PREF_JSON_KEY = "mPatientTreeSetJson";

    private static final String CAREPROVIDER_PREF_JSON_KEY = "mCareProviderJson";

    private static final String PATIENT_PREF_JSON_KEY = "mPatientJson";

    private static final String LOGIN_ACCOUNT_JSON_KEY = "mLoginAccountJson";

    /**
     * Save a {@code Gson} compatible {@code Object} into Android's Shared Preferences.
     *
     * @param context  {@code Context}
     * @param object   {@code Object}
     * @param JSONKey  {@code String}
     */
    private static void saveSharedPreferencesObject(Context context, Object object, String JSONKey) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        Log.e(TAG, "saved json: " + json);
        prefsEditor.putString(JSONKey, json);
        prefsEditor.commit();
    }

    public static void saveSharedPreferencesLoginAccount(Context context, Account account) {
        saveSharedPreferencesObject(context, account, LOGIN_ACCOUNT_JSON_KEY);
    }

    public static Account loadSharedPreferencesLoginAccount(Context context) {
        Account account;
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson = new Gson();

        String json = mPrefs.getString(LOGIN_ACCOUNT_JSON_KEY, "");
        Log.e(TAG, "loaded json: " + json);

        if (json.isEmpty()) {
            account = null;
        } else {
            Type type = new TypeToken<Account>() {
            }.getType();
            account = gson.fromJson(json, type);
        }
        return account;
    }

    /**
     * Save a {@code PatientTreeSet} using Android's SharedPreferences.
     *
     * @param context        {@code Context}
     * @param patientTreeSet {@code PatientTreeSet}
     */
    public static void saveSharedPreferencesPatientTreeSet(Context context, PatientTreeSet patientTreeSet) {
        saveSharedPreferencesObject(context, patientTreeSet, PATIENT_TREESET_PREF_JSON_KEY);
    }

    /**
     * Load the {@code PatientTreeSet} using Android's SharedPreferences.
     *
     * @param context {@code Context}
     * @return {@code PatientTreeSet}
     */
    @NonNull
    public static PatientTreeSet loadSharedPreferencesPatientTreeSet(Context context) {
        PatientTreeSet patientTreeSet;
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();

        String json = mPrefs.getString(PATIENT_TREESET_PREF_JSON_KEY, "");
        Log.d(TAG, "loaded json: " + json);

        if (json.isEmpty()) {
            patientTreeSet = new PatientTreeSet();
        } else {
            Type type = new TypeToken<PatientTreeSet>() {
            }.getType();
            patientTreeSet = gson.fromJson(json, type);
        }

        // ensure we are never returning null
        // this mocks similar behaviour to the RemoteModel
        if (patientTreeSet == null){
            patientTreeSet = new PatientTreeSet();
        }
        return patientTreeSet;
    }

    /**
     * Save a {@code Patient} using Android's SharedPreferences.
     *
     * @param context {@code Context}
     * @param patient {@code Patient}
     */
    public static void saveSharedPreferencesPatient(Context context, Patient patient) {
        saveSharedPreferencesObject(context, patient, PATIENT_PREF_JSON_KEY);
    }

    /**
     * Load the {@code CareProvider} using Android's SharedPreferences.
     *
     * @param context {@code Context}
     * @return {@code CareProvider}
     */
    @Nullable
    public static Patient loadSharedPreferencesPatient(Context context) {
        Patient patient;
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();

        String json = mPrefs.getString(PATIENT_PREF_JSON_KEY, "");
        Log.d(TAG, "loaded json: " + json);

        if (json.isEmpty()) {
            patient = null;
        } else {
            Type type = new TypeToken<Patient>() {
            }.getType();
            patient = gson.fromJson(json, type);
        }
        return patient;
    }

    /**
     * Save a {@code CareProvider} using Android's SharedPreferences.
     *
     * @param context      {@code Context}
     * @param careProvider {@code CareProvider}
     */
    public static void saveSharedPreferencesCareProvider(Context context, CareProvider careProvider) {
        saveSharedPreferencesObject(context, careProvider, CAREPROVIDER_PREF_JSON_KEY);
    }

    /**
     * Load the {@code CareProvider} using Android's SharedPreferences.
     *
     * @param context {@code Context}
     * @return {@code CareProvider}
     */
    @Nullable
    public static CareProvider loadSharedPreferencesCareProvider(Context context) {
        CareProvider careProvider;
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();

        String json = mPrefs.getString(CAREPROVIDER_PREF_JSON_KEY, "");
        Log.d(TAG, "loaded json: " + json);

        if (json.isEmpty()) {
            careProvider = null;
        } else {
            Type type = new TypeToken<CareProvider>() {
            }.getType();
            careProvider = gson.fromJson(json, type);
        }
        return careProvider;
    }
}
