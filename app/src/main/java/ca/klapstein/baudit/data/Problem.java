package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;
import java.util.Date;

import static ca.klapstein.baudit.BauditDateFormat.getBauditDateFormat;

/**
 * Data class representing a Medical Problem for a {@code Patient}.
 *
 * @see Patient
 */
public class Problem implements Comparable<Problem> {
    public static final int MAX_DESCRIPTION_LENGTH = 300;
    public static final int MAX_TITLE_LENGTH = 30;

    private String title;
    private String description;
    private RecordTreeSet recordTreeSet;
    private Date date;

    public Problem() {
        this.date = new Date();
    }

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

    public RecordTreeSet getRecordTreeSet() {
        return recordTreeSet;
    }

    public Problem(@NonNull String title, String description) throws IllegalArgumentException{
        this.setTitle(title);
        this.setDescription(description);
        this.date = new Date();
    }

    @Override
    public int compareTo(@NonNull Problem problem) {
        // NOTE: Remove this code once remote is working.
        // Users are physically unable to add more than one problem per ms.
        // However, when adding programmatically, there can be 2-3 added per ms.
        // Thus, the ProblemTReeSet thinks they are the same object unless there is another check.
        // Hence, this code.
        if (getDate().compareTo(problem.getDate()) == 0) {
            return getTitle().compareTo(problem.getTitle());
        } else {
            return getDate().compareTo(problem.getDate()); // Order by date
        }
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
}
