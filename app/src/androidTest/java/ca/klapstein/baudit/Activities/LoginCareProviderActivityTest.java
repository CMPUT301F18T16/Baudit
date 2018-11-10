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

public class LoginCareProviderActivityTest extends ActivityTestRule<LoginCareProviderActivity> {

    private Solo solo;

    public LoginCareProviderActivityTest() {
        super(ca.klapstein.baudit.activities.LoginCareProviderActivity.class);
    }

    @Before
    public void setUp() {
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        solo = null;
    }

    /**
     * Ensures that the activity is created properly.
     */
    @Test
    public void testOnCreate() {
        solo.waitForActivity(LoginCareProviderActivity.class);
        solo.assertCurrentActivity("Wrong Activity", LoginCareProviderActivity.class);
    }

    /**
     * Tests a valid login. Should open the PatientListActivity.
     * Will be updated when the remote login validation is completed.
     */
    @Test
    public void testLoginSuccess() {
        solo.enterText((EditText) solo.getView(R.id.enter_care_provider_username), "test");
        solo.enterText((EditText) solo.getView(R.id.enter_care_provider_password), "foo");
        solo.clickOnView(solo.getView(R.id.login_care_provider_button));
        solo.waitForActivity(PatientListActivity.class);
        solo.assertCurrentActivity("Wrong Activity", PatientListActivity.class);
    }

    /**
     * Tests an invalid login attempt. Should not open a new activity.
     * Will be updated when the remote login validation is completed.
     */
    @Test
    public void testLoginFail() {
        solo.enterText((EditText) solo.getView(R.id.enter_care_provider_username), "wrong");
        solo.enterText((EditText) solo.getView(R.id.enter_care_provider_password), "wrong");
        solo.clickOnView(solo.getView(R.id.login_care_provider_button));
        solo.waitForActivity(LoginCareProviderActivity.class);
        solo.waitForText(getActivity().getResources().getString(R.string.login_failed));
        solo.assertCurrentActivity("Wrong Activity", LoginCareProviderActivity.class);
    }

    /**
     * Tests clicking the sign up button. Should open the CreateCareProviderAccountActivity.
     */
    @Test
    public void testRegister() {
        solo.clickOnView(solo.getView(R.id.register_care_provider_button));
        solo.waitForActivity(CreateCareProviderAccountActivity.class);
        solo.assertCurrentActivity("Wrong Activity", CreateCareProviderAccountActivity.class);
    }

    /**
     * Tests switching to the LoginPatientActivity.
     */
    @Test
    public void testSwitchToPatientLogin() {
        solo.clickOnView(solo.getView(R.id.log_in_as_patient_button));
        solo.waitForActivity(LoginPatientActivity.class);
        solo.assertCurrentActivity("Wrong Activity", LoginPatientActivity.class);
    }
}