package ca.klapstein.baudit.data;

/**
 * data class representing a Medical Problem for a {@code Patient}.
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
