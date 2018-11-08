package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.events.PatientLogInButtonClicked;
import ca.klapstein.baudit.events.PatientSignUpButtonClicked;
import ca.klapstein.baudit.events.NotifyLogInFailed;
import ca.klapstein.baudit.events.NotifyLogInSucceeded;
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

    private final EventBus bus = EventBus.getDefault();
    private LoginPresenter presenter;

    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_patient);

        presenter = new LoginPresenter(this, bus);

        usernameInput = findViewById(R.id.enterUsername);
        passwordInput = findViewById(R.id.enterPassword);

        Button logInButton = findViewById(R.id.loginButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bus.post(new PatientLogInButtonClicked(
                    usernameInput.getText().toString(),
                    passwordInput.getText().toString()
                ));
            }
        });

        Button signUpButton = findViewById(R.id.registerButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bus.post(new PatientSignUpButtonClicked());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    @Subscribe @Override
    public void onLogInSuccess(NotifyLogInSucceeded event) {
        Intent intent = new Intent(this, ProblemListActivity.class);
        startActivity(intent);
        finish();
    }

    @Subscribe @Override
    public void onLogInFailure(NotifyLogInFailed event) {
        // TODO: Do something!
    }
}
