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
        solo = null;
    }

    @Test
    public void testOnCreate() {
        solo.assertCurrentActivity("Wrong Activity", LoginPatientActivity.class);
    }

    @Test
    public void testLoginSuccess() {
        solo.enterText((EditText) solo.getView(R.id.enter_patient_username), "test");
        solo.enterText((EditText) solo.getView(R.id.enter_patient_password), "foo");
        solo.clickOnView(solo.getView(R.id.login_patient_button));
        solo.waitForActivity(ProblemListActivity.class, 2000);
    }

    @Test
    public void testLoginFail() {
        solo.enterText((EditText) solo.getView(R.id.enter_patient_username), "wrong");
        solo.enterText((EditText) solo.getView(R.id.enter_patient_password), "wrong");
        solo.clickOnView(solo.getView(R.id.login_patient_button));
        solo.assertCurrentActivity("Wrong Activity", LoginPatientActivity.class);
    }

    @Test
    public void testRegister() {
        solo.clickOnView(solo.getView(R.id.login_patient_button));
        solo.waitForActivity(CreatePatientAccountActivity.class, 2000);
    }

    @Test
    public void testSwitchToCareProviderLogin() {
        solo.clickOnView(solo.getView(R.id.log_in_as_care_provider_button));
        solo.waitForActivity(LoginCareProviderActivity.class, 2000);
    }
}