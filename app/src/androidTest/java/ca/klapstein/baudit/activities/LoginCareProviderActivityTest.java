package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
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
        solo.assertCurrentActivity("Wrong Activity", LoginCareProviderActivity.class);
    }

    /**
     * Tests clicking the sign up button. Should open the CreateCareProviderAccountActivity.
     */
    @Test
    public void testRegister() {
        solo.clickOnView(solo.getView(R.id.register_account_button));
        solo.waitForActivity(CreateCareProviderAccountActivity.class);
        solo.assertCurrentActivity("Wrong Activity", CreateCareProviderAccountActivity.class);
    }

    @Test
    public void SwitchLoginScreen() {
        solo.clickOnView(solo.getView(R.id.log_in_other_button));
        solo.waitForActivity(LoginPatientActivity.class);
        // TODO: this test seems to fail on the ci but not in dev environ
        solo.assertCurrentActivity("Wrong Activity", LoginPatientActivity.class);
    }
}