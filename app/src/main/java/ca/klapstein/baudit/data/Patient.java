package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import ca.klapstein.baudit.util.BitmapEncoder;

import java.util.ArrayList;

/**
 * Class that represents a Patient.
 */
public class Patient extends Account {

    private ProblemTreeSet problemTreeSet;

    private ArrayList<BodyLocationPhoto> bodyLocationPhotos;

    public Patient(Username username, ContactInfo contactInfo) {
        super(username, contactInfo);
        this.problemTreeSet = new ProblemTreeSet();
        bodyLocationPhotos = new ArrayList<>();
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
    public ArrayList<BodyLocationPhoto> getBodyLocationPhotos() {
        return bodyLocationPhotos;
    }

    /**
     * Setter for a {@code Patient}'s body photo.
     *
     * @param bitmap {@code Bitmap}
     */
    public void addBodyLocationPhoto(Bitmap bitmap, String label) {
        bodyLocationPhotos.add(new BodyLocationPhoto(bitmap, label));
    }

    public void removeBodyLocationPhoto(int index) {
        bodyLocationPhotos.remove(index);
    }
}
