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

    @NotNull
    public PatientTreeSet getAssignedPatientTreeSet(){
        return this.assignedPatientTreeSet;
    }
}