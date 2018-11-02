package ca.klapstein.baudit.views;

import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.PatientTreeSet;

public interface PatientListView {
    void addPatient(Patient patient);

    void editPatient(Patient patient, final int position);

    void setPatientList(PatientTreeSet patientList);
}
