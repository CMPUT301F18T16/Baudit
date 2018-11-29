package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.StartPresenter;
import ca.klapstein.baudit.views.StartView;

/**
 * Template activity for a "login" screen for Baudit.
 * <p>
 * This activity should not be run. It simply provides code duplication mitigation for scanning QR
 * functionality.
 */
public class StartActivity extends AppCompatActivity implements StartView {
    private static final String TAG = "StartActivity";

    private static final int REQUEST_CODE_QR_SCAN = 101;

    protected StartPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        presenter = new StartPresenter(this, getApplicationContext());

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

    /**
     * On a login validation failure create a short toast message notifying
     * the reason of the failure.
     *
     * @param message {@code String} message noting the reason of the failure
     */
    @Override
    public void onLoginValidationFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * On a login validation success proceed and start the appropriate home activity.
     *
     * @param homeClass
     */
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
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes
            startActivityForResult(intent, REQUEST_CODE_QR_SCAN);
        } catch (Exception e) {
            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            startActivity(marketIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_QR_SCAN) {

            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
                Log.e(TAG, "obtained qr code decoded string: " + contents);
                presenter.onQRCodeScanned(contents);
            } else if (resultCode == RESULT_CANCELED) {
                //handle cancel
            }
        }
    }

    @Override
    public void startRegistration() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }
}
