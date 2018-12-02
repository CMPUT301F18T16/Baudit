package ca.klapstein.baudit.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.BodyLocationPhoto;
import ca.klapstein.baudit.presenters.EditPatientAccountPresenter;
import ca.klapstein.baudit.views.EditPatientAccountView;

/**
 * Activity for editing a {@code Account}.
 * <p>
 * Should be accessed by both a {@code Patient} and {@code CareProvider}.
 *
 * @see ca.klapstein.baudit.data.Patient
 * @see ca.klapstein.baudit.data.CareProvider
 */
public class EditPatientAccountActivity extends AppCompatActivity implements EditPatientAccountView {

    private EditPatientAccountPresenter presenter;

    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;
    private TextView emailErrorText;
    private EditText phoneNumberInput;
    private TextView phoneNumberErrorText;
    private LinearLayout bodyLocationsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient_account);
        Toolbar toolbar = findViewById(R.id.edit_patient_account_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.edit_account);

        presenter = new EditPatientAccountPresenter(this, getApplicationContext());

        firstNameInput = findViewById(R.id.edit_patient_account_first_name_input);
        lastNameInput = findViewById(R.id.edit_patient_account_last_name_input);
        emailInput = findViewById(R.id.edit_patient_account_email_input);
        emailErrorText = findViewById(R.id.edit_patient_account_email_error);

        phoneNumberInput = findViewById(R.id.edit_patient_account_phone_number_input);
        phoneNumberErrorText = findViewById(R.id.edit_patient_account_phone_number_error);

        bodyLocationsLayout = findViewById(R.id.edit_patient_account_body_locations_layout);

        Button cancelButton = findViewById(R.id.edit_patient_account_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button saveButton = findViewById(R.id.edit_patient_account_save_button);
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
    public void updateBodyLocationField(ArrayList<BodyLocationPhoto> photos) {
        bodyLocationsLayout.removeAllViews();

        int index = 0;
        for (BodyLocationPhoto photo : photos) {
            final int position = index;
            CardView bodyLocationView = (CardView) LayoutInflater
                .from(bodyLocationsLayout.getContext())
                .inflate(R.layout.card_body_location, bodyLocationsLayout, false);
            ImageView bodyLocationImage =
                bodyLocationView.findViewById(R.id.card_body_location_image);
            bodyLocationImage.setImageBitmap(photo.getPhoto());
            TextView bodyLocationLabel =
                bodyLocationView.findViewById(R.id.card_body_location_label);
            bodyLocationLabel.setText(photo.getLabel());

            bodyLocationView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(EditPatientAccountActivity.this)
                        .setTitle(R.string.delete_body_location_question)
                        .setCancelable(true)
                        .setNegativeButton(R.string.cancel, null)
                        .setPositiveButton(R.string.delete,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface di, int i) {
                                    presenter.deleteBodyLocationClicked(position);
                                }
                            })
                        .show();
                    return false;
                }
            });

            bodyLocationsLayout.addView(bodyLocationView);

            index++;
        }

        CardView addBodyLocationView = (CardView) LayoutInflater
            .from(bodyLocationsLayout.getContext())
            .inflate(R.layout.card_add_body_location, bodyLocationsLayout, false);

        addBodyLocationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Do something
            }
        });

        bodyLocationsLayout.addView(addBodyLocationView);
    }

    @Override
    public void commitAccountEditFailure() {
        Toast.makeText(
            this,
            getResources().getString(R.string.account_edit_commit_failure),
            Toast.LENGTH_LONG
        ).show();
    }

    @Override
    public void commitAccountEditSuccess() {
        Toast.makeText(
            this,
            getResources().getString(R.string.account_edit_commit_success),
            Toast.LENGTH_LONG
        ).show();
        finish();
    }
}
