package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.*;
import ca.klapstein.baudit.models.DataModel;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        dataModel.clearOfflineLoginAccount();
        dataModel.setOfflineLoginAccount(new Patient(
            new Username("TESTPatient1"),
            new ContactInfo(
                "Test",
                "Patient",
                    new Email("patient@example.com"),
                new PhoneNumber("111-111-1111")
            )
        ));
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        dataModel.clearOfflineLoginAccount();
        solo.finishOpenedActivities();
    }

    @Test
    public void onCreate() {
        // TODO: some very advanced mocking needs to happen here to obtain some useful tests
        solo.assertCurrentActivity("Wrong Activity", EditAccountActivity.class);
    }

    @Test
    public void testEditAccountFirstName() {
        solo.assertCurrentActivity("Wrong Activity", EditAccountActivity.class);
        solo.clearEditText((EditText) solo.getView(R.id.edit_account_first_name_input));
        solo.enterText((EditText) solo.getView(R.id.edit_account_first_name_input), "Bobby");
        solo.clickOnView(solo.getView(R.id.edit_account_save_button));
        Account account = dataModel.getLoggedInAccount();
        assertNotNull(account);
        assertEquals("Bobby", account.getContactInfo().getFirstName());
        assertEquals("Patient", account.getContactInfo().getLastName());
    }

    @Test
    public void testEditAccountLastName() throws InterruptedException {
        solo.assertCurrentActivity("Wrong Activity", EditAccountActivity.class);
        solo.clearEditText((EditText) solo.getView(R.id.edit_account_last_name_input));
        solo.enterText((EditText) solo.getView(R.id.edit_account_last_name_input), "Smith");
        solo.clickOnView(solo.getView(R.id.edit_account_save_button));
        Thread.sleep(1000);
        Account account = dataModel.getLoggedInAccount();
        assertNotNull(account);
        assertEquals("Test", account.getContactInfo().getFirstName());
        assertEquals("Smith", account.getContactInfo().getLastName());
    }
}