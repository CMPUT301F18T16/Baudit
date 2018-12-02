package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.CreateAccountPresenter;
import ca.klapstein.baudit.views.CreateAccountView;

/**
 * Activity providing the inputs and validations for creating a valid {@code Account} for Baudit.
 *
 * @see ca.klapstein.baudit.data.Account
 */
public class CreateAccountActivity extends AppCompatActivity implements CreateAccountView {

    private CreateAccountPresenter presenter;
    private EditText identificationMincInput;
    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText usernameInput;
    private TextView usernameErrorText;
    private EditText emailInput;
    private TextView emailErrorText;
    private EditText phoneNumberInput;
    private TextView phoneNumberErrorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = findViewById(R.id.create_account_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.create_account);

        presenter = new CreateAccountPresenter(this, getApplicationContext());

        final RadioGroup accountTypeRadioGroup = findViewById(R.id.account_type_radio_group);

        final TextView identificationMincText = findViewById(R.id.create_account_minc_text);
        identificationMincInput = findViewById(R.id.create_account_minc_input);

        RadioButton patientRadioButton = findViewById(R.id.patient_radio_button);
        patientRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    identificationMincText.setVisibility(View.GONE);
                    identificationMincInput.setVisibility(View.GONE);
                }
            }
        });

        RadioButton careProviderRadioButton = findViewById(R.id.care_provider_radio_button);
        careProviderRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    identificationMincText.setVisibility(View.VISIBLE);
                    identificationMincInput.setVisibility(View.VISIBLE);
                }
            }
        });

        firstNameInput = findViewById(R.id.create_account_first_name_input);
        lastNameInput = findViewById(R.id.create_account_last_name_input);

        usernameInput = findViewById(R.id.create_account_username_input);
        usernameErrorText = findViewById(R.id.create_account_username_error);

        emailInput = findViewById(R.id.create_account_email_input);
        emailErrorText = findViewById(R.id.create_account_email_error);

        phoneNumberInput = findViewById(R.id.create_account_phone_number_input);
        phoneNumberErrorText = findViewById(R.id.create_account_phone_number_error);

        Button cancelButton = findViewById(R.id.create_account_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button confirmButton = findViewById(R.id.create_account_confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.confirmButtonClicked(
                    accountTypeRadioGroup.getCheckedRadioButtonId(),
                    firstNameInput.getText().toString(),
                    lastNameInput.getText().toString(),
                    usernameInput.getText().toString(),
                    emailInput.getText().toString(),
                    phoneNumberInput.getText().toString()
                );
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
        usernameErrorText.setText(message);
    }

    /**
     * Displays an error message if the inputted email is invalid.
     *
     * @param message {@code String}
     */
    @Override
    public void updateEmailError(String message) {
        emailErrorText.setText(message);
    }

    /**
     * Displays an error message if the inputted phone number is invalid.
     *
     * @param message {@code String}
     */
    @Override
    public void updatePhoneNumberError(String message) {
        phoneNumberErrorText.setText(message);
    }

    /**
     * Responds to account confirmation by opening the account's home screen.
     */
    @Override
    public void onAccountConfirmed(Class homeClass) {
        startActivity(new Intent(CreateAccountActivity.this, homeClass));
        finish();
    }
}
