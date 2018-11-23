package ca.klapstein.baudit.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.LoginPresenter;
import ca.klapstein.baudit.views.LoginView;
import com.blikoon.qrcodescanner.QrCodeActivity;

/**
 * Activity presenting a login page for a Patient Baudit.
 *
 * @see ca.klapstein.baudit.data.Username
 * @see ca.klapstein.baudit.data.Email
 * @see ca.klapstein.baudit.data.Patient
 */
public class LoginPatientActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter presenter;
    private EditText usernameInput;
    private EditText passwordInput;
    private TextView errorText;
    private static final String TAG = "LoginPatient";
    private static final int REQUEST_CODE_QR_SCAN = 101;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onLoginValidationSuccess() {
        startActivity(new Intent(LoginPatientActivity.this, PatientHomeActivity.class));
        finish();
    }

    @Override
    public void onLoginValidationFailure(String message) {
        passwordInput.setText("");
        errorText.setText(message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_patient);

        presenter = new LoginPresenter(this, this);

        usernameInput = findViewById(R.id.enter_patient_username);
        passwordInput = findViewById(R.id.enter_patient_password);

        errorText = findViewById(R.id.patient_login_error_text);

        Button loginButton = findViewById(R.id.login_patient_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginButtonClicked(
                    usernameInput.getText().toString(),
                    passwordInput.getText().toString()
                );
            }
        });

        Button registerButton = findViewById(R.id.register_patient_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                    LoginPatientActivity.this,
                    CreatePatientAccountActivity.class
                );
                startActivity(intent);
            }
        });

        Button switchLoginButton = findViewById(R.id.log_in_as_care_provider_button);
        switchLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchLoginScreen();
            }
        });

        Button scanQRCodeButton = findViewById(R.id.scan_qr_code_button);
        scanQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanQRCode();
            }
        });
    }

    /**
     * Switch to the {@code CareProvider} login screen {@code LoginCareProviderActivity}.
     *
     * @see LoginCareProviderActivity
     */
    @Override
    public void switchLoginScreen() {
        startActivity(new Intent(LoginPatientActivity.this, LoginCareProviderActivity.class));
        finish();
    }

    /**
     * start an activity to start the camera and scan a QR code.
     */
    @Override
    public void startScanQRCode() {
        Intent i = new Intent(this, QrCodeActivity.class);
        startActivityForResult(i, REQUEST_CODE_QR_SCAN);
    }

    // TODO: dedupe between login screens
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            Log.d(TAG, "COULD NOT GET A GOOD RESULT.");
            if (data == null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (result != null) {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }

        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (data == null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            Log.d(TAG, "Have scan result in your app activity :" + result);
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Scan result");
            alertDialog.setMessage(result);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            // TODO: do additional login/authentication work.
        }
    }
}

