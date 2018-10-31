package ca.klapstein.baudit.Data;

import java.util.Collection;
import java.util.Date;

/**
 * Data class representing a Record for a Medical Problem {@code Problem}.
 *
 * @see Problem
 */
public class Record {
    private static final String TAG = "Record";
    public Username username;
    public String timestamp;
    public Collection<RecordPhoto> recordPhotos; //not approved yet
    public Collection<BodyPhoto> bodyPhotos; // not approved yet
}
