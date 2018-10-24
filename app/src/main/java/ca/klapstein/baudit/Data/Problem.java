package ca.klapstein.baudit.Data;

/**
 * Data class representing a Medical Problem for a {@code Patient}.
 *
 * @see Patient
 */
public class Problem {
    private static final String TAG = "Problem";
    private RecordTreeSet recordTreeSet;

    public RecordTreeSet getRecordTreeSet() {
        return recordTreeSet;
    }
}
