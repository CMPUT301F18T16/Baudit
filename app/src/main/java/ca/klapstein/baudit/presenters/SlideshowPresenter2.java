package ca.klapstein.baudit.presenters;

import android.content.Context;

import java.util.ArrayList;

import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.views.SlideshowView2;

import static ca.klapstein.baudit.activities.CameraActivity.RECORD_PHOTO_PROBLEM_ID_FIELD;

public class SlideshowPresenter2 extends Presenter<SlideshowView2> {

    public SlideshowPresenter2(SlideshowView2 view, Context context) {
        super(view, context);
    }

    public ArrayList<String> getRecordBitmapStrings(int problemId, int recordId) {
        Patient patient = (Patient) dataManager.getLoggedInAccount();
        Problem problem = (Problem) patient.getProblemTreeSet().toArray()[problemId];
        Record record = (Record) problem.getRecordTreeSet().toArray()[recordId];
        return record.getPhotoBitmapStrings();
    }
}