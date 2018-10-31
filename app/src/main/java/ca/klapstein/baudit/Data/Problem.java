package ca.klapstein.baudit.Data;

/**
 * Data class representing a Medical Problem for a {@code Patient}.
 *
 * @see Patient
 */
public class Problem implements Comparable<Problem> {
    private static final String TAG = "Problem";
    private RecordTreeSet recordTreeSet;

    private long timestamp;

    public Problem() {
        this.timestamp = System.currentTimeMillis();
        this.recordTreeSet = new RecordTreeSet();
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
