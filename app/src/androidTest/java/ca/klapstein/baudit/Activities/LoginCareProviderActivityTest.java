package ca.klapstein.baudit.Activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class LoginCareProviderActivityTest extends ActivityTestRule<LoginCareProviderActivity> {

    private Solo solo;

    LoginCareProviderActivityTest() {
        super(ca.klapstein.baudit.Activities.LoginCareProviderActivity.class);
    }

    @Before
    public void setUp() {
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void onCreate() {
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
    }

    @Test
    public void onLogin() {
    }
}