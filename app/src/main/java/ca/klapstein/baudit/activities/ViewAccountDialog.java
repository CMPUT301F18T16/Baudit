package ca.klapstein.baudit.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.presenters.ViewAccountPresenter;
import ca.klapstein.baudit.views.ViewAccountView;

/**
 * Dialog providing a overview of an {@code Account}.
 *
 * @see Account
 */
public class ViewAccountDialog extends DialogFragment implements ViewAccountView {

    private ViewAccountPresenter presenter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_view_account, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        // TODO: more implementation

        presenter = new ViewAccountPresenter(this, getContext());

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void setUsername() {
        // TODO: implement
    }

    @Override
    public void setFirstName() {
        // TODO: implement
    }

    @Override
    public void setLastName() {
        // TODO: implement
    }

    @Override
    public void setEmail() {
        // TODO: implement
    }

    @Override
    public void setPhoneNumber() {
        // TODO: implement
    }

    @Override
    public void setViewAccountError() {
        // TODO: implement
    }
}
