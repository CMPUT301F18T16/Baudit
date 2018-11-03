package ca.klapstein.baudit.data;

import java.util.Date;

import static ca.klapstein.baudit.BauditDateFormat.getBauditDateFormat;

/**
 * Data class representing a Medical Problem for a {@code Patient}.
 *
 * @see Patient
 */
public class Problem {
    private static final String TAG = "Problem";

    public static final int MAX_DESCRIPTION_LENGTH = 300;
    public static final int MAX_TITLE_LENGTH = 30;

    private RecordTreeSet recordTreeSet;
    private String title;
    private String description;
    private Date date;

    public RecordTreeSet getRecordTreeSet() {
        return recordTreeSet;
    }

    public String getDescription() {
        return description;
    }

    static public boolean isValidProblemDescription(String description) {
        return description.length() <= MAX_DESCRIPTION_LENGTH;
    }

    static public boolean isValidProblemTitle(String title) {
        return title.length() <= MAX_TITLE_LENGTH;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) throws IllegalArgumentException {
        if (!isValidProblemDescription(description)) {
            throw new IllegalArgumentException("invalid problem description");
        }
        this.description = description;
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
}
