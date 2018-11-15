package ca.klapstein.baudit.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import ca.klapstein.baudit.data.CareProvider;
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

    private static final String PATIENT_TREESET_PREF_NAME = "mPatientTreeSet";
    private static final String PATIENT_TREESET_PREF_JSON_KEY = "mPatientTreeSetJson";
    private static final String CAREPROVIDER_PREF_NAME = "mCareProvider";
    private static final String CAREPROVIDER_PREF_JSON_KEY = "mCareProviderJson";

    /**
     * Save a {@code PatientTreeSet} using Android's SharedPreferences.
     *
     * @param context        {@code Context}
     * @param patientTreeSet {@code PatientTreeSet}
     */
    static void saveSharedPreferencesPatientTreeSet(Context context, PatientTreeSet patientTreeSet) {
        SharedPreferences mPrefs = context.getSharedPreferences(PATIENT_TREESET_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(patientTreeSet);
        prefsEditor.putString(PATIENT_TREESET_PREF_JSON_KEY, json);
        prefsEditor.apply();
    }

    /**
     * Load the {@code PatientTreeSet} using Android's SharedPreferences.
     *
     * @param context {@code Context}
     * @return {@code PatientTreeSet}
     */
    static PatientTreeSet loadSharedPreferencesPatientTreeSet(Context context) {
        PatientTreeSet patientTreeSet;
        SharedPreferences mPrefs = context.getSharedPreferences(PATIENT_TREESET_PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(PATIENT_TREESET_PREF_NAME, "");
        if (json.isEmpty()) {
            patientTreeSet = new PatientTreeSet();
        } else {
            Type type = new TypeToken<PatientTreeSet>() {
            }.getType();
            patientTreeSet = gson.fromJson(json, type);
        }
        return patientTreeSet;
    }

    @Nullable
    public static CareProvider loadSharedPreferencesCareProvider(Context context) {
        CareProvider careProvider;
        SharedPreferences mPrefs = context.getSharedPreferences(CAREPROVIDER_PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(CAREPROVIDER_PREF_NAME, "");
        if (json.isEmpty()) {
            careProvider = null;
        } else {
            Type type = new TypeToken<CareProvider>() {
            }.getType();
            careProvider = gson.fromJson(json, type);
        }
        return careProvider;
    }

    static void saveSharedPreferencesCareProvider(Context context, CareProvider careProvider) {
        SharedPreferences mPrefs = context.getSharedPreferences(CAREPROVIDER_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(careProvider);
        prefsEditor.putString(CAREPROVIDER_PREF_JSON_KEY, json);
        prefsEditor.apply();
    }
}
