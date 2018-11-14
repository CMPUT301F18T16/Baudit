package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.klapstein.baudit.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class PatientHomeActivityTest extends ActivityTestRule<PatientHomeActivity> {

    private Solo solo;

    public PatientHomeActivityTest() {
        super(PatientHomeActivity.class);
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
        solo.assertCurrentActivity("Wrong Activity", PatientHomeActivity.class);
    }

    @Test
    public void testOpenMapView() {
        solo.clickOnView(solo.getView(R.id.patient_home_view_map));
        solo.waitForActivity(MapAllProblemsActivity.class);
        solo.assertCurrentActivity("Wrong Activity", MapAllProblemsActivity.class);
    }

    @Test
    public void testEditAccount() {
        solo.clickOnActionBarHomeButton();
        solo.clickOnMenuItem(getActivity().getResources().getString(R.string.edit_account));
        solo.waitForActivity(EditAccountActivity.class);
        solo.assertCurrentActivity("Wrong Activity", EditAccountActivity.class);
    }

    @Test
    public void testLogOut() {
        solo.clickOnActionBarHomeButton();
        solo.clickOnMenuItem(getActivity().getResources().getString(R.string.log_out));
        solo.waitForActivity(LoginPatientActivity.class);
        solo.assertCurrentActivity("Wrong Activity", LoginPatientActivity.class);
    }
}