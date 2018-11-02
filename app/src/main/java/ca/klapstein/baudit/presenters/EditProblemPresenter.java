package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.views.EditProblemView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Problem} via a {@code EditProblemView}.
 *
 * @see ca.klapstein.baudit.data.Problem
 * @see EditProblemView
 */
public class EditProblemPresenter extends ProblemPresenter<EditProblemView> {
    private static final String TAG = "EditProblemPresenter";

    public EditProblemPresenter(EditProblemView view) {
        super(view);
    }
}
