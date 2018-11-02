package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.BodyPhotoCoords;
import ca.klapstein.baudit.views.BodyLocationView;

public class BodyLocationPresenter extends Presenter<BodyLocationView> {

    private BodyPhotoCoords bodyPhotoCoords;

    public BodyLocationPresenter(BodyLocationView view) {
        super(view);
    }

    public boolean validateBodyLocation() {
        return true;
    }
}
