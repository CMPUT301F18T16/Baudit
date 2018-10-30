package ca.klapstein.baudit.Activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AccountCreateActivityTest extends ActivityTestRule<AccountCreateActivity> {

    private Solo solo;

    public AccountCreateActivityTest() {
        super(ca.klapstein.baudit.Activities.AccountCreateActivity.class);
    }

    @Before
    public void setUp() {
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {
        solo.assertCurrentActivity("Wrong Activity", AccountCreateActivity.class);
    }
}