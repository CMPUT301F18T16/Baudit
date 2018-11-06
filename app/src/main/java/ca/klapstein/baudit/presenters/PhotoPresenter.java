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

    /**
     * Check if a given Bitmap image is valid for usage in Baudit.
     *
     * @param bitmap {@code Bitmap} the Bitmap image to validate
     * @return {@code boolean} {@code true} if the given bitmap image is valid, otherwise {@code false}
     */
    public boolean ValidatePhoto(Bitmap bitmap) {
        return true;
    }
}
