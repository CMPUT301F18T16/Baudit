package ca.klapstein.baudit.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import ca.klapstein.baudit.data.CareProvider;
import ca.klapstein.baudit.data.PatientTreeSet;
import ca.klapstein.baudit.data.Username;
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

    private static final String PATIENT_TREESET_PREF_NAME = "mPatientTreeSet";
    private static final String PATIENT_TREESET_PREF_JSON_KEY = "mPatientTreeSetJson";

    private static final String CAREPROVIDER_PREF_NAME = "mCareProvider";
    private static final String CAREPROVIDER_PREF_JSON_KEY = "mCareProviderJson";

    private static final String LOGIN_ACCOUNT_USERNAME_PREF_NAME = "mLoginAccountUsername";
    private static final String LOGIN_ACCOUNT_USERNAME_JSON_KEY = "mLoginAccountUsernameJson";

    /**
     * Save a {@code Gson} compatible object into Android's Shared Preferences.
     *
     * @param context  {@code Context}
     * @param object   {@code Object}
     * @param prefName {@code String}
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

    public static void saveSharedPreferencesLoginAccountUsername(Context context, Username username) {
        saveSharedPreferencesObject(context, username, LOGIN_ACCOUNT_USERNAME_JSON_KEY);
    }

    public static Username loadSharedPreferencesLoginAccountUsername(Context context) {
        Username username;
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson = new Gson();
        String json = mPrefs.getString(LOGIN_ACCOUNT_USERNAME_JSON_KEY, "");
        Log.e(TAG, "loaded json: " + json);

        if (json.isEmpty()) {
            username = null;
        } else {
            Type type = new TypeToken<Username>() {
            }.getType();
            username = gson.fromJson(json, type);
        }
        return username;
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
    public static PatientTreeSet loadSharedPreferencesPatientTreeSet(Context context) {
        PatientTreeSet patientTreeSet;
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();

        String json = mPrefs.getString(PATIENT_TREESET_PREF_JSON_KEY, "");
        Log.d(TAG, "loaded mCareProviderJson: " + json);

        if (json.isEmpty()) {
            patientTreeSet = new PatientTreeSet();
        } else {
            Type type = new TypeToken<PatientTreeSet>() {
            }.getType();
            patientTreeSet = gson.fromJson(json, type);
        }
        return patientTreeSet;
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
