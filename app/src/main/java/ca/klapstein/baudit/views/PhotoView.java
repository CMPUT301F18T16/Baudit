package ca.klapstein.baudit.views;

import android.graphics.Bitmap;

public interface PhotoView extends View {
    void setPhoto(Bitmap bitmap);

    void setPhotoError();
}
