package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.ViewAccountPresenter;
import ca.klapstein.baudit.views.ViewAccountView;

public class ViewAccountActivity extends AppCompatActivity implements ViewAccountView {

    public static String VIEW_ACCOUNT_USERNAME_EXTRA = "username";

    private String username;
    private ViewAccountPresenter presenter;
    private TextView nameView;
    private TextView usernameView;
    private TextView emailView;
    private TextView phoneNumberView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);

        username = getIntent().getStringExtra(VIEW_ACCOUNT_USERNAME_EXTRA);

        presenter = new ViewAccountPresenter(this, getApplicationContext());

        nameView = findViewById(R.id.view_account_name);
        usernameView = findViewById(R.id.view_account_username);
        emailView = findViewById(R.id.view_account_email);
        phoneNumberView = findViewById(R.id.view_account_phone_number);

        Button okButton = findViewById(R.id.view_account_ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted(username);
    }

    @Override
    public void updateNameDisplay(String firstName, String lastName) {
        String name = firstName + " " + lastName;
        nameView.setText(name);
    }

    @Override
    public void updateUsernameDisplay(String username) {
        usernameView.setText(username);
    }

    @Override
    public void updateEmailDisplay(String email) {
        emailView.setText(email);
    }

    @Override
    public void updatePhoneNumberDisplay(String phoneNumber) {
        phoneNumberView.setText(phoneNumber);
    }

    @Override
    public void updateViewAccountError() {
        finish();
    }
}
