package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.views.CreatePatientAccountView;

public class CreatePatientAccountPresenter extends CreateAccountPresenter<CreatePatientAccountView> {
    private static final String TAG = "CreatePatientAccountPresenter";

    public CreatePatientAccountPresenter(CreatePatientAccountView view) {
        super(view);
    }
}
