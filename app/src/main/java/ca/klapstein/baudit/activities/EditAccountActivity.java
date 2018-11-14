package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    private EditText nameInput;
    private EditText emailInput;
    private EditText phoneNumberInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        Toolbar toolbar = findViewById(R.id.edit_account_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.edit_account);

        presenter = new EditAccountPresenter(this);

        nameInput = findViewById(R.id.edit_account_name_input);
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
                    nameInput.getText().toString(),
                    emailInput.getText().toString(),
                    phoneNumberInput.getText().toString()
                );
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted();
    }

    @Override
    public void updateFields(String name, String email, String phoneNumber) {
        nameInput.setText(name);
        emailInput.setText(email);
        phoneNumberInput.setText(phoneNumber);
    }
}
