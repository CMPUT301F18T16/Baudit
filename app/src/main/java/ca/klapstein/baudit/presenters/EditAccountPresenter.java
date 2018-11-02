package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.views.EditAccountView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code User} via a {@code EditAccountView}.
 *
 * @see ca.klapstein.baudit.data.User
 * @see EditAccountView
 */
public class EditAccountPresenter extends AccountPresenter<EditAccountView> {
    private static final String TAG = "EditAccountPresenter";

    public EditAccountPresenter(EditAccountView view) {
        super(view);
    }
}
