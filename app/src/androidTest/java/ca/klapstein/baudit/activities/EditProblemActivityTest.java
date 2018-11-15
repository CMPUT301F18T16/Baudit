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
public class EditProblemActivityTest extends ActivityTestRule<EditProblemActivity> {

    private Solo solo;

    public EditProblemActivityTest() {
        super(EditProblemActivity.class);
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
        solo.assertCurrentActivity("Wrong Activity", EditProblemActivity.class);
    }

    @Test
    public void testNewRecord() {
        solo.clickOnView(solo.getView(R.id.edit_problem_add_record));
        solo.waitForActivity(EditRecordActivity.class);
        solo.assertCurrentActivity("Wrong Activity", EditRecordActivity.class);
    }
}