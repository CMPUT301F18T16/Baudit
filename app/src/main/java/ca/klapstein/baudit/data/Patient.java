package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Class that represents a Patient.
 */
public class Patient extends Account {

    @NonNull
    private ProblemTreeSet problemTreeSet = new ProblemTreeSet();
    @NonNull
    private ArrayList<BodyLocationPhoto> bodyLocationPhotos = new ArrayList<>();

    public Patient(@NonNull Username username, @NonNull ContactInfo contactInfo) {
        super(username, contactInfo);
    }

    /**
     * Get the list of {@code Problem}s owned by the {@code Patient}.
     *
     * @return {@code ProblemTreeSet} the list of {@code Problem}s owned by the {@code Patient}.
     */
    @NonNull
    public ProblemTreeSet getProblemTreeSet() {
        return this.problemTreeSet;
    }

    /**
     * Get a {@code Patient}'s {@code BodyPhoto}.
     *
     * @return {@code BodyPhoto} belonging to the patient.
     */
    @NonNull
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
