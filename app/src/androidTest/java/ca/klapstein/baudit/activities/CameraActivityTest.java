package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.*;
import ca.klapstein.baudit.models.DataModel;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class CameraActivityTest extends ActivityTestRule<CameraActivity> {

    private Solo solo;
    private DataModel dataModel;

    public CameraActivityTest() {
        super(CameraActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        dataModel = new DataModel(InstrumentationRegistry.getTargetContext());
        dataModel.setOfflineLoginAccount(new Patient(
                new Username("TESTPatient1"),
                new ContactInfo("John", "Smith", new Email("patient@example.com"), new PhoneNumber("111-111-1111"))
        ));
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() throws Exception {
        dataModel.clearOfflineLoginAccount();
        solo.finishOpenedActivities();
    }

    @Test
    public void onCreate() {
        solo.assertCurrentActivity("Wrong activity", CameraActivity.class);
        solo.waitForActivity(solo.getCurrentActivity().toString());
    }

    @Test
    public void onSwitchCamera() {
        solo.clickOnView(solo.getView(R.id.switch_camera_button));
        solo.waitForActivity(solo.getCurrentActivity().toString());
    }

    @Test
    public void onOrientationChange() {
        solo.setActivityOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        solo.setActivityOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        solo.setActivityOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        solo.waitForActivity(solo.getCurrentActivity().toString());
    }
}