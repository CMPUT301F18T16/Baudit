package ca.klapstein.baudit.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import ca.klapstein.baudit.R;

import java.util.Arrays;


public class CameraActivity extends AppCompatActivity implements Camera.PictureCallback {
    private static final String TAG = "CameraActivity";
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 23;
    private Camera camera;
    private CameraPreview cameraPreview;
    private int cameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
    private ImageView cameraOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
            }
        }
        cameraOverlay = findViewById(R.id.camera_overlay);
        cameraPreview = findViewById(R.id.camera_preview);

        Button switchCameraButton = findViewById(R.id.switch_camera_button);
        switchCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCamera();
            }
        });

        setResult(RESULT_CANCELED);

        openCamera(cameraId);

        if (camera != null) {
            initCameraPreview();
        } else {
            Toast.makeText(this, getResources().getText(R.string.camera_open_fail), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Start your camera handling here
                } else {
                }
        }
    }

    private void releaseCameraAndPreview() {
        cameraPreview.init(null);
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    // Show the camera view on the activity
    private void initCameraPreview() {
        cameraPreview.init(camera);
    }

    public void onCaptureClick(View button) {
        // Take a picture with a callback when the photo has been created
        // Here you can add callbacks if you want to give feedback when the picture is being taken
        camera.takePicture(null, null, this);
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Bitmap picture = BitmapFactory.decodeByteArray(data, 0, data.length);
        // TODO: do stuff with bitmap
        Log.d(TAG, "Picture taken raw data:" + Arrays.toString(data));
        finish();
    }

    // ALWAYS remember to release the camera when you are finished
    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    private void openFrontCamera() {
        cameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
        openCamera(cameraId);
    }

    private void openBackCamera() {
        cameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
        openCamera(cameraId);
    }

    private void openCamera(int cameraId) {
        try {
            releaseCameraAndPreview();
            camera = Camera.open(cameraId);
        } catch (Exception e) {
            Log.e(TAG, "failed to open Camera", e);
        }
    }

    private void switchCamera() {
        // Camera may be in use by another activity or the system or not available at all
        if (cameraId == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            openBackCamera();
        } else {
            openFrontCamera();
        }
    }
}
