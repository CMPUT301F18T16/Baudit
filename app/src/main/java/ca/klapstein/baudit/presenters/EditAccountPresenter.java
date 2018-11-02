package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.User;
import ca.klapstein.baudit.views.EditAccountView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code User} via a {@code EditAccountView}.
 *
 * @see ca.klapstein.baudit.data.User
 * @see EditAccountView
 */
public class EditAccountPresenter {
    private static final String TAG = "EditAccountPresenter";

    private User user;
    private EditAccountView view;

    public EditAccountPresenter(EditAccountView view) {
        this.view = view;
    }
}
