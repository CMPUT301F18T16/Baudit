package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.views.ViewAccountView;

public class ViewAccountPresenter extends AccountPresenter<ViewAccountView> {
    private static final String TAG = "ViewAccountPresenter";

    public ViewAccountPresenter(ViewAccountView view) {
        super(view);
    }
}
