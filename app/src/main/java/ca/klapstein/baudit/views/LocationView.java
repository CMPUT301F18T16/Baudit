package ca.klapstein.baudit.views;

import ca.klapstein.baudit.data.Patient;

public interface LocationView extends View {
    void chooseLocation(Patient patient);
    // void updateLocation(); // TODO
    // void setLocationError();
}
