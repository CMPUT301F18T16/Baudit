package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static ca.klapstein.baudit.BauditDateFormat.getBauditDateFormat;
import static ca.klapstein.baudit.util.BitmapEncoderUtil.decodeBase64;
import static ca.klapstein.baudit.util.BitmapEncoderUtil.encodeTobase64;

/**
 * Data class representing a Record for a Medical Problem {@code Problem}.
 *
 * @see Problem
 */
public class Record implements Comparable<Record> {
    private static final int MAX_PHOTO_BYTES = 65535;
    private static final int MAX_COMMENT_LENGTH = 300;
    private static final int MAX_TITLE_LENGTH = 30;


    @NonNull
    private ArrayList<String> photoBitmapStrings = new ArrayList<String>();
    @NonNull
    private final UUID recordId = UUID.randomUUID();
    @Nullable
    private String title;
    @Nullable
    private String comment;
    @Nullable
    private GeoLocation geoLocation;
    @NonNull
    private ArrayList<RecordPhoto> recordPhotos = new ArrayList<>();

    @NonNull
    private ArrayList<BodyPhotoCoords> bodyPhotoCoords = new ArrayList<>();
    @NonNull
    private ArrayList<String> keywords = new ArrayList<>();
    @NonNull
    private Date date = new Date();

    public Record() {
    }

    public Record(@NonNull String title) throws IllegalArgumentException {
        this.setTitle(title);
    }

    public Record(@NonNull String title, @NonNull String comment) throws IllegalArgumentException {
        this.setTitle(title);
        this.setComment(comment);
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
    @NonNull
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
        if (photoBitmapStrings.size() >= 1) {
            String recordPhotoBitmapString = photoBitmapStrings.get(photoBitmapStrings.size() - 1);
            if (recordPhotoBitmapString != null)
                return decodeBase64(recordPhotoBitmapString);
        }
        return null;
    }

    public ArrayList<String> getPhotoBitmapStrings(){
        return this.photoBitmapStrings;
    }

    public void addRecordPhoto(Bitmap bitmap) {
        String recordPhotoBitmapString;
        if (bitmap.getByteCount() > MAX_PHOTO_BYTES) {
            Bitmap CorrectedBitmap = ThumbnailUtils.extractThumbnail(bitmap, 255, 255);
            recordPhotoBitmapString = encodeTobase64(CorrectedBitmap);
        }else
            recordPhotoBitmapString = encodeTobase64(bitmap);
        photoBitmapStrings.add(recordPhotoBitmapString);
    }

    /**
     * Get the {@code title} of the {@code Record}.
     *
     * @return {@code String}
     */
    @Nullable
    public String getTitle() {
        return title;
    }

    /**
     * Setter for a {@code Record}'s title.
     *
     * @param title {@code String}
     * @throws IllegalArgumentException if the {@code Record}'s title is invalid
     */
    public void setTitle(@NonNull String title) throws IllegalArgumentException {
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
    @Nullable
    public String getComment() {
        return comment;
    }

    /**
     * Setter for a {@code Record}'s comment.
     *
     * @param comment {@code String}
     * @throws IllegalArgumentException if the {@code Record}'s comment is invalid
     */
    public void setComment(@NonNull String comment) throws IllegalArgumentException {
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
    @NonNull
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
    public void setGeoLocation(@NonNull GeoLocation geoLocation) {
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

    @NonNull
    public UUID getRecordId() {
        return recordId;
    }
}
