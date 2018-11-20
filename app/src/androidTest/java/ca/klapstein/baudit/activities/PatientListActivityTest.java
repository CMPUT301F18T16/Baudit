package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import ca.klapstein.baudit.data.Username;
import ca.klapstein.baudit.models.DataModel;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class PatientListActivityTest extends ActivityTestRule<PatientListActivity> {

    private Solo solo;
    private DataModel dataModel;

    public PatientListActivityTest() {
        super(ca.klapstein.baudit.activities.PatientListActivity.class);
    }

    @Before
    public void setUp() {
        dataModel = new DataModel(InstrumentationRegistry.getTargetContext());
        dataModel.setLoginAccountUserName(new Username("TESTCareProvider1"));
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        dataModel.clearLoginAccountUserName();
        solo.finishOpenedActivities();
    }

    @Test
    public void onCreate() {
        solo.assertCurrentActivity("Wrong Activity", PatientListActivity.class);
    }
}