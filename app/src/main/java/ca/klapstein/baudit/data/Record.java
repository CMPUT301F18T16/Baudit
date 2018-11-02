package ca.klapstein.baudit.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Data class representing a Record for a Medical Problem {@code Problem}.
 *
 * @see Problem
 */
public class Record {
    private static final String TAG = "Record";

    private String timestamp;
    private String title;
    private String comment;
    private double xCoord;
    private double yCoord;
    private ArrayList<BodyPhotoCoords> bodyPhotoCoords;
    private ArrayList<String> keywords;

    public Record(){
        this.setTimestamp();
    }

    public void setTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.CANADA);
        this.timestamp = dateFormat.format(new Date());
    }

    public String getTimestamp(){
        return this.timestamp;
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
}
