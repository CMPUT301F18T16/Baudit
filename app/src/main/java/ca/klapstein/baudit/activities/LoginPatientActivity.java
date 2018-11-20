package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.LoginPresenter;
import ca.klapstein.baudit.views.LoginView;

/**
 * Activity presenting a login page for a Patient Baudit. Providing a page for the {@code Patient}
 * to login with their {@code Username} or {@code Email} and {@code Password} combination.
 *
 * @see ca.klapstein.baudit.data.Username
 * @see ca.klapstein.baudit.data.Email
 * @see ca.klapstein.baudit.data.Password
 * @see ca.klapstein.baudit.data.Patient
 */
public class LoginPatientActivity extends AppCompatActivity implements LoginView {

    private static final String TAG = "LoginPatientActivity";
    private LoginPresenter presenter;

    private EditText usernameInput;
    private EditText passwordInput;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_patient);

        presenter = new LoginPresenter(this, this);

        usernameInput = findViewById(R.id.enter_patient_username);
        passwordInput = findViewById(R.id.enter_patient_password);

        errorText = findViewById(R.id.patient_login_error_text);

        Button loginButton = findViewById(R.id.login_patient_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginButtonClicked(
                    usernameInput.getText().toString(),
                    passwordInput.getText().toString()
                );
            }
        });

        Button registerButton = findViewById(R.id.register_patient_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                    LoginPatientActivity.this,
                    CreatePatientAccountActivity.class
                );
                startActivity(intent);
            }
        });

        Button switchLoginButton = findViewById(R.id.log_in_as_care_provider_button);
        switchLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchLoginScreen();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onLoginValidationSuccess() {
        Intent intent = new Intent(
            this,
            PatientHomeActivity.class
        );
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginValidationFailure() {
        passwordInput.setText("");
        errorText.setText(getResources().getString(R.string.login_failed));
    }

    @Override
    public void switchLoginScreen() {
        Intent intent = new Intent(
                LoginPatientActivity.this,
                LoginCareProviderActivity.class
        );
        startActivity(intent);
        finish();
    }
}
