package ca.klapstein.baudit.data;

/**
 * Class that represents a Patient.
 */
public class Patient extends User {
    private static final String TAG = "Patient";
    private ProblemTreeSet problemTreeSet;

    /**
     * A {@code Patient} can only have one {@code BodyPhoto}.
     */
    private BodyPhoto bodyPhoto;

    public ProblemTreeSet getProblemTreeSet() {
        return problemTreeSet;
    }

    public BodyPhoto getBodyPhoto() {
        return bodyPhoto;
    }

    /**
     * Setter for a {@code Patient}'s {@code BodyPhoto}.
     *
     * @param bodyPhoto {@code BodyPhoto}
     */
    public void setBodyPhoto(BodyPhoto bodyPhoto) {
        this.bodyPhoto = bodyPhoto;
    }
}
