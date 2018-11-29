package ca.klapstein.baudit.views;

import android.graphics.Bitmap;

public interface PhotoView extends View {
    void updatePhotoImage(Bitmap bitmap);

    void updatePhotoError();
}
