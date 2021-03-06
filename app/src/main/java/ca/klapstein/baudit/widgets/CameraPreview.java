package ca.klapstein.baudit.widgets;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "CameraPreview";
    private Camera camera;
    private SurfaceHolder holder;

    public CameraPreview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraPreview(Context context) {
        super(context);
    }

    public void init(Camera camera) {
        this.camera = camera;
        initSurfaceHolder();
    }

    private void initSurfaceHolder() {
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initCamera(holder);
    }

    private void initCamera(SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (Exception e) {
            Log.e(TAG, "Error setting camera preview", e);
        }
    }

    public void switchCamera(Camera camera) {
        this.camera = camera;
        initCamera(getHolder());
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // not-used
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // not-used
    }
}
