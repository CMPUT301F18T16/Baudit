package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import ca.klapstein.baudit.views.PhotoView;

public class PhotoPresenter extends Presenter<PhotoView> {

    public PhotoPresenter(PhotoView view, Context context) {
        super(view, context);
    }

    /**
     * Check if a given Bitmap image is valid for usage in Baudit.
     *
     * @param bitmap {@code Bitmap} the Bitmap image to validate
     * @return {@code boolean} {@code true} if the given bitmap image is valid, otherwise {@code false}
     */
    public boolean ValidatePhoto(Bitmap bitmap) {
        // TODO:
        return true;
    }
}
