package ca.klapstein.baudit.views;

import java.util.ArrayList;

import ca.klapstein.baudit.data.BodyLocationPhoto;

public interface EditPatientAccountView extends EditAccountView {
    void updateBodyLocationField(ArrayList<BodyLocationPhoto> photos);
}
