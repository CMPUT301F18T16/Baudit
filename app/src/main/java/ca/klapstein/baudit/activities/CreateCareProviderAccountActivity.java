package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.CreateCareProviderAccountPresenter;
import ca.klapstein.baudit.views.CreateCareProviderAccountView;

/**
 * Activity providing the inputs and validations for creating a
 * valid {@code CareProvider} Account for Baudit.
 *
 * @see ca.klapstein.baudit.data.CareProvider
 */
public class CreateCareProviderAccountActivity extends AppCompatActivity
    implements CreateCareProviderAccountView {

    private CreateCareProviderAccountPresenter presenter;
    private EditText id;
    private EditText firstname;
    private EditText lastname;
    private EditText username;
    private EditText email;
    private EditText phonenumber;
    private EditText password;
    private EditText confirmpassword;
    private TextView errorText;

    private TextView create_careprovider_id_error;
    private TextView create_carepovider_username_error;
    private TextView create_careprovider_email_error;
    private TextView create_carepovider_phonenumber_error;
    private TextView create_careprovider_password_error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_care_provider_account);

        presenter = new CreateCareProviderAccountPresenter(this, getApplicationContext());

        id = findViewById(R.id.create_careprovider_id);
        firstname = findViewById(R.id.create_careprovider_firstname);
        lastname = findViewById(R.id.create_careprovider_lastName);
        username= findViewById(R.id.create_careprovider_username);
        email = findViewById(R.id.create_careprovider_email);
        phonenumber = findViewById(R.id.create_careprovider_phone);
        password = findViewById(R.id.create_careprovider_password);
        confirmpassword =findViewById(R.id.create_careprovider_confirmpassword);


        create_careprovider_id_error =findViewById(R.id.create_careprovider_id_error);
        create_carepovider_username_error = findViewById(R.id.create_careprovider_username_error);
        create_careprovider_email_error = findViewById(R.id.create_careprovider_email_error);
        create_carepovider_phonenumber_error = findViewById(R.id.create_careprovider_phonenumber_error);
        create_careprovider_password_error = findViewById(R.id.create_careprovider_password_error);


        Button verifyButton = findViewById(R.id.create_careprovider_verify_button);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.verifyButtonClicked(
                        id.getText().toString(),
                        firstname.getText().toString(),
                        lastname.getText().toString(),
                        username.getText().toString(),
                        email.getText().toString(),
                        phonenumber.getText().toString(),
                        password.getText().toString(),
                        confirmpassword.getText().toString()
                );
            }
        });

        Button cancelButton = findViewById(R.id.create_careprovider_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        CreateCareProviderAccountActivity.this,
                        LoginCareProviderActivity.class
                ));
            }
        });
    }







    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * Displays an error message if the inputted username is invalid.
     *
     * @param message {@code String}
     */
    @Override
    public void updateUsernameError(String message) {
        create_carepovider_username_error.setText(message);
        // TODO: Set error message to text view
    }

    /**
     * Displays an error message if the inputted email is invalid.
     *
     * @param message {@code String}
     */
    @Override
    public void updateEmailError(String message) {
        create_careprovider_email_error.setText(message);
        // TODO: Set error message to text view
    }

    /**
     * Displays an error message if the inputted phone number is invalid.
     *
     * @param message {@code String}
     */
    @Override
    public void updatePhoneNumberError(String message) {
        create_carepovider_phonenumber_error.setText(message);
        // TODO: Set error message to text view
    }

    /**
     * Displays an error message if the inputted password(s) is/are invalid.
     *
     * @param message {@code String}
     */
    @Override
    public void updatePasswordError(String message) {
        create_careprovider_password_error.setText(message);
        // TODO: Set error message to text view
    }

    /**
     * Displays an error message if the inputted MINC ID is invalid.
     *
     * @param message {@code String}
     */
    @Override
    public void updateCareProviderIdError(String message) {
        create_careprovider_id_error.setText(message);
        // TODO: Set error message to text view
    }

    /**
     * Responds to account confirmation by opening the new care provider account's home screen.
     */
    @Override
    public void onAccountConfirmed() {
        startActivity(new Intent(
                CreateCareProviderAccountActivity.this,
                CareProviderHomeActivity.class
        ));
        finish();
    }
}
