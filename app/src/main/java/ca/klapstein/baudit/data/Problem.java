package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
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
    @NonNull
    private String title;
    @Nullable
    private String description;
    @NonNull
    private final UUID problemId = UUID.randomUUID();
    @NonNull
    private Date date = new Date();
    @NonNull
    private RecordTreeSet recordTreeSet = new RecordTreeSet();

    public Problem(@NonNull String title) throws IllegalArgumentException {
        this.setTitle(title);
    }

    /**
     * Check if a given string is a valid Problem description.
     *
     * @param description {@code String} the description to validate
     * @return {@code boolean} {@code true} if the Problem's description is valid, otherwise
     * {@code false}
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

    public HashSet<String> getKeywords() {
        HashSet<String> keywords = new HashSet<>();
        keywords.clear();
        if (getTitle() != null)
            keywords.addAll(Arrays.asList(getTitle().toLowerCase().split(" ")));
        if (getDescription() != null)
            keywords.addAll(Arrays.asList(getDescription().toLowerCase().split(" ")));
        if (getDate() != null)
            keywords.add(getTimeStamp());
        for (Record record : getRecordTreeSet()) {
            if (record.getGeoLocation() != null && record.getGeoLocation().getAddress() != null) {
                keywords.addAll(
                        Arrays.asList(record.getGeoLocation().getAddress().toLowerCase().split(" "))
                );
            }
            if (record.getComment() != null) {
                keywords.addAll(Arrays.asList(record.getComment().toLowerCase().split(" ")));
            }
            if (record.getTitle() != null) {
                keywords.addAll(Arrays.asList(record.getTitle().toLowerCase().split(" ")));
            }
        }
        return keywords;
    }

    public Problem(@NonNull String title, @NonNull String description) throws IllegalArgumentException {
        this.setTitle(title);
        this.setDescription(description);
    }

    /**
     * Get the {@code RecordTreeSet} of the {@code Problem}.
     *
     * @return {@code RecordTreeSet}
     */
    @NotNull
    public RecordTreeSet getRecordTreeSet() {
        return recordTreeSet;
    }

    /**
     * Setter for a {@code Problem}'s {@code RecordTreeSet}.
     *
     * @param recordTreeSet {@code RecordTreeSet}
     */
    public void setRecordTreeSet(@NonNull RecordTreeSet recordTreeSet) {
        this.recordTreeSet = recordTreeSet;
    }

    /**
     * Get the {@code description} of the {@code Problem}.
     *
     * @return {@code String}
     */
    @Nullable
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
    @NotNull
    public String getTitle() {
        return title;
    }

    /**
     * Setter for a {@code Problem}'s title.
     *
     * @param title {@code String}
     * @throws IllegalArgumentException if the {@code Problem}'s title is invalid
     */
    public void setTitle(@NonNull String title) throws IllegalArgumentException {
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
    @NotNull
    public Date getDate() {
        return date;
    }

    /**
     * Setter for a {@code Problem}'s {@code Date}.
     *
     * @param date {@code Date}
     */
    public void setDate(@NonNull Date date) {
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
     * Compare two {@code Problem}s by their creation time and if equal creation times by their
     * titles.
     * <p>
     * This is used for sorting a {@code ProblemTreeSet} by a {@code Problem}'s creation time and
     * title.
     *
     * @param problem {@code Problem} the given {@code Problem} to compare.
     * @return {@code int} {@code 0} if both {@code Problem}'s creation times and titles are equal
     *                     {@code -int} if the this {@code Problem} is less than the other or
     *                     {@code +int} if the this {@code Problem} is greater than the other.
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

    @NotNull
    public UUID getProblemId() {
        return problemId;
    }
}