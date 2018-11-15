package ca.klapstein.baudit.models;

import android.os.AsyncTask;
import android.util.Log;
import ca.klapstein.baudit.data.CareProvider;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.PatientTreeSet;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class for managing Baudit's remote (i.e. ElasticSearch) usage.
 */
public class RemoteModel {
    private static final String TAG = "RemoteModel";
    private static final String REMOTE_TEST_URL = "http://cmput301.softwareprocess.es:8080/cmput301f18t16test/";
    private static final String REMOTE_PROD_URL = "http://cmput301.softwareprocess.es:8080/cmput301f18t16/";
    private static final String PATIENT_INDEX = "patient";
    private static final String CARE_PROVIDER_INDEX = "careprovider";

    private static JestDroidClient createBaseClient() {
        DroidClientConfig config = new DroidClientConfig.Builder(REMOTE_TEST_URL).build();
        JestClientFactory factory = new JestClientFactory();
        factory.setDroidClientConfig(config);
        return (JestDroidClient) factory.getObject();
    }

    /**
     * Validate whether a given username {@code String} representation is not already
     * taken within both remote.
     *
     * @param username {@code String}
     * @return {@code true} if the username {@code String} representation is not already taken, otherwise {@code false}
     */
    static public boolean uniqueID(String username) {
        // TODO: implement uniqueness checking of a userid given a string
        return true;
    }

    public static boolean validateLogin(String username, String password) {
        Log.d(TAG, "Validating username: " + username);
        return "test".equals(username) && "foo".equals(password);
    }

    public static class GetPatient extends AsyncTask<String, Void, Patient> {
        @Override
        protected Patient doInBackground(String... search_parameters) {
            JestDroidClient client = createBaseClient();
            Log.d(TAG, "elastic search parameters: " + Arrays.toString(search_parameters));
            ArrayList<Patient> patientArrayList = new ArrayList<>();
            String query =
                    "{\n" +
                            "    \"query\": {\n" +
                            "        \"ids\" : {\n" +
                            "            \"type\" : \"patient\",\n" +
                            "            \"values\" : [\"" + search_parameters[0] + "\"]\n" +
                            "         }\n" +
                            "     }\n" +
                            "}";

            Log.d(TAG, "search query:\n" + query);
            Search search = new Search.Builder(query)
                    .addIndex(PATIENT_INDEX)
                    .build();
            Log.d(TAG, "search json: " + search.toString());
            try {
                JestResult result = client.execute(search);

                if (result.isSucceeded()) {
                    List<Patient> patientList;
                    patientList = result.getSourceAsObjectList(Patient.class);
                    patientArrayList.addAll(patientList);
                }

            } catch (Exception e) {
                Log.e(TAG, "failed to get care providers from remote", e);
            }
            if (patientArrayList.size() > 1) {
                Log.w(TAG, "more than one patients found via query using the first result");
            } else if (patientArrayList.size() == 0) {
                Log.w(TAG, "no matching patients found via query");
                return null;
            }
            return patientArrayList.get(0);
        }
    }

    public static class GetPatients extends AsyncTask<String, Void, PatientTreeSet> {
        @Override
        protected PatientTreeSet doInBackground(String... search_parameters) {
            JestDroidClient client = createBaseClient();
            Log.d(TAG, "elastic search parameters: " + Arrays.toString(search_parameters));
            PatientTreeSet patientTreeSet = new PatientTreeSet();
            Search search = new Search.Builder(search_parameters[0])
                    .addIndex(PATIENT_INDEX)
                    .build();
            Log.d(TAG, "search json: " + search.toString());
            try {
                JestResult result = client.execute(search);

                if (result.isSucceeded()) {
                    List<Patient> patientList;
                    patientList = result.getSourceAsObjectList(Patient.class);
                    patientTreeSet.addAll(patientList);
                }
            } catch (Exception e) {
                Log.e(TAG, "failed to get patients from remote", e);
            }
            return patientTreeSet;
        }
    }

    public static class AddPatientTask extends AsyncTask<Patient, Void, Void> {
        @Override
        protected Void doInBackground(Patient... patients) {
            JestDroidClient client = createBaseClient();
            for (Patient patient : patients) {
                Index index = new Index.Builder(patient)
                        .index(PATIENT_INDEX)
                        .id(patient.getUsername().getUsernameString()) // this sets the id...
                        .build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        assert patient.getUsername().getUsernameString().equals(result.getId());
                        Log.d(TAG, "successfully added patient to remote: remoteID: " + patient.getUsername().getUsernameString());
                    }
                } catch (Exception e) {
                    Log.e(TAG, "failed to add patient to remote", e);
                }
            }
            return null;
        }
    }


    // TODO: rethink and clean
    public static class GetCareProvider extends AsyncTask<String, Void, CareProvider> {
        @Override
        protected CareProvider doInBackground(String... search_parameters) {
            JestDroidClient client = createBaseClient();
            Log.d(TAG, "elastic search parameters: " + Arrays.toString(search_parameters));
            ArrayList<CareProvider> careProviderArrayList = new ArrayList<>();
            String query =
                    "{\n" +
                            "    \"query\": {\n" +
                            "        \"ids\" : {\n" +
                            "            \"type\" : \"careprovider\",\n" +
                            "            \"values\" : [\"" + search_parameters[0] + "\"]\n" +
                            "         }\n" +
                            "     }\n" +
                            "}";

            Log.d(TAG, "search query:\n" + query);
            Search search = new Search.Builder(query)
                    .addIndex(CARE_PROVIDER_INDEX)
                    .build();
            Log.d(TAG, "search json: " + search.toString());
            try {
                JestResult result = client.execute(search);

                if (result.isSucceeded()) {
                    List<CareProvider> careProviderList;
                    careProviderList = result.getSourceAsObjectList(CareProvider.class);
                    careProviderArrayList.addAll(careProviderList);
                }

            } catch (Exception e) {
                Log.e(TAG, "failed to get care providers from remote", e);
            }
            if (careProviderArrayList.size() > 1) {
                Log.w(TAG, "more than one care provider found via query using the first result");
            } else if (careProviderArrayList.size() == 0) {
                Log.w(TAG, "no matching care providers found via query");
                return null;
            }
            return careProviderArrayList.get(0);
        }
    }

    public static class AddCareProviderTask extends AsyncTask<CareProvider, Void, Void> {
        @Override
        protected Void doInBackground(CareProvider... careProviders) {
            JestDroidClient client = createBaseClient();
            for (CareProvider careProvider : careProviders) {
                Index index = new Index.Builder(careProvider)
                        .index(CARE_PROVIDER_INDEX)
                        .id(careProvider.getUsername().getUsernameString()) // this sets the id...
                        .build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        assert careProvider.getUsername().getUsernameString().equals(result.getId());
                        Log.d(TAG, "successfully added care provider to remote: remoteID: " + careProvider.getUsername().getUsernameString());
                    }
                } catch (Exception e) {
                    Log.e(TAG, "failed to add the care provider to remote", e);
                }
            }
            return null;
        }
    }
}
