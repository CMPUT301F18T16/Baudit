package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.PatientTreeSet;
import ca.klapstein.baudit.views.PatientListView;
import ca.klapstein.baudit.views.PatientRowView;

/**
 * MVP presenter for presenting a {@code PatientTreeSet} on a {@code PatientListView}.
 *
 * @see PatientTreeSet
 * @see PatientListView
 */
public class PatientListPresenter {
    private PatientListView view;
    private PatientTreeSet patientTreeSet;

    public PatientListPresenter(PatientListView view) {
        this.view = view;
    }

    public void onBindPatientRowViewAtPosition(PatientRowView rowView, int position) {
        Patient patient = (Patient)patientTreeSet.toArray()[position];
        // rowView.setPatientNameText(patient.name);
    }

    public int getPatientCount() {
        return patientTreeSet.size();
    }
}
