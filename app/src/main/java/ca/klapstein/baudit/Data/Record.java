package ca.klapstein.baudit.Data;

/**
 * Data class representing a Record for a Medical Problem {@code Problem}.
 *
 * @see Problem
 */
public class Record implements Comparable<Record> {
    private static final String TAG = "Record";

    private long timestamp;

    public Record() {
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    @Override
    public int compareTo(Record r) {
        return (int)(this.getTimestamp() - r.getTimestamp());
    }
}
