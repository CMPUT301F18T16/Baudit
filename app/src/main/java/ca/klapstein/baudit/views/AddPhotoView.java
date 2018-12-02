package ca.klapstein.baudit.views;

import android.graphics.Bitmap;

public interface AddPhotoView extends View {
    void commitPhotoSuccess();

    void commitPhotoFailure();

    void updateCameraOverlayImage(Bitmap bitmap);

    void updateCameraOverlayError();
}
