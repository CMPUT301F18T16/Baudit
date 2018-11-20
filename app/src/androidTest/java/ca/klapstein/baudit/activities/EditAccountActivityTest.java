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
public class EditAccountActivityTest extends ActivityTestRule<EditAccountActivity> {

    private Solo solo;
    private DataModel dataModel;

    public EditAccountActivityTest() {
        super(ca.klapstein.baudit.activities.EditAccountActivity.class);
    }

    @Before
    public void setUp() {
        dataModel = new DataModel(InstrumentationRegistry.getTargetContext());
        dataModel.setLoginAccountUserName(new Username("TESTPatient1"));
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        dataModel.clearLoginAccountUserName();
    }

    @Test
    public void onCreate() {
        // TODO: some very advanced mocking needs to happen here to obtain some useful tests
        solo.assertCurrentActivity("Wrong Activity", EditAccountActivity.class);
    }
}