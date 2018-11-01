package ca.klapstein.baudit.Data;

import java.util.Collection;

/**
 * Data class representing a Record for a Medical Problem {@code Problem}.
 *
 * @see Problem
 */
public class Record implements Comparable<Record> {
    private static final String TAG = "Record";
    public Collection<RecordPhoto> recordPhotos; //not approved yet
    public Collection<BodyPhoto> bodyPhotos; // not approved yet
    private long timestamp;
    public String comment;

    public void setComment(String comment){
        this.comment = comment;
    }

    public String getComment(){
        return this.comment;
    }

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
