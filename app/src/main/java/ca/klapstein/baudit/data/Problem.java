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

    public void setDescription(String description) {
        // TODO: add validator
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        // TODO: add validator
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
