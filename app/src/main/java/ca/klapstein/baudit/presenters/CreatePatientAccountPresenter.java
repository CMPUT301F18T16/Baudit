package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.views.CreatePatientAccountView;

public class CreatePatientAccountPresenter extends CreateAccountPresenter<CreatePatientAccountView> {
    private static final String TAG = "CreatePatientAccountPresenter";

    public CreatePatientAccountPresenter(CreatePatientAccountView view, Context context) {
        super(view, context);
    }
}
