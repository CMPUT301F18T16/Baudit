package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.BodyPhotoCoords;
import ca.klapstein.baudit.views.BodyLocationView;

public class BodyLocationPresenter extends Presenter<BodyLocationView> {
    private static final String TAG = "BodyLocationPresenter";

    private BodyPhotoCoords bodyPhotoCoords;

    public BodyLocationPresenter(BodyLocationView view, Context context) {
        super(view, context);
    }


    public boolean validateBodyLocation() {
        return true;
    }
}
