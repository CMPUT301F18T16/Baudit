package ca.klapstein.baudit.presenters;

import android.graphics.Bitmap;
import ca.klapstein.baudit.data.Photo;
import ca.klapstein.baudit.views.AddPhotoView;

public class AddPhotoPresenter extends Presenter<AddPhotoView> {
    private static final String TAG = "AddPhotoPresenter";

    public Photo photo;

    public AddPhotoPresenter(AddPhotoView view) {
        super(view);
    }

    public boolean ValidatePhoto(Bitmap bitmap) {
        return true;
    }
}
