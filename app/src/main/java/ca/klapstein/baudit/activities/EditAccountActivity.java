package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.EditAccountPresenter;
import ca.klapstein.baudit.views.EditAccountView;

/**
 * Activity for editing a {@code Account}.
 * <p>
 * Should be accessed by both a {@code Patient} and {@code CareProvider}.
 *
 * @see ca.klapstein.baudit.data.Patient
 * @see ca.klapstein.baudit.data.CareProvider
 */
public class EditAccountActivity extends AppCompatActivity implements EditAccountView {

    private EditAccountPresenter presenter;

    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;
    private TextView emailErrorText;
    private EditText phoneNumberInput;
    private TextView phoneNumberErrorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        Toolbar toolbar = findViewById(R.id.edit_account_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.edit_account);

        presenter = new EditAccountPresenter(this, getApplicationContext());

        firstNameInput = findViewById(R.id.edit_account_first_name_input);
        lastNameInput = findViewById(R.id.edit_account_last_name_input);
        emailInput = findViewById(R.id.edit_account_email_input);
        emailErrorText = findViewById(R.id.edit_account_email_error);

        phoneNumberInput = findViewById(R.id.edit_account_phone_number_input);
        phoneNumberErrorText = findViewById(R.id.edit_account_phone_number_error);

        Button cancelButton = findViewById(R.id.edit_account_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button saveButton = findViewById(R.id.edit_account_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveClicked(
                        firstNameInput.getText().toString(),
                        lastNameInput.getText().toString(),
                    emailInput.getText().toString(),
                    phoneNumberInput.getText().toString()
                );
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted();
    }

    @Override
    public void updateFirstNameField(String firstName) {
        firstNameInput.setText(firstName);
    }

    @Override
    public void updateLastNameField(String lastName) {
        lastNameInput.setText(lastName);
    }

    @Override
    public void updateEmailField(String email) {
        emailInput.setText(email);
    }

    @Override
    public void updatePhoneNumberField(String phoneNumber) {
        phoneNumberInput.setText(phoneNumber);
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
    public void commitAccountEditFailure() {
        Toast.makeText(this, getResources().getString(R.string.account_edit_commit_failure), Toast.LENGTH_LONG).show();
    }

    @Override
    public void commitAccountEditSuccess() {
        Toast.makeText(this, getResources().getString(R.string.account_edit_commit_success), Toast.LENGTH_LONG).show();
        finish();
    }
}
