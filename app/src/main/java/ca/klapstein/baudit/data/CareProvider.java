package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a Care Provider.
 */
public class CareProvider extends Account {
    private static final String TAG = "CareProvider";

    @NonNull
    private PatientTreeSet assignedPatientTreeSet = new PatientTreeSet();

    public CareProvider(@NonNull Username username, @NonNull ContactInfo contactInfo) {
        super(username, contactInfo);
    }

    /**
     * Decode the {@code bitmapString} and return its representing {@code Bitmap} for usage.
     *
     * @return {@code PatientTreeSet}
     */
    @NotNull
    public PatientTreeSet getAssignedPatientTreeSet(){
        return this.assignedPatientTreeSet;
    }
}