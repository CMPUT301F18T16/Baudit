package ca.klapstein.baudit.activities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.AddPhotoPresenter;
import ca.klapstein.baudit.views.AddPhotoView;
import ca.klapstein.baudit.widgets.CameraPreview;
import org.jetbrains.annotations.NotNull;


public class CameraActivity extends AppCompatActivity implements Camera.PictureCallback, AddPhotoView {
    private static final String TAG = "CameraActivity";

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 23;

    public static final String RECORD_PHOTO_FIELD = "recordPhoto";
    public static final String RECORD_PHOTO_RECORD_ID_FIELD = "recordId";
    public static final String RECORD_PHOTO_PROBLEM_ID_FIELD = "problemId";
    public static final String BODY_PHOTO_FIELD = "bodyPhoto";

    private AddPhotoPresenter presenter;

    private Camera camera;
    private CameraPreview cameraPreview;
    private int cameraId;
    private ImageView cameraOverlay;

    /**
     * Set the camera's orientation.
     *
     * @param activity {@code Activity}
     */
    public void setCameraOrientation(Activity activity) {
        android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
        camera.getParameters().setRotation(result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // request camera permission if not already given
        // TODO: move this to the start activity?
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

        Button capturePhotoButton = findViewById(R.id.capture_photo_button);
        capturePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCaptureClick();
            }
        });

        cameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
        openCamera();
        initCamera();

        presenter = new AddPhotoPresenter(this, this);

        if (getIntent().getBooleanExtra(RECORD_PHOTO_FIELD, false)) {
            presenter.getLastRecordPhoto(getIntent().getIntExtra(RECORD_PHOTO_PROBLEM_ID_FIELD, -1));
            Log.d(TAG, "starting CameraActivity for adding a record photo");
        } else if (getIntent().getBooleanExtra(BODY_PHOTO_FIELD, false)) {
            Log.d(TAG, "starting CameraActivity for adding a body photo");
        } else {
            Log.w(TAG, "unknown reason for starting CameraActivity: are you testing?");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Start your camera handling here
            } else {
                // TODO: put stuff here?
            }
        }
    }

    /**
     * Update the camera orientation on device configuration changes (i.e. rotation).
     *
     * @param newConfig {@code Configuration}
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (camera != null) {
            setCameraOrientation(this);
        }
    }

    /**
     * Show the camera view on the activity
     */
    private void initCamera() {
        if (camera != null) {
            cameraPreview.init(camera);
        } else {
            Toast.makeText(this, getResources().getText(R.string.camera_open_fail), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void onCaptureClick() {
        camera.takePicture(null, null, this);
    }

    /**
     * On taking a picture convert it to a {@code Bitmap} and send it to the presenter for either committing a
     * record or body photo.
     *
     * @param data   {@code byte[]}
     * @param camera {@code Camera}
     */
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Bitmap picture = BitmapFactory.decodeByteArray(data, 0, data.length);
        // TODO: additional Bitmap work
        if (getIntent().getBooleanExtra(RECORD_PHOTO_FIELD, false)) {
            presenter.commitRecordPhoto(picture, getIntent().getIntExtra(RECORD_PHOTO_PROBLEM_ID_FIELD, -1), getIntent().getIntExtra(RECORD_PHOTO_RECORD_ID_FIELD, -1));
        } else if (getIntent().getBooleanExtra(BODY_PHOTO_FIELD, false)) {
            presenter.commitBodyPhoto(picture);
        } else {
            Log.w(TAG, "unknown reason CameraActivity photo taken");
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera() {
        if (camera != null) {
            camera.release();
        }
    }

    /**
     * Attempt to open a camera with the specified cameraId.
     */
    private void openCamera() {
        try {
            releaseCamera();
            camera = Camera.open(cameraId);
            setCameraOrientation(this);
            cameraPreview.switchCamera(camera);
        } catch (Exception e) {
            Log.e(TAG, "failed to open Camera", e);
        }
    }

    /**
     * Switch between the front and rear facing cameras.
     */
    private void switchCamera() {
        // Camera may be in use by another activity or the system or not available at all
        if (cameraId == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            cameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
            openCamera();
        } else {
            cameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
            openCamera();
        }
    }

    @Override
    public void updateCameraOverlayImage(Bitmap bitmap) {
        // make the image more transparent
        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        // setAlpha is in 0-255 range
        drawable.setAlpha(177);
        cameraOverlay.setImageBitmap(drawable.getBitmap());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void updateCameraOverlayError() {
        Toast.makeText(this, getResources().getText(R.string.update_camera_overlay_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void commitPhotoSuccess() {
        Toast.makeText(this, getResources().getText(R.string.camera_image_commit_success), Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void commitPhotoFailure() {
        Toast.makeText(this, getResources().getText(R.string.camera_image_commit_failure), Toast.LENGTH_LONG).show();
    }
}
