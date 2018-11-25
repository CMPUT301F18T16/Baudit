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

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.StartPresenter;
import ca.klapstein.baudit.views.StartView;

import com.blikoon.qrcodescanner.QrCodeActivity;

/**
 * Template activity for a "login" screen for Baudit.
 * <p>
 * This activity should not be run. It simply provides code duplication mitigation for scanning QR
 * functionality.
 */
public class StartActivity extends AppCompatActivity implements StartView {

    private static final int REQUEST_CODE_QR_SCAN = 101;
    private static final String TAG = "StartActivity";

    protected StartPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button registerButton = findViewById(R.id.register_account_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegistration();
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

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onLoginValidationFailure(String message) {
        // TODO: Discuss what should happen here
    }

    @Override
    public void onLoginValidationSuccess(Class homeClass) {
        startActivity(new Intent(getApplicationContext(), homeClass));
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

    /**
     * On return the QR activities result.
     * <p>
     * TODO: implement
     *
     * @param requestCode {@code int}
     * @param resultCode {@code int}
     * @param data {@code Intent}
     */
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

    @Override
    public void startRegistration() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }
}
