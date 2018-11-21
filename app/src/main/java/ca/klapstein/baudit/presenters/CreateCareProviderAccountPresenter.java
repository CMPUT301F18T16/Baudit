package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.views.CreateCareProviderAccountView;

public class CreateCareProviderAccountPresenter extends CreateAccountPresenter<CreateCareProviderAccountView> {
    private static final String TAG = "CreateCareProviderAccountPresenter";

    public CreateCareProviderAccountPresenter(CreateCareProviderAccountView view, Context context) {
        super(view, context);
    }

    public boolean validateCareProviderAccount(String username, String email, String careProviderID) {
        // TODO:
        return true;
    }
}
