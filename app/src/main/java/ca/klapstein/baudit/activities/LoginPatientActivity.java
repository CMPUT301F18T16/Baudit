package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.LoginPresenter;

/**
 * Activity presenting a login page for a Patient Baudit. Providing a page for the {@code Patient}
 * to login with their {@code Username} or {@code Email} and {@code Password} combination.
 *
 * @see ca.klapstein.baudit.data.Username
 * @see ca.klapstein.baudit.data.Email
 * @see ca.klapstein.baudit.data.Password
 * @see ca.klapstein.baudit.data.Patient
 */
public class LoginPatientActivity extends LoginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_patient);

        presenter = new LoginPresenter(this);
    }

    /**
     * Attempt a login as a {@code Patient}.
     *
     * @return {@code true} if the login was successful, otherwise return {@code false}.
     */
    public boolean login(){
        // TODO: implement
        return true;
    }

    /**
     * After logging in as a {@code Patient} start the {@code ProblemListActivity}.
     *
     * @see ProblemListActivity
     */
    public void onLogin() {
        Intent intent = new Intent(this, ProblemListActivity.class);
        startActivity(intent);
    }

    @Override
    public void setUserNameError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void setLoginSuccess() {

    }
}
