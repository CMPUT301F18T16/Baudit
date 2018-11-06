package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.views.EditAccountView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Account} via a {@code EditAccountView}.
 *
 * @see Account
 * @see EditAccountView
 */
public class EditAccountPresenter extends AccountPresenter<EditAccountView> {
    private static final String TAG = "EditAccountPresenter";

    public EditAccountPresenter(EditAccountView view) {
        super(view);
    }
}
