package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import ca.klapstein.baudit.util.BitmapEncoder;

/**
 * Class that represents a Patient.
 */
public class Patient extends Account {

    private ProblemTreeSet problemTreeSet;

    private String bodyPhoto;

    public Patient(Username username, ContactInfo contactInfo) {
        super(username, contactInfo);
        this.problemTreeSet = new ProblemTreeSet();
    }

    /**
     * Get the list of {@code Problem}s owned by the {@code Patient}.
     *
     * @return {@code ProblemTreeSet} the list of {@code Problem}s owned by the {@code Patient}.
     */
    public ProblemTreeSet getProblemTreeSet() {
        return this.problemTreeSet;
    }

    /**
     * Get a {@code Patient}'s {@code BodyPhoto}.
     *
     * @return {@code BodyPhoto} belonging to the patient.
     */
    public Bitmap getBodyPhoto() {
        return BitmapEncoder.decodeBase64(bodyPhoto);
    }

    /**
     * Setter for a {@code Patient}'s body photo.
     *
     * @param bitmap {@code Bitmap}
     */
    public void setBodyPhoto(Bitmap bitmap) {
        this.bodyPhoto = BitmapEncoder.encodeTobase64(bitmap);
    }
}
