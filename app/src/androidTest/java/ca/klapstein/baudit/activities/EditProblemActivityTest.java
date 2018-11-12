package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class EditProblemActivityTest extends ActivityTestRule<EditProblemActivity> {

    private Solo solo;

    public EditProblemActivityTest() {
        super(EditProblemActivity.class);
    }

    @Before
    public void setUp() {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        solo.finishOpenedActivities();
    }

    @Test
    public void onCreate() {
        Intent intent = new Intent();
        intent.putExtra("problemId", 0);
        super.launchActivity(intent);
        solo.assertCurrentActivity("Wrong Activity", EditProblemActivity.class);
    }
}