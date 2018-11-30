package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.graphics.Bitmap;

import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.data.RecordPhoto;
import ca.klapstein.baudit.views.AddPhotoView;

public class AddPhotoPresenter extends Presenter<AddPhotoView> {

    private Patient patient;

    public AddPhotoPresenter(AddPhotoView view, Context context) {

        super(view, context);
        patient = dataManager.getLoggedInPatient();
    }

    public boolean ValidatePhoto(Bitmap bitmap) {

        if (bitmap != null) {
            Bitmap emptyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
            if (!bitmap.equals(emptyBitmap))
                return true;
        }
        return false;
    }

    public void UploadRecordPhoto(Bitmap bitmap, int recordId, int problemId){

        RecordPhoto recordPhoto = new RecordPhoto(bitmap);
        Problem problem = (Problem) patient.getProblemTreeSet().toArray()[problemId];
        Record record = (Record) problem.getRecordTreeSet().toArray()[recordId];
        record.addPhoto(recordPhoto);
    }

    public void UploadBodyPhoto(Bitmap bitmap){

        //TODO: how to add body photo with no patient?
        /*
        BodyPhoto bodyPhoto = new BodyPhoto(bitmap);
        patient.setBodyPhoto(bodyPhoto);
        */
    }

}