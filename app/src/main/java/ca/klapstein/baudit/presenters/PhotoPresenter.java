package ca.klapstein.baudit.presenters;

import android.graphics.Bitmap;
import ca.klapstein.baudit.data.Photo;
import ca.klapstein.baudit.views.PhotoView;

public class PhotoPresenter extends Presenter<PhotoView> {
    private static final String TAG = "PhotoPresenter";

    public Photo photo;

    public PhotoPresenter(PhotoView view) {
        super(view);
    }

    public boolean ValidatePhoto(Bitmap bitmap) {
        return true;
    }
}