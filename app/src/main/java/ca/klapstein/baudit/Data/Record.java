package ca.klapstein.baudit.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        this.timestamp = date.getTime();

    }

    public long getTimestamp() {
        return this.timestamp;
    }

    @Override
    public int compareTo(Record r) {
        return (int)(this.getTimestamp() - r.getTimestamp());
    }
}
