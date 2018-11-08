package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_patient);

        presenter = new LoginPresenter(this);

        final EditText usernameInput = findViewById(R.id.enterUsername);
        final EditText passwordInput = findViewById(R.id.enterPassword);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginButtonClicked(
                    usernameInput.getText().toString(),
                    passwordInput.getText().toString()
                );
            }
        });

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CreatePatientAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void loginValidationSuccess() {
        Intent intent = new Intent(this, ProblemListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginValidationFailure() {
        // TODO: Implement
    }
}
