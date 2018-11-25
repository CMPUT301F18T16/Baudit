package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.klapstein.baudit.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CreateAccountActivityTest
    extends ActivityTestRule<CreateAccountActivity> {

    private Solo solo;

    public CreateAccountActivityTest() {
        super(CreateAccountActivity.class);
    }

    @Before
    public void setUp() {
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        solo.finishOpenedActivities();
    }

    @Test
    public void testOnCreate() {
        solo.assertCurrentActivity("Wrong Activity", CreateAccountActivity.class);
    }

    @Test
    public void testInvalidUsername() {
        solo.enterText((EditText)solo.getView(R.id.create_account_username_input), "Short");
        solo.clickOnView(solo.getView(R.id.create_account_confirm_button));
        solo.waitForText(getActivity().getResources().getString(R.string.username_error));
        assertEquals(
            getActivity().getResources().getString(R.string.username_error),
            ((TextView)solo.getView(R.id.create_account_username_error)).getText().toString()
        );
    }

    @Test
    public void testInvalidEmail() {
        solo.enterText((EditText)solo.getView(R.id.create_account_email_input), "@@");
        solo.clickOnView(solo.getView(R.id.create_account_confirm_button));
        solo.waitForText(getActivity().getResources().getString(R.string.email_error));
        assertEquals(
            getActivity().getResources().getString(R.string.email_error),
            ((TextView)solo.getView(R.id.create_account_email_error)).getText().toString()
        );
    }

    @Test
    public void testInvalidPhoneNumber() {
        solo.enterText((EditText)solo.getView(R.id.create_account_phone_number_input), "000");
        solo.clickOnView(solo.getView(R.id.create_account_confirm_button));
        solo.waitForText(getActivity().getResources().getString(R.string.phone_number_error));
        assertEquals(
            getActivity().getResources().getString(R.string.phone_number_error),
            ((TextView)solo.getView(R.id.create_account_phone_number_error)).getText().toString()
        );
    }
}