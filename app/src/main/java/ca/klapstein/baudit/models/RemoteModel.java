package ca.klapstein.baudit.models;

import android.util.Log;

/**
 * Helper class for managing Baudit's remote (i.e. ElasticSearch) usage.
 */
public class RemoteModel {
    private static final String TAG = "RemoteModel";

    public boolean uniqueID(String username) {
        // TODO: implement uniqueness checking of a userid given a string
        return true;
    }

    public boolean validateLogin(String username, String password) {
        Log.d(username, password);
        return username.equals("test") && password.equals("foo");
    }
}
