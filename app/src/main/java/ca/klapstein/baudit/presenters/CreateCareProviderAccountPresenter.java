package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.views.CreateCareProviderAccountView;

public class CreateCareProviderAccountPresenter extends CreateAccountPresenter<CreateCareProviderAccountView> {
    private static final String TAG = "CreateCareProviderAccountPresenter";

    public CreateCareProviderAccountPresenter(CreateCareProviderAccountView view) {
        super(view);
    }

    public boolean validateCareProviderAccount(String username, String email, String careProviderID) {
        return true;
    }
}
