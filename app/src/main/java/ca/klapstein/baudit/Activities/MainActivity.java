package ca.klapstein.baudit.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;


/**
 * Starting Activity for Baudit.
 * <p>
 * Acts as a start page for Baudit providing access to Account Creation and Login.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
