package ca.klapstein.baudit.data;

import java.util.ArrayList;
import java.util.Date;

import static ca.klapstein.baudit.BauditDateFormat.getBauditDateFormat;

/**
 * Data class representing a Record for a Medical Problem {@code Problem}.
 *
 * @see Problem
 */
public class Record {
    private static final String TAG = "Record";

    public static final int MAX_COMMENT_LENGTH = 300;
    public static final int MAX_TITLE_LENGTH = 30;

    private Date date;
    private String title;
    private String comment;
    private GeoLocation geoLocation;
    private ArrayList<BodyPhotoCoords> bodyPhotoCoords;
    private ArrayList<String> keywords;

    public Record(){
        this.date = new Date();
        // TODO: populate these properly
        this.keywords = new ArrayList<>();
        this.bodyPhotoCoords = new ArrayList<>();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeStamp() {
        return getBauditDateFormat().format(date);
    }

    static public boolean isValidRecordTitle(String title) {
        return title.length() <= MAX_TITLE_LENGTH;
    }

    static public boolean isValidRecordComment(String comment) {
        return comment.length() <= MAX_COMMENT_LENGTH;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        if (!isValidRecordTitle(title)) {
            throw new IllegalArgumentException("invalid record title: too long");
        }
        this.title = title;
    }

    public void setComment(String comment) throws IllegalArgumentException {
        if (!isValidRecordComment(comment)) {
            throw new IllegalArgumentException("invalid record comment: too long");
        }
        this.comment = comment;
    }

    public String getComment(){
        return this.comment;
    }

    public void addKeyword(String keyword){
        this.keywords.add(keyword);
    }

    public void removeKeyword(String keyword) {
        this.keywords.remove(keyword);
    }

    public ArrayList<String> getKeywords(){
        return this.keywords;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }
}
