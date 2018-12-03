package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.data.RecordTreeSet;
import ca.klapstein.baudit.views.SlideshowView;

/**
 * MVP presenter for presenting a series of images on a {@code SlideshowView}.
 *
 * @see SlideshowView
 */
public class SlideshowPresenter extends Presenter<SlideshowView> {

    private Patient patient;

    public SlideshowPresenter(@NonNull SlideshowView view, @NonNull Context context) {
        super(view, context);
        patient = (Patient) dataManager.getLoggedInAccount();
    }

    public void viewStarted() {
        if (patient != null) {
            ArrayList<Bitmap> photos = new ArrayList<>();

            ProblemTreeSet patientProblems = patient.getProblemTreeSet();
            for (Problem problem : patientProblems) {
                RecordTreeSet problemRecords = problem.getRecordTreeSet();
                for (Record record : problemRecords) {
                    photos.addAll(record.getRecordPhotos());
                }
            }

            view.updateImageList(photos);
        } else {
            // TODO: Handle error
        }
    }
}
