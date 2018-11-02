package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.views.DeleteView;

public class DeleteRecordPresenter extends Presenter<DeleteView> {
    private static final String TAG = "DeleteRecordPresenter";

    public DeleteRecordPresenter(DeleteView view) {
        super(view);
    }

    public void notifyClickedDelete(final int position) {

    }
}
