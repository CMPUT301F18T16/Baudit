package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.views.EditProblemView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Problem} via a {@code EditProblemView}.
 *
 * @see ca.klapstein.baudit.data.Problem
 * @see EditProblemView
 */
public class EditProblemPresenter extends ProblemPresenter<EditProblemView> {

    public EditProblemPresenter(EditProblemView view) {
        super(view);
    }

    public void clickedDateButton() {
        // TODO: Get date from db and convert to calendar
    }

    public void clickedTimeButton() {
        // TODO: Get time from db and convert to calendar
    }
}
