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
public class ViewProblemActivityTest extends ActivityTestRule<ViewProblemActivity> {

    private Solo solo;

    public ViewProblemActivityTest() {
        super(ViewProblemActivity.class);
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
        solo.assertCurrentActivity("Wrong Activity", ViewProblemActivity.class);
    }

    @Test
    public void testNewRecord() {
        solo.clickOnView(solo.getView(R.id.edit_problem_add_record));
        solo.waitForActivity(EditRecordActivity.class);
        solo.assertCurrentActivity("Wrong Activity", EditRecordActivity.class);
    }
}