package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.GeoLocation;
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
        if (problemPosition == -1) { // For testing purposes, this case is handled
            problem = new Problem(
                context.getResources().getString(R.string.default_title),
                context.getResources().getString(R.string.default_description)
            );
        } else {
            problem = (Problem) patient.getProblemTreeSet().toArray()[problemPosition];
        }


        if (recordPosition == -1) { // If the record is new
            record = new Record(
                context.getResources().getString(R.string.default_title),
                context.getResources().getString(R.string.default_comment)
            );
            view.updateRecordHints();
        } else { // If the record exists and is being edited
            record = (Record) problem.getRecordTreeSet().toArray()[recordPosition];
            view.updateTitleField(record.getTitle());
            view.updateCommentField(record.getComment());
            view.updateLocationField(record.getGeoLocation());
        }

    }

    public void commitRecord(int position, String title, String comment, GeoLocation geoLocation) {
        record.setTitle(title);
        record.setComment(comment);

        if (geoLocation != null) {
            record.setGeoLocation(geoLocation);
        }

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
}
