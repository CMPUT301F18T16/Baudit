package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Data class representing a Medical Problem for a {@code Patient}.
 *
 * @see Patient
 */
public class Problem implements Comparable<Problem> {
    private static final String TAG = "Problem";
    private String title;
    private String description;
    private String timestamp;
    private RecordTreeSet recordTreeSet;

    public RecordTreeSet getRecordTreeSet() {
        return recordTreeSet;
    }

    public Problem(@NonNull String title, String description) throws IllegalArgumentException{
        this.setTitle(title);
        this.setDescription(description);
        this.setTimeStamp();

    }


    public void setTimeStamp() {
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");
        this.timestamp = date.format(new Date());
    }

    public String getTimestamp(){
        return this.timestamp;
    }


    public void setTitle(@NonNull String title)throws IllegalArgumentException {
        int len = title.length();
        if( len < 30 && 0 < len ){
            this.title = title;
        } else throw new IllegalArgumentException();
    }

    public void setDescription(String description) {
        int len = description.length();
        if (len < 300){
            this.description = description;
        } else throw new IllegalArgumentException();
    }


    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }


    @Override
    public int compareTo(@NonNull Problem problem) {
      return this.getTimestamp().compareTo(problem.getTimestamp()); //Order by date
    }

}
