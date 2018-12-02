package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.views.AddPhotoView;

public class AddPhotoPresenter extends Presenter<AddPhotoView> {

    private static final String TAG = "AddPhotoPresenter";
    private Patient patient;

    public AddPhotoPresenter(AddPhotoView view, Context context) {
        super(view, context);
        patient = dataManager.getLoggedInPatient();
    }

    public void getLastRecordPhoto(int problemId) {
        patient = dataManager.getLoggedInPatient();
        Problem problem = (Problem) patient.getProblemTreeSet().toArray()[problemId];
        Record record = problem.getRecordTreeSet().pollFirst();
        if (record.getRecordPhoto() != null) {
            view.updateCameraOverlayImage(record.getRecordPhoto());
        } else {
            view.updateCameraOverlayError();
        }
    }

    public void commitRecordPhoto(Bitmap bitmap, int recordId, int problemId) {
        try {
            Log.w(TAG, "record id: " + recordId + " " + "problem id: " + problemId);
            patient = dataManager.getLoggedInPatient();
            Problem problem = (Problem) patient.getProblemTreeSet().toArray()[problemId];
            Record record = (Record) problem.getRecordTreeSet().toArray()[recordId];
            record.setRecordPhoto(bitmap);

            if (record.getRecordPhoto() != null)
                Log.w(TAG, "IMAGE NOT NULL");

            //problem.getRecordTreeSet().remove(record);
            problem.getRecordTreeSet().remove(record);
            problem.getRecordTreeSet().add(record);
            patient.getProblemTreeSet().remove(problem);
            patient.getProblemTreeSet().add(problem);
            Log.w(TAG, "problem title: " + problem.getTitle() + " record title: " + record.getTitle());
            dataManager.commitPatient(patient);
            view.commitPhotoSuccess();
        } catch (Exception e) {
            Log.e(TAG, "failed to commit RecordPhoto", e);
            view.commitPhotoFailure();
        }
    }

    public void commitBodyPhoto(Bitmap bitmap) {
        try {
            patient = dataManager.getLoggedInPatient();
            patient.setBodyPhoto(bitmap);
            dataManager.commitPatient(patient);
            view.commitPhotoSuccess();
        } catch (Exception e) {
            Log.e(TAG, "failed to commit BodyPhoto", e);
            view.commitPhotoFailure();
        }
    }
}

