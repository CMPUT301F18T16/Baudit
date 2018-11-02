package ca.klapstein.baudit.data;

/**
 * Class that represents a Patient.
 */
public class Patient extends User {
    private static final String TAG = "Patient";
    private ProblemTreeSet problemTreeSet;
    private BodyPhoto bodyPhoto;

    public ProblemTreeSet getProblemTreeSet() {
        return problemTreeSet;
    }

    public BodyPhoto getBodyPhoto() {
        return bodyPhoto;
    }

    public void setBodyPhoto(BodyPhoto bodyPhoto) {
        this.bodyPhoto = bodyPhoto;
    }
}
