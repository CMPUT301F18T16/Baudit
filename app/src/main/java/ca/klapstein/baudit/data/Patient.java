package ca.klapstein.baudit.data;

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
     * Add a {@code BodyLocationPhoto} to the {@code Patient}.
     *
     * @param bodyLocationPhoto {@code BodyLocationPhoto}
     */
    public void addBodyLocationPhoto(BodyLocationPhoto bodyLocationPhoto) {
        bodyLocationPhotos.add(bodyLocationPhoto);
    }

    public void removeBodyLocationPhoto(int index) {
        bodyLocationPhotos.remove(index);
    }

    public void setProblemTreeSet(ProblemTreeSet problemTreeSet){this.problemTreeSet = problemTreeSet;}
}
