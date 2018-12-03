package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.views.SlideShowView;

import java.util.ArrayList;

public class SlideShowPresenter extends Presenter<SlideShowView> {

    private static final String TAG = "SlideShowPresenter";

    public SlideShowPresenter(SlideShowView view, Context context) {
        super(view, context);
    }

    public ArrayList<Bitmap> getRecordPhotoBitmaps(int problemId, int recordId) {
        try {
            Patient patient = dataManager.getLoggedInPatient();
            Problem problem = (Problem) patient.getProblemTreeSet().toArray()[problemId];
            Record record = (Record) problem.getRecordTreeSet().toArray()[recordId];
            return record.getRecordPhotos();
        } catch (Exception e) {
            Log.e(TAG, "failed to present record photo slideshow", e);
            view.updateViewSlideShowError();
            return new ArrayList<>();
        }
    }
}