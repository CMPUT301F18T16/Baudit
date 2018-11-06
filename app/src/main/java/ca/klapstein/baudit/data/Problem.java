package ca.klapstein.baudit.data;

import java.util.Date;

import static ca.klapstein.baudit.BauditDateFormat.getBauditDateFormat;

/**
 * Data class representing a Medical Problem for a {@code Patient}.
 *
 * @see Patient
 */
public class Problem implements Comparable<Problem> {
    private static final String TAG = "Problem";
    
    public static final int MAX_DESCRIPTION_LENGTH = 300;
    public static final int MAX_TITLE_LENGTH = 30;
    private RecordTreeSet recordTreeSet;
    private String title;
    private String description;
    private Date date;

    /**
     * Check if a given string is a valid Problem description.
     *
     * @param description {@code String} the description to validate
     * @return {@code boolean} {@code true} if the Problem's description is valid, otherwise {@code false}
     */
    static public boolean isValidProblemDescription(String description) {
        return description.length() <= MAX_DESCRIPTION_LENGTH;
    }

    /**
     * Check if a given string is a valid Problem title.
     *
     * @param title {@code String} the title to validate
     * @return {@code boolean} {@code true} if the Problem's title is valid, otherwise {@code false}
     */
    static public boolean isValidProblemTitle(String title) {
        return title.length() <= MAX_TITLE_LENGTH;
    }

    private long timestamp;
    private String title;

    public Problem() {

        this.recordTreeSet = new RecordTreeSet();
        Date date = new Date();
        this.timestamp = date.getTime();
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public RecordTreeSet getRecordTreeSet() {
        return recordTreeSet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IllegalArgumentException {
        if (!isValidProblemDescription(description)) {
            throw new IllegalArgumentException("invalid problem description");
        }
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        if (!isValidProblemTitle(title)) {
            throw new IllegalArgumentException("invalid problem title");
        }
        this.title = title;
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
  
    @Override
    public int compareTo(Problem p) {
        return (int)(this.getTimestamp() - p.getTimestamp());
    }
}
