
package ca.klapstein.baudit.data;


/**
 * Class that represents a Patient.
 */
public class Patient extends Account implements Comparable<Patient> {
    private static final String TAG = "Patient";
    private ProblemTreeSet problemTreeSet;

    public Patient(Username username, Password password, ContactInfo contactInfo){
        super(username, contactInfo, password);
        this.problemTreeSet = new ProblemTreeSet();
    }

    @Override
    public int compareTo(Patient patient) {
        return  (int)(this.getUsername().getUsernameString().compareTo(patient.getUsername().getUsernameString()));
    }
  
    /**
     * A {@code Patient} can only have one {@code BodyPhoto}.
     */
    private BodyPhoto bodyPhoto;

    /**
     * Get the list of {@code Problem}s owned by the {@code Patient}.
     *
     * @return {@code ProblemTreeSet} the list of {@code Problem}s owned by the {@code Patient}.
     */
    public ProblemTreeSet getProblemTreeSet() {
        return problemTreeSet;
    }

    /**
     * Get a {@code Patient}'s {@code BodyPhoto}.
     *
     * @return {@code BodyPhoto} belonging to the patient.
     */
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