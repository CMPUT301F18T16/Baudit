package ca.klapstein.baudit.data;

import android.location.Location;
import android.support.annotation.NonNull;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Data class representing a Record for a Medical Problem {@code Problem}.
 *
 * @see Problem
 */
public class Record implements Comparable<Record> {
    private static final String TAG = "Record";

    public String timestamp;
    private String title;
    private String comment;
    private double xcoord;
    private double ycoord;
    private BodyPhotoCoords bodyPhotoCoords[];
    public String[] keywords;

    public Record(){
        this.setTimeStamp();
    }


    public void setTimeStamp() {
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");
        this.timestamp = date.format(new Date());
    }

    public String getTimestamp(){
        return this.timestamp;
    }

    public void setTitle(String title){
        if(title.length()<=30) {
            this.title = title;
        }else{
            throw new IllegalArgumentException("Title too long");
        }
    }

    public String getTitle(){
        return this.title;
    }

    public void setComment(String comment){
        if(comment.length()<=300) {
            this.comment = comment;
        }else{
            throw new IllegalArgumentException("Comment too long");
        }
    }
    public String getComment(){
        return this.comment;
    }

    public void addKeyword(String keyword){
        int len = this.keywords.length;
        len++;
        keywords[len]=keyword;
    }

    public String popKeyword(){
        return this.keywords[this.keywords.length+1];
    }

    public String[] getKeywords(){
        return this.keywords;
    }

    @Override
    public int compareTo(@NonNull Record record) {
        return this.getTimestamp().compareTo(record.getTimestamp());
    }
}
