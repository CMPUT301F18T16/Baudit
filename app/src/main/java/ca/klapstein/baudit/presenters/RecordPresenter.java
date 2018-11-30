package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.views.RecordView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Record} via a {@code EditRecordView}.
 *
 * @see ca.klapstein.baudit.data.Record
 * @see RecordView
 */
public class RecordPresenter extends Presenter<RecordView> {

    private static final String TAG = "RecordPresenter";

    private final Patient patient;
    private Record record;
    private Problem problem;

    public RecordPresenter(RecordView view, Context context) {
        super(view, context);
        // TODO: load patient via other method so that care provider can obtain record as well
        patient = dataManager.getLoggedInPatient();
    }

    public void viewStarted(int problemPosition, int recordPosition) {
        problem = (Problem) patient.getProblemTreeSet().toArray()[problemPosition];

        if (recordPosition == -1) { // If the record is new
            record = new Record("", "");
        } else { // If the record exists and is being edited
            record = (Record) problem.getRecordTreeSet().toArray()[recordPosition];
        }

        view.updateTitleField(record.getTitle());
        view.updateCommentField(record.getComment());
    }

    public void commitRecord(int position, String title, String comment) {
        record.setTitle(title);
        record.setComment(comment);

        try {
            if (position == -1) {
                problem.getRecordTreeSet().add(record);
            }
            dataManager.commitPatient(patient);
            view.commitRecordSuccess();
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "failed committing Record", e);
            view.commitRecordFailure();
        }
    }

    public static Bitmap createImage(int width, int height, int color) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(0F, 0F, (float) width, (float) height, paint);
        return bitmap;
    }
}
