package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.views.ViewAccountView;

public class ViewAccountPresenter extends Presenter<ViewAccountView> {
    private static final String TAG = "ViewAccountPresenter";

    public ViewAccountPresenter(ViewAccountView view, Context context) {
        super(view, context);
    }
}
