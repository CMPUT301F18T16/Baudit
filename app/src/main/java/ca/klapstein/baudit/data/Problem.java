package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

import static ca.klapstein.baudit.BauditDateFormat.getBauditDateFormat;

/**
 * Data class representing a Medical Problem for a {@code Patient}.
 *
 * @see Patient
 */
public class Problem implements Comparable<Problem> {

    private static final int MAX_DESCRIPTION_LENGTH = 300;
    private static final int MAX_TITLE_LENGTH = 30;

    private UUID problemId;
    private String title;
    private String description;
    private Date date;
    private RecordTreeSet recordTreeSet;

    public Problem(@NonNull String title) throws IllegalArgumentException {
        this.setTitle(title);
        this.date = new Date();
        this.recordTreeSet = new RecordTreeSet();
        this.problemId = UUID.randomUUID();
    }

    /**
     * Check if a given string is a valid Problem description.
     *
     * @param description {@code String} the description to validate
     * @return {@code boolean} {@code true} if the Problem's description is valid, otherwise {@code false}
     */
    static public boolean isValidProblemDescription(@NonNull String description) {
        return description.length() <= MAX_DESCRIPTION_LENGTH;
    }


    /**
     * Check if a given string is a valid Problem title.
     *
     * @param title {@code String} the title to validate
     * @return {@code boolean} {@code true} if the Problem's title is valid, otherwise {@code false}
     */
    static public boolean isValidProblemTitle(@NonNull String title) {
        return title.length() <= MAX_TITLE_LENGTH;
    }

    public Problem(@NonNull String title, String description) throws IllegalArgumentException{
        this.setTitle(title);
        this.setDescription(description);
        this.date = new Date();
        this.recordTreeSet = new RecordTreeSet();
        this.problemId = UUID.randomUUID();
    }

    /**
     * Get the {@code RecordTreeSet} of the {@code Problem}.
     *
     * @return {@code RecordTreeSet}
     */
    public RecordTreeSet getRecordTreeSet() {
        return recordTreeSet;
    }

    /**
     * Setter for a {@code Problem}'s {@code RecordTreeSet}.
     *
     * @param recordTreeSet {@code RecordTreeSet}
     */
    public void setRecordTreeSet(RecordTreeSet recordTreeSet) {
        this.recordTreeSet = recordTreeSet;
    }

    /**
     * Get the {@code description} of the {@code Problem}.
     *
     * @return {@code String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for a {@code Problem}'s description.
     *
     * @param description {@code String}
     * @throws IllegalArgumentException if the {@code Problem}'s description is invalid
     */
    public void setDescription(String description) throws IllegalArgumentException {
        if (!isValidProblemDescription(description)) {
            throw new IllegalArgumentException("invalid problem description");
        }
        this.description = description;
    }

    /**
     * Get the {@code title} of the {@code Problem}.
     *
     * @return {@code String}
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for a {@code Problem}'s title.
     *
     * @param title {@code String}
     * @throws IllegalArgumentException if the {@code Problem}'s title is invalid
     */
    public void setTitle(String title) throws IllegalArgumentException {
        if (!isValidProblemTitle(title)) {
            throw new IllegalArgumentException("invalid problem title");
        }
        this.title = title;
    }

    /**
     * Get the {@code Date} of the {@code Problem}.
     *
     * @return {@code Date}
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for a {@code Problem}'s {@code Date}.
     *
     * @param date {@code Date}
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the timestamp of the {@code Date} for the {@code Problem}.
     *
     * @return {@code String}
     */
    public String getTimeStamp() {
        return getBauditDateFormat().format(date);
    }

    /**
     * Compare two {@code Problem}s by their creation time and if equal creation times by their titles.
     * <p>
     * This is used for sorting a {@code ProblemTreeSet} by a {@code Problem}'s creation time and title.
     *
     * @param problem {@code Problem} the given {@code Problem} to compare.
     * @return {@code int} {@code 0} if both {@code Problem}'s creation times and titles are the same or
     *                     {@code -int} if the this {@code Problem} is less than the given {@code Problem}
     *                     {@code +int} if the this {@code Problem} is greater than the given {@code Problem}.
     */
    @Override
    public int compareTo(@NonNull Problem problem) {
        if (getProblemId().compareTo(problem.getProblemId()) == 0) {
            return 0;
        }

        if (getDate().compareTo(problem.getDate()) == 0) {
            return getTitle().compareTo(problem.getTitle());
        } else {
            return getDate().compareTo(problem.getDate()); // Order by date
        }
    }

    public UUID getProblemId() {
        if (problemId == null) {
            setProblemId(UUID.randomUUID());
        }
        return problemId;
    }

    public void setProblemId(UUID problemId) {
        this.problemId = problemId;
    }
}