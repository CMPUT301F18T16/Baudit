package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.views.RecordView;

abstract public class RecordPresenter<T extends RecordView> {
    protected Record record;
    private T view;

    RecordPresenter(T view) {
        this.view = view;
    }
}
