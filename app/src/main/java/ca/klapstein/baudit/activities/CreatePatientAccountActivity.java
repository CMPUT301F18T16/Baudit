package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.CreatePatientAccountPresenter;
import ca.klapstein.baudit.views.CreateAccountView;

/**
 * Activity providing the inputs and validations for creating a
 * valid {@code Patient} Account for Baudit.
 *
 * @see ca.klapstein.baudit.data.Patient
 */
public class CreatePatientAccountActivity extends AppCompatActivity
    implements CreateAccountView {

    private CreatePatientAccountPresenter presenter;
    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText usernameInput;
    private TextView usernameErrorText;
    private EditText emailInput;
    private TextView emailErrorText;
    private EditText phoneNumberInput;
    private TextView phoneNumberErrorText;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private TextView passwordErrorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient_account);
        Toolbar toolbar = findViewById(R.id.create_patient_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.create_account);

        presenter = new CreatePatientAccountPresenter(this, getApplicationContext());

        firstNameInput = findViewById(R.id.create_patient_first_name_input);
        lastNameInput = findViewById(R.id.create_patient_last_name_input);

        usernameInput = findViewById(R.id.create_patient_username_input);
        usernameErrorText = findViewById(R.id.create_patient_username_error);

        emailInput = findViewById(R.id.create_patient_email_input);
        emailErrorText = findViewById(R.id.create_patient_email_error);

        phoneNumberInput = findViewById(R.id.create_patient_phone_number_input);
        phoneNumberErrorText = findViewById(R.id.create_patient_phone_number_error);

        passwordInput = findViewById(R.id.create_patient_password_input);
        confirmPasswordInput = findViewById(R.id.create_patient_confirm_password_input);
        passwordErrorText = findViewById(R.id.create_patient_password_error);

        Button uploadButton = findViewById(R.id.create_patient_body_location_upload_button);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Work with this
            }
        });

        Button cancelButton = findViewById(R.id.create_patient_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button confirmButton = findViewById(R.id.create_patient_confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.confirmButtonClicked(
                    firstNameInput.getText().toString(),
                    lastNameInput.getText().toString(),
                    usernameInput.getText().toString(),
                    emailInput.getText().toString(),
                    phoneNumberInput.getText().toString(),
                    passwordInput.getText().toString(),
                    confirmPasswordInput.getText().toString()
                );
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void updateUsernameError(String message) {
        usernameErrorText.setText(message);
    }

    @Override
    public void updateEmailError(String message) {
        emailErrorText.setText(message);
    }

    @Override
    public void updatePhoneNumberError(String message) {
        phoneNumberErrorText.setText(message);
    }

    @Override
    public void updatePasswordError(String message) {
        passwordErrorText.setText(message);
    }

    @Override
    public void onAccountConfirmed() {
        startActivity(new Intent(
            CreatePatientAccountActivity.this,
            PatientHomeActivity.class
        ));
        finish();
    }
}
