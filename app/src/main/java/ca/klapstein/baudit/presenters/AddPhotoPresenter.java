package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import ca.klapstein.baudit.views.AddPhotoView;

public class AddPhotoPresenter extends Presenter<AddPhotoView> {

    public AddPhotoPresenter(AddPhotoView view, Context context) {
        super(view, context);
    }

    public boolean ValidatePhoto(Bitmap bitmap) {
        // TODO:
        if (bitmap != null) {
            Bitmap emptyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
            if (bitmap != emptyBitmap)
                return true;
        }
        return false;
    }
}
