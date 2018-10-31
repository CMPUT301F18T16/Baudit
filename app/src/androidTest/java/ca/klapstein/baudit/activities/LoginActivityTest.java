package ca.klapstein.baudit.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest extends ActivityTestRule<LoginActivity> {

    private Solo solo;

    public LoginActivityTest() {
        super(ca.klapstein.baudit.activities.LoginActivity.class);
    }

    @Before
    public void setUp() {
//        super.launchActivity(new Intent());
//        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void onCreate() {
//        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
    }
}