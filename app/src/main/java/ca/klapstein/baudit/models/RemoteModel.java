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

    public boolean uniqueID(String username) {
        // TODO: implement uniqueness checking of a userid given a string
        return true;
    }

    public boolean validateLogin(String username, String password) {
        Log.d(TAG, "Validating username: " + username);
        return "test".equals(username) && "foo".equals(password);
    }

    public static class GetPatients extends AsyncTask<String, Void, PatientTreeSet> {
        @Override
        protected PatientTreeSet doInBackground(String... search_parameters) {
            JestDroidClient client = createBaseClient();
            Log.d(TAG, "Elastic search parameters: " + Arrays.toString(search_parameters));
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
                Log.e(TAG, "Something went wrong when we tried to communicate with the elasticsearch server!", e);
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
                        Log.d(TAG, "successfully added patient remoteID: " + patient.getUsername().getUsernameString());
                    }
                } catch (Exception e) {
                    Log.e(TAG, "The application failed to build and send the patient", e);
                }
            }
            return null;
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
                        Log.d(TAG, "successfully added care provider remoteID: " + careProvider.getUsername().getUsernameString());
                    }
                } catch (Exception e) {
                    Log.e(TAG, "The application failed to build and send the patient", e);
                }
            }
            return null;
        }
    }
}
