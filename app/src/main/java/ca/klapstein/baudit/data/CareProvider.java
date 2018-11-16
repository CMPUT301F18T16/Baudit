package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;

/**
 * Class that represents a Care Provider.
 */
public class CareProvider extends Account implements Comparable<CareProvider> {
    private static final String TAG = "CareProvider";

    public CareProvider(Username username, ContactInfo contactInfo, Password password) {
        super(username, contactInfo, password);
    }

    private PatientTreeSet assignedPatientTreeSet;

    public CareProvider(Username username, Password password, ContactInfo contactInfo){
        super(username, contactInfo, password);
        this.assignedPatientTreeSet = new PatientTreeSet();
    }

    @Override
    public int compareTo(@NonNull CareProvider careProvider) {
        return this.getUsername().getUsernameString()
                .compareTo(careProvider.getUsername().getUsernameString());
    }

    public PatientTreeSet getAssignedPatientTreeSet(){
        return this.assignedPatientTreeSet;
    }

}