package ca.klapstein.baudit.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import ca.klapstein.baudit.data.CareProvider;
import ca.klapstein.baudit.data.Patient;
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

    /**
     * Save a {@code Gson} compatible {@code Object} into Android's Shared Preferences.
     *
     * @param context  {@code Context}
     * @param object   {@code Object}
     * @param JSONKey  {@code String}
     */
    private static void saveSharedPreferencesObject(Context context, Object object,
                                                    String JSONKey) {
        SharedPreferences mPrefs =
            PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        Log.d(TAG, "saved " + JSONKey + " json: " + json);
        prefsEditor.putString(JSONKey, json);
        prefsEditor.apply();
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
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson = new Gson();

        String json = mPrefs.getString(PATIENT_PREF_JSON_KEY, "");
        Log.d(TAG, "loaded " + PATIENT_PREF_JSON_KEY + " json: " + json);

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
    public static void saveSharedPreferencesCareProvider(Context context,
                                                         CareProvider careProvider) {
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
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson = new Gson();

        String json = mPrefs.getString(CAREPROVIDER_PREF_JSON_KEY, "");
        Log.d(TAG, "loaded " + CAREPROVIDER_PREF_JSON_KEY + " json: " + json);

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
