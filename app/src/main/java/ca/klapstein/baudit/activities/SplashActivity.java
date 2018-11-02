package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Starting Activity for Baudit.
 * <p>
 * Acts as a start page for Baudit providing access to Account Creation and Login.
 */
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void onCreateCareProviderAccountClick() {
        Intent intent = new Intent(this, CreateCareProviderAccountActivity.class);
        startActivity(intent);
        finish();
    }

    private void onPatientAccountClick() {
        Intent intent = new Intent(this, CreatePatientAccountActivity.class);
        startActivity(intent);
        finish();
    }

    private void onCareProviderLoginClick() {
        Intent intent = new Intent(this, LoginCareProviderActivity.class);
        startActivity(intent);
        finish();
    }

    private void onPatientLoginClick() {
        Intent intent = new Intent(this, LoginPatientActivity.class);
        startActivity(intent);
        finish();
    }
}
