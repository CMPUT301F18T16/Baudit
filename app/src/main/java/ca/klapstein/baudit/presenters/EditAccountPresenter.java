package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.User;
import ca.klapstein.baudit.views.EditAccountView;

public class EditAccountPresenter {
    private static final String TAG = "EditAccountPresenter";

    private User user;
    private EditAccountView view;

    public EditAccountPresenter(EditAccountView view) {
        this.view = view;
    }
}
