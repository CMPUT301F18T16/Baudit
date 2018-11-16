package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.views.DeleteView;

public class DeleteProblemPresenter extends Presenter<DeleteView> {
    private static final String TAG = "DeleteProblemPresenter";

    public DeleteProblemPresenter(DeleteView view, Context context) {
        super(view, context);
    }


    public void notifyClickedDelete(final int position) {

    }
}
