package ca.klapstein.baudit.models;

import android.os.AsyncTask;
import android.util.Log;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
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

    private static final String PROBLEM_INDEX = "baudit-problems";

    private static JestDroidClient client;

    private static void verifySettings() {
        if (client == null) {
            DroidClientConfig config = new DroidClientConfig.Builder(REMOTE_TEST_URL).build();
            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

    public boolean uniqueID(String username) {
        // TODO: implement uniqueness checking of a userid given a string
        return true;
    }

    public boolean validateLogin(String username, String password) {
        Log.d(username, password);
        return "test".equals(username) && "foo".equals(password);
    }

    public static class GetProblemsTask extends AsyncTask<String, Void, ProblemTreeSet> {
        @Override
        protected ProblemTreeSet doInBackground(String... search_parameters) {
            verifySettings();
            Log.d(TAG, "Elastic search parameters: " + Arrays.toString(search_parameters));
            ProblemTreeSet problemTreeSet = new ProblemTreeSet();
            Search search = new Search.Builder(Arrays.toString(search_parameters))
                    .addIndex(PROBLEM_INDEX)
                    .addType(Problem.ES_TYPE)
                    .build();
            try {
                JestResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Problem> problemList;
                    problemList = result.getSourceAsObjectList(Problem.class);
                    Log.d(TAG, "obtained tweets: " + problemList.toString());
                    problemTreeSet.addAll(problemList);
                }
            } catch (Exception e) {
                Log.e(TAG, "Something went wrong when we tried to communicate with the elasticsearch server!", e);
            }
            return problemTreeSet;
        }
    }

    public static class AddProblemsTask extends AsyncTask<Problem, Void, Void> {
        @Override
        protected Void doInBackground(Problem... problems) {
            verifySettings();
            Index index = new Index.Builder(problems[0])
                    .index(PROBLEM_INDEX)
                    .type(Problem.ES_TYPE)
                    .build();
            try {
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {
                    problems[0].setProblemID(result.getId());
                }
            } catch (Exception e) {
                Log.e(TAG, "The application failed to build and send the tweets", e);
            }
            return null;
        }
    }
}
