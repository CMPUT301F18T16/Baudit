package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.views.BodyLocationView;

public class BodyLocationPresenter extends Presenter<BodyLocationView> {

    public BodyLocationPresenter(BodyLocationView view, Context context) {
        super(view, context);
    }

    public boolean validateBodyLocation() {
        // TODO:
        return true;
    }
}
