package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.klapstein.baudit.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class LoginPatientActivityTest extends ActivityTestRule<LoginPatientActivity> {

    private Solo solo;

    public LoginPatientActivityTest() {
        super(ca.klapstein.baudit.activities.LoginPatientActivity.class);
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

    /**
     * Ensures that the activity is created properly.
     */
    @Test
    public void testOnCreate() {
        solo.assertCurrentActivity("Wrong Activity", LoginPatientActivity.class);
    }

    /**
     * Tests a valid login. Should open the ProblemListActivity.
     * Will be updated when the remote login validation is completed.
     */
    @Test
    public void testLoginSuccess() {
        solo.enterText((EditText) solo.getView(R.id.enter_patient_username), "test");
        solo.enterText((EditText) solo.getView(R.id.enter_patient_password), "foo");
        solo.clickOnView(solo.getView(R.id.login_patient_button));
        solo.assertCurrentActivity("Wrong Activity", ProblemListActivity.class);
    }

    /**
     * Tests an invalid login attempt. Should not open a new activity.
     * Will be updated when the remote login validation is completed.
     */
    @Test
    public void testLoginFail() {
        solo.enterText((EditText) solo.getView(R.id.enter_patient_username), "wrong");
        solo.enterText((EditText) solo.getView(R.id.enter_patient_password), "wrong");
        solo.clickOnView(solo.getView(R.id.login_patient_button));
        solo.waitForText(getActivity().getResources().getString(R.string.login_failed));
        solo.assertCurrentActivity("Wrong Activity", LoginPatientActivity.class);
    }

    /**
     * Tests clicking the sign up button. Should open the CreatePatientAccountActivity.
     */
    @Test
    public void testRegister() {
        solo.clickOnView(solo.getView(R.id.register_patient_button));
        solo.assertCurrentActivity("Wrong Activity", CreatePatientAccountActivity.class);
    }
}