package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.EditAccountPresenter;
import ca.klapstein.baudit.views.EditAccountView;

/**
 * Activity for editing a {@code Account}.
 * <p>
 * Should be accessed by both a {@code Patient} and {@code CareProvider}.
 *
 * @see ca.klapstein.baudit.data.Patient
 * @see ca.klapstein.baudit.data.CareProvider
 */
public class EditAccountActivity extends AppCompatActivity implements EditAccountView {
    private static final String TAG = "EditAccountActivity";

    private EditAccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        presenter = new EditAccountPresenter(this);
    }

    @Override
    public void setEmail(String string) {

    }

    @Override
    public void setEmailError() {

    }

    @Override
    public void setUsername(String string) {

    }

    @Override
    public void setUserNameError() {

    }

    @Override
    public void commitEditAccount() {
        finish();
    }
}
