package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.LoginPresenter;
import ca.klapstein.baudit.views.LoginView;

/**
 * Activity presenting a login page for a CareProvider Baudit. Providing a page for the {@code CareProvider}
 * to login with their {@code Username} or {@code Email} and {@code Password} combination.
 *
 * @see ca.klapstein.baudit.data.Username
 * @see ca.klapstein.baudit.data.Email
 * @see ca.klapstein.baudit.data.Password
 * @see ca.klapstein.baudit.data.CareProvider
 */
public class LoginCareProviderActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter presenter;

    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_care_provider);

        presenter = new LoginPresenter(this);

        usernameInput = findViewById(R.id.enter_care_provider_username);
        passwordInput = findViewById(R.id.enter_care_provider_password);

        Button loginButton = findViewById(R.id.login_care_provider_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginButtonClicked(
                    usernameInput.getText().toString(),
                    passwordInput.getText().toString()
                );
            }
        });

        Button registerButton = findViewById(R.id.register_care_provider_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CreateCareProviderAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button switchLoginButton = findViewById(R.id.log_in_as_patient_button);
        switchLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginPatientActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onLoginValidationSuccess() {
        Intent intent = new Intent(this, PatientListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginValidationFailure() {
        passwordInput.setText("");
        Toast.makeText(
            getBaseContext(),
            "Login failed: Invalid credentials",
            Toast.LENGTH_SHORT
        ).show();
    }
}
