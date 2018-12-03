package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static ca.klapstein.baudit.BauditDateFormat.getBauditDateFormat;

/**
 * Data class representing a Record for a Medical Problem {@code Problem}.
 *
 * @see Problem
 */
public class Record implements Comparable<Record> {
    private static final int MAX_COMMENT_LENGTH = 300;
    private static final int MAX_TITLE_LENGTH = 30;

    private Date date;

    private String title;
    private String comment;
    private GeoLocation geoLocation;

    @NonNull
    private ArrayList<RecordPhoto> recordPhotos = new ArrayList<>();
    @NonNull
    private ArrayList<BodyPhotoCoords> bodyPhotoCoords = new ArrayList<>();
    @NonNull
    private ArrayList<String> keywords = new ArrayList<>();
    private UUID recordId;

    public Record() {
        date = new Date();
        recordId = UUID.randomUUID();
    }

    public Record(String title) throws IllegalArgumentException {
        date = new Date();
        this.setTitle(title);
        recordId = UUID.randomUUID();
    }

    public Record(String title, String comment) throws IllegalArgumentException {
        date = new Date();
        this.setTitle(title);
        this.setComment(comment);
        recordId = UUID.randomUUID();
    }

    /**
     * Check if a given string is a valid Record title.
     *
     * @param title {@code String} the title to validate
     * @return {@code boolean} {@code true} if the Record's title is valid, otherwise {@code false}
     */
    static private boolean isValidRecordTitle(@NonNull String title) {
        return title.length() <= MAX_TITLE_LENGTH;
    }

    /**
     * Check if a given string is a valid Record comment.
     *
     * @param comment {@code String} the comment to validate
     * @return {@code boolean} {@code true} if the Record's comment is valid, otherwise {@code false}
     */
    static private boolean isValidRecordComment(@NonNull String comment) {
        return comment.length() <= MAX_COMMENT_LENGTH;
    }

    /**
     * Get the {@code Date} of the {@code Record}.
     *
     * @return {@code Date}
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for a {@code Record}'s {@code Date}.
     *
     * @param date {@code Date}
     */
    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    /**
     * Get the timestamp of the {@code Record}.
     *
     * @return {@code String}
     */
    public String getTimeStamp() {
        return getBauditDateFormat().format(date);
    }

    public void addRecordPhoto(Bitmap bitmap) {
        recordPhotos.add(new RecordPhoto(bitmap));
    }

    @NonNull
    public ArrayList<Bitmap> getRecordPhotos() {
        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        for (RecordPhoto recordPhoto : recordPhotos) {
            bitmaps.add(recordPhoto.getBitmap());
        }
        return bitmaps;
    }

    @Nullable
    public Bitmap getLastRecordPhoto() {
        if (getRecordPhotos().isEmpty())
            return null;
        else
            return getRecordPhotos().get(getRecordPhotos().size() - 1);
    }

    /**
     * Get the {@code title} of the {@code Record}.
     *
     * @return {@code String}
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for a {@code Record}'s title.
     *
     * @param title {@code String}
     * @throws IllegalArgumentException if the {@code Record}'s title is invalid
     */
    public void setTitle(String title) throws IllegalArgumentException {
        if (!isValidRecordTitle(title)) {
            throw new IllegalArgumentException("invalid record title: too long");
        }
        this.title = title;
    }

    /**
     * Get the {@code comment} of the {@code Record}.
     *
     * @return {@code String}
     */
    public String getComment() {
        return comment;
    }

    /**
     * Setter for a {@code Record}'s comment.
     *
     * @param comment {@code String}
     * @throws IllegalArgumentException if the {@code Record}'s comment is invalid
     */
    public void setComment(String comment) throws IllegalArgumentException {
        if (!isValidRecordComment(comment)) {
            throw new IllegalArgumentException("invalid record comment: too long");
        }
        this.comment = comment;
    }

    /**
     * Add keywords from {@code keywords}.
     *
     * TODO: add validation method for keywords?
     */
    public void addKeyword(@NonNull String keyword) {
        this.keywords.add(keyword);
    }

    /**
     * Remove keywords from {@code keywords}.
     */
    public void removeKeyword(@NonNull String keyword) {
        this.keywords.remove(keyword);
    }

    /**
     * Get the {@code keywords} of the {@code Record}.
     *
     * @return {@code ArrayList<String>}
     */
    public ArrayList<String> getKeywords() {
        return keywords;
    }

    /**
     * Get the {@code geoLocation} of the {@code Record}.
     *
     * @return {@code GeoLocation}
     */
    @Nullable
    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    /**
     * Setter for a {@code Record}'s {@code GeoLocation}.
     *
     * @param geoLocation {@code GeoLocation}
     */
    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    /**
     * Compare two {@code Record}s by their creation time.
     *
     * This is used for sorting a {@code RecordTreeSet} by a {@code Record}'s creation time.
     *
     * @param record {@code Record} the given {@code Record} to compare.
     * @return {@code 0} if both {@code Record}'s times are the same or
     *         {@code -int} if this {@code Record} is created earlier than the other
     *         {@code +int} if this {@code Record} is created later than the other
     */
    @Override
    public int compareTo(@NonNull Record record) {
        if (getRecordId().compareTo(record.getRecordId()) == 0) {
            return 0;
        }
        return date.compareTo(record.getDate());
    }

    public UUID getRecordId() {
        if (recordId == null) { // backwards compatibility fix
            setRecordId(UUID.randomUUID());
        }
        return recordId;
    }

    public void setRecordId(@NonNull UUID recordId) {
        this.recordId = recordId;
    }
}
