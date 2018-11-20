package ca.klapstein.baudit.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.LogoutPresenter;
import ca.klapstein.baudit.views.LogoutView;

/**
 * Dialog providing a logout prompt.
 */
public class LogoutDialog extends DialogFragment implements LogoutView {
    private static final String TAG = "LogoutDialog";

    LogoutPresenter presenter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.logout_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        // TODO: more implementation
        presenter = new LogoutPresenter(this, getContext());
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void setLogoutError() {

    }

    /**
     * On a proper logout go back to SplashActivity.
     */
    @Override
    public void setLogoutSuccess() {
        Intent intent = new Intent(getContext(), SplashActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
