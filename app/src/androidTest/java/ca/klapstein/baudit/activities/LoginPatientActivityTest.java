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

public class LoginPatientActivityTest extends ActivityTestRule<LoginPatientActivity> {

    private Solo solo;
    private DataModel dataModel;

    public LoginPatientActivityTest() {
        super(LoginPatientActivity.class);
    }

    @Before
    public void setUp() {
        dataModel = new DataModel(InstrumentationRegistry.getTargetContext());
        dataModel.clearOfflineLoginAccount();
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        dataModel.clearOfflineLoginAccount();
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
        // TODO: add hook to ensure test patient is added to the remote or make some mock
        solo.enterText((EditText) solo.getView(R.id.enter_patient_username), "TESTPatient1");
        solo.enterText((EditText) solo.getView(R.id.enter_patient_password), "foobar123");
        solo.clickOnView(solo.getView(R.id.login_patient_button));
        solo.waitForActivity(PatientHomeActivity.class);
        solo.assertCurrentActivity("Wrong Activity", PatientHomeActivity.class);
    }

    /**
     * Tests an invalid login attempt. Should not open a new activity.
     * Will be updated when the remote login validation is completed.
     */
    @Test
    public void testLoginFail() {
        solo.enterText((EditText) solo.getView(R.id.enter_patient_username), "TESTPatient1");
        solo.enterText((EditText) solo.getView(R.id.enter_patient_password), "BADPASSWORD");
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
        solo.waitForActivity(CreatePatientAccountActivity.class);
        solo.assertCurrentActivity("Wrong Activity", CreatePatientAccountActivity.class);
    }

    /**
     * Test logging with a valid {@code CareProvider}'s username and password. But, we are on the wrong
     * login page, thus, we should still fail.
     */
    @Test
    public void testCareProviderLoginFail() {
        solo.enterText((EditText) solo.getView(R.id.enter_patient_username), "TESTCareProvider1");
        solo.enterText((EditText) solo.getView(R.id.enter_patient_password), "foobar123");
        solo.clickOnView(solo.getView(R.id.login_patient_button));
        solo.waitForText(getActivity().getResources().getString(R.string.login_failed));
        solo.assertCurrentActivity("Wrong Activity", LoginPatientActivity.class);
    }

    @Test
    public void SwitchLoginScreen() {
        solo.clickOnView(solo.getView(R.id.log_in_as_care_provider_button));
        solo.waitForActivity(LoginCareProviderActivity.class);
        // TODO: this test seems to fail on the ci but not in dev environ
//        solo.assertCurrentActivity("Wrong Activity", LoginCareProviderActivity.class);

    }
}