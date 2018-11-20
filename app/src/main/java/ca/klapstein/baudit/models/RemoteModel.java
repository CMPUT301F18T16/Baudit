package ca.klapstein.baudit.models;

import android.os.AsyncTask;
import android.util.Log;
import ca.klapstein.baudit.data.Account;
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
 * Helper class for managing Baudit's remote storage/databasing solution.
 * <p>
 * Baudit uses a open ElasticSearch cluster located at:
 * <p>
 * http://cmput301.softwareprocess.es:8080
 * <p>
 * Note: all {@code Patient}s and {@code CareProviders} are uniquely identified by their username within
 * the remote ElasticSearch.
 */
class RemoteModel {
    private static final String TAG = "RemoteModel";
    private static final String REMOTE_TEST_URL = "http://cmput301.softwareprocess.es:8080/cmput301f18t16test/";

    // TODO: switch to PROD URL on release
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
     * return {@code true} if the username {@code String} representation is not already taken, otherwise {@code false}
     */
    public static class UniqueID extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... search_parameters) {
            JestDroidClient client = createBaseClient();
            Log.d(TAG, "elastic search parameters: " + Arrays.toString(search_parameters));
            String query = "{\n" +
                    "   \"query\": {\n" +
                    "       \"bool\": {\n" +
                    "           \"must\": [\n" +
                    "                {\"match\": \n" +
                    "                    {\"_id\" : \"" + search_parameters[0] + "\"} \n" +
                    "                } \n" +
                    "           ] \n" +
                    "       } \n" +
                    "   } \n" +
                            "}";

            Log.d(TAG, "search query:\n" + query);
            Search search = new Search.Builder(query)
                    .addIndex(CARE_PROVIDER_INDEX)
                    .addIndex(PATIENT_INDEX)
                    .build();
            Log.d(TAG, "search json: " + search.toString());

            try {
                JestResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Account> accountList;
                    accountList = result.getSourceAsObjectList(Account.class);
                    return (accountList.size() == 0);
                }
            } catch (Exception e) {
                Log.e(TAG, "failed to validate account username from remote", e);
                return false; // assume false on failure to avoid an unexpected state
            }
            return false;
        }
    }

    /**
     * Validate that a given username and password pair match to valid {@code Account} within the remote ElasticSearch.
     * <p>
     *
     * return {@code Account} corresponding to the username and password, or null if no such account is found
     */
    public static class ValidateLogin extends AsyncTask<String, Void, Account> {
        @Override
        protected Account doInBackground(String... search_parameters) {
            JestDroidClient client = createBaseClient();
            Log.d(TAG, "elastic search parameters: " + Arrays.toString(search_parameters));
            ArrayList<Account> accountArrayList = new ArrayList<>();
            String query = "{\n" +
                    "   \"query\": {\n" +
                    "       \"bool\": {\n" +
                    "           \"must\": [\n" +
                    "                {\"match\": \n" +
                    "                    {\"_id\" : \"" + search_parameters[0] + "\"} \n" +
                    "                }, \n" +
                    "                {\"match\": \n" +
                    "                    {\"password\" : \"" + search_parameters[1] + "\"} \n" +
                    "                } \n" +
                    "           ] \n" +
                    "       } \n" +
                    "   } \n" +
                    "}";


            Log.d(TAG, "search query:\n" + query);
            Search search = new Search.Builder(query)
                    .addIndex(PATIENT_INDEX)
                    .addIndex(CARE_PROVIDER_INDEX)
                    .build();
            Log.d(TAG, "search json: " + search.toString());
            try {
                JestResult result = client.execute(search);

                if (result.isSucceeded()) {
                    List<Account> accountList;
                    accountList = result.getSourceAsObjectList(Account.class);
                    accountArrayList.addAll(accountList);
                }

            } catch (Exception e) {
                Log.e(TAG, "failed to get patients from remote", e);
            }
            if (accountArrayList.size() > 1) {
                // we shouldn't encounter this. But, someone could poison our elasticsearch
                // writing a log of abnormal results is the least we can do
                Log.w(TAG, "more than one account found via query using the first result");
            } else if (accountArrayList.size() == 0) {
                Log.w(TAG, "no matching account found via query");
                return null;
            }
            return accountArrayList.get(0);
        }
    }

    /**
     * Search the remote ElasticSearch for a {@code Patient} with the given username.
     * <p>
     * Return either {@code null} if no {@code Patient} was found with the given username.
     * Or return an instance of the first found {@code Patient} with a matching username.
     */
    public static class GetPatient extends AsyncTask<String, Void, Patient> {
        @Override
        protected Patient doInBackground(String... search_parameters) {
            JestDroidClient client = createBaseClient();
            Log.d(TAG, "elastic search parameters: " + Arrays.toString(search_parameters));
            ArrayList<Patient> patientArrayList = new ArrayList<>();
            String query = "{\n" +
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
                // we shouldn't encounter this. But, someone could poison our elasticsearch
                // writing a log of abnormal results is the least we can do
                Log.w(TAG, "more than one patients found via query using the first result");
            } else if (patientArrayList.size() == 0) {
                Log.w(TAG, "no matching patients found via query");
                return null;
            }
            return patientArrayList.get(0);
        }
    }

    /**
     * Get all {@code Patient}s contained within the remote ElasticSearch.
     * Return them as a {@code PatientTreeSet}.
     */
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

    /**
     * Add/update a single {@code Patient} within the remote ElasticSearch.
     */
    public static class AddPatientTask extends AsyncTask<Patient, Void, Void> {
        @Override
        protected Void doInBackground(Patient... patients) {
            JestDroidClient client = createBaseClient();
            for (Patient patient : patients) {
                Index index = new Index.Builder(patient)
                        .index(PATIENT_INDEX)
                        .id(patient.getUsername().toString()) // this sets the id...
                        .build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        assert patient.getUsername().toString().equals(result.getId());
                        Log.d(TAG, "successfully added patient to remote: remoteID: " + patient.getUsername().toString());
                    }
                } catch (Exception e) {
                    Log.e(TAG, "failed to add patient to remote", e);
                }
            }
            return null;
        }
    }

    /**
     * Search the remote ElasticSearch for a {@code CareProvider} with the given username.
     * <p>
     * Return either {@code null} if no {@code CareProvider} was found with the given username.
     * Or return an instance of the first found {@code CareProvider} with a matching username.
     */
    public static class GetCareProvider extends AsyncTask<String, Void, CareProvider> {
        @Override
        protected CareProvider doInBackground(String... search_parameters) {
            JestDroidClient client = createBaseClient();
            Log.d(TAG, "elastic search parameters: " + Arrays.toString(search_parameters));
            ArrayList<CareProvider> careProviderArrayList = new ArrayList<>();
            String query = "{\n" +
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

    /**
     * Add/update a single {@code CareProvider} within the remote ElasticSearch.
     */
    public static class AddCareProviderTask extends AsyncTask<CareProvider, Void, Void> {
        @Override
        protected Void doInBackground(CareProvider... careProviders) {
            JestDroidClient client = createBaseClient();
            for (CareProvider careProvider : careProviders) {
                Index index = new Index.Builder(careProvider)
                        .index(CARE_PROVIDER_INDEX)
                        .id(careProvider.getUsername().toString()) // this sets the id...
                        .build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        assert careProvider.getUsername().toString().equals(result.getId());
                        Log.d(TAG, "successfully added care provider to remote: remoteID: " + careProvider.getUsername().toString());
                    }
                } catch (Exception e) {
                    Log.e(TAG, "failed to add the care provider to remote", e);
                }
            }
            return null;
        }
    }
}
