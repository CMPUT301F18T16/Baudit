package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import ca.klapstein.baudit.data.*;
import ca.klapstein.baudit.views.AddPhotoView;

public class AddPhotoPresenter extends Presenter<AddPhotoView> {

    private static final String TAG = "AddPhotoPresenter";
    private Patient patient;

    public AddPhotoPresenter(AddPhotoView view, Context context) {
        super(view, context);
    }

    public void getLastRecordPhoto(int problemId) {
        try {
            patient = dataManager.getLoggedInPatient();
            Problem problem = (Problem) patient.getProblemTreeSet().toArray()[problemId];
            Record record = problem.getRecordTreeSet().pollFirst();
            if (record.getLastRecordPhoto() != null) {
                view.updateCameraOverlayImage(record.getLastRecordPhoto());
            } else {
                view.updateCameraOverlayError();
            }
        } catch (Exception e){
            Log.e(TAG, "failed to get record photo", e);
            view.updateCameraOverlayError();
        }
    }

    public void commitRecordPhoto(Bitmap bitmap, int recordId, int problemId) {
        try {
            patient = dataManager.getLoggedInPatient();
            Problem problem = (Problem) patient.getProblemTreeSet().toArray()[problemId];
            Record record = (Record) problem.getRecordTreeSet().toArray()[recordId];
            record.addRecordPhoto(new RecordPhoto(bitmap));
            problem.getRecordTreeSet().add(record);
            patient.getProblemTreeSet().remove(problem);
            patient.getProblemTreeSet().add(problem);
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
            // TODO: update
            patient.addBodyLocationPhoto(new BodyLocationPhoto(bitmap, "No label"));
            dataManager.commitPatient(patient);
            view.commitPhotoSuccess();
        } catch (Exception e) {
            Log.e(TAG, "failed to commit BodyPhoto", e);
            view.commitPhotoFailure();
        }
    }
}

