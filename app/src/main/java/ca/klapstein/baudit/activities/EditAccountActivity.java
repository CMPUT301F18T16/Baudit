package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText emailInput;
    private EditText phoneNumberInput;
    private EditText firstNameInput;
    private EditText lastNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        Toolbar toolbar = findViewById(R.id.edit_account_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.edit_account);

        presenter = new EditAccountPresenter(this, getApplicationContext());

        firstNameInput = findViewById(R.id.edit_account_first_name_input);
        lastNameInput = findViewById(R.id.edit_account_last_name_input);
        emailInput = findViewById(R.id.edit_account_email_input);
        phoneNumberInput = findViewById(R.id.edit_account_phone_number_input);

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

        // TODO: possibly move this into a better menu
        Button getAccountQRCodeButton = findViewById(R.id.account_qr_code_button);
        getAccountQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditAccountActivity.this, DisplayQRCodeActivity.class));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted();
    }

    @Override
    public void setFirstName(String firstName) {
        firstNameInput.setText(firstName);

    }

    @Override
    public void setLastName(String lastName) {
        lastNameInput.setText(lastName);
    }

    @Override
    public void setEmail(String email) {
        emailInput.setText(email);

    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        phoneNumberInput.setText(phoneNumber);
    }

    @Override
    public void commitAccountEditFailure() {
        Toast.makeText(this, "Failed to Commit Account Edits", Toast.LENGTH_LONG).show();
    }

    @Override
    public void commitAccountEditSuccess() {
        Toast.makeText(this, "Successfully Committed Account Edits", Toast.LENGTH_LONG).show();
        finish();
    }
}
