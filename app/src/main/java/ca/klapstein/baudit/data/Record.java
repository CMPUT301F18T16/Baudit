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

    private Date date;
    private String title;
    private String comment;
    private GeoLocation geoLocation;
    private ArrayList<BodyPhotoCoords> bodyPhotoCoords;
    private ArrayList<String> keywords;

    public Record(){
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimestamp(){
        return getBauditDateFormat().format(date);
    }

    public void setTitle(String title){
        if (title.length() <= 30) {
            this.title = title;
        }else{
            throw new IllegalArgumentException("Title too long");
        }
    }

    public String getTitle(){
        return this.title;
    }

    public void setComment(String comment){
        if (comment.length() <= 300) {
            this.comment = comment;
        }else{
            throw new IllegalArgumentException("Comment too long");
        }
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
