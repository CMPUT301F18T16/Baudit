package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.views.AddProblemView;

/**
 * MVP presenter for presenting and controlling the addition of a {@code Problem} via a {@code AddProblemView}.
 *
 * @see ca.klapstein.baudit.data.Problem
 * @see AddProblemView
 */
public class AddProblemPresenter extends ProblemPresenter<AddProblemView> {
    private static final String TAG = "AddProblemPresenter";

    public AddProblemPresenter(AddProblemView view, Context context) {
        super(view, context);
    }
}
