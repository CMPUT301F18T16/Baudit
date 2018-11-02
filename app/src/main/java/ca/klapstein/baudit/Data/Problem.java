package ca.klapstein.baudit.Data;

import java.util.Date;

/**
 * Data class representing a Medical Problem for a {@code Patient}.
 *
 * @see Patient
 */
public class Problem implements Comparable<Problem> {
    private static final String TAG = "Problem";
    private RecordTreeSet recordTreeSet;

    private long timestamp;
    private String title;

    public Problem() {

        this.recordTreeSet = new RecordTreeSet();
        Date date = new Date();
        this.timestamp = date.getTime();
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public RecordTreeSet getRecordTreeSet() {
        return recordTreeSet;
    }

    @Override
    public int compareTo(Problem p) {
        return (int)(this.getTimestamp() - p.getTimestamp());
    }
}
