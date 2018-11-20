package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class AddPhotoActivityTest extends ActivityTestRule<AddPhotoActivity> {

    private Solo solo;

    public AddPhotoActivityTest() {
        super(AddPhotoActivity.class);
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
    public void onCreate() {
    }

    @Test
    public void setPhoto() {
    }

    @Test
    public void setPhotoError() {
        solo.assertCurrentActivity("Wrong Activity", AddPhotoActivity.class);
    }
}