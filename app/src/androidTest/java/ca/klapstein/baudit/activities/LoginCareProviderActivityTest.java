package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.models.DataModel;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class LoginCareProviderActivityTest extends ActivityTestRule<LoginCareProviderActivity> {

    private Solo solo;
    private DataModel dataModel;

    public LoginCareProviderActivityTest() {
        super(ca.klapstein.baudit.activities.LoginCareProviderActivity.class);
    }

    @Before
    public void setUp() {
        dataModel = new DataModel(InstrumentationRegistry.getTargetContext());
        dataModel.clearLoginAccountUserName();
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        solo.finishOpenedActivities();
        dataModel.clearLoginAccountUserName();
    }

    /**
     * Ensures that the activity is created properly.
     */
    @Test
    public void testOnCreate() {
        solo.assertCurrentActivity("Wrong Activity", LoginCareProviderActivity.class);
    }

    /**
     * Tests a valid login. Should open the PatientListActivity.
     * Will be updated when the remote login validation is completed.
     */
    @Test
    public void testLoginSuccess() {
        // TODO: add hook to ensure test care provider is added to the remote or make some mock
        solo.enterText((EditText) solo.getView(R.id.enter_care_provider_username), "TESTCareProvider1");
        solo.enterText((EditText) solo.getView(R.id.enter_care_provider_password), "foobar123");
        solo.clickOnView(solo.getView(R.id.login_care_provider_button));
        solo.assertCurrentActivity("Wrong Activity", PatientListActivity.class);
    }

    /**
     * Tests an invalid login attempt. Should not open a new activity.
     * Will be updated when the remote login validation is completed.
     */
    @Test
    public void testLoginFail() {
        solo.enterText((EditText) solo.getView(R.id.enter_care_provider_username), "TESTCareProvider1");
        solo.enterText((EditText) solo.getView(R.id.enter_care_provider_password), "BADPASSWORD");
        solo.clickOnView(solo.getView(R.id.login_care_provider_button));
        solo.waitForText(getActivity().getResources().getString(R.string.login_failed));
        solo.assertCurrentActivity("Wrong Activity", LoginCareProviderActivity.class);
    }

    /**
     * Tests clicking the sign up button. Should open the CreateCareProviderAccountActivity.
     */
    @Test
    public void testRegister() {
        solo.clickOnView(solo.getView(R.id.register_care_provider_button));
        solo.assertCurrentActivity("Wrong Activity", CreateCareProviderAccountActivity.class);
    }
}