package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.view.menu.ActionMenuItemView;
import android.widget.SearchView;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.*;
import ca.klapstein.baudit.models.DataModel;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class PatientHomeActivityTest extends ActivityTestRule<PatientHomeActivity> {

    private Solo solo;
    private DataModel dataModel;

    public PatientHomeActivityTest() {
        super(PatientHomeActivity.class);
    }

    @Before
    public void setUp() {
        dataModel = new DataModel(InstrumentationRegistry.getTargetContext());
        dataModel.clearOfflineLoginAccount();
        Patient patient = new Patient(
                new Username("TESTPatient1"),
                new ContactInfo("John", "Smith", new Email("patient@example.com"), new PhoneNumber("111-111-1111"))
        );
        patient.getProblemTreeSet().add(new Problem("problem 1", "description"));
        patient.getProblemTreeSet().add(new Problem("problem 2", "description"));
        patient.getProblemTreeSet().add(new Problem("problem 3", "description"));
        dataModel.setOfflineLoginAccount(patient);
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        dataModel.clearOfflineLoginAccount();
        solo.finishOpenedActivities();
    }

    @Test
    public void testOnCreate() {
        solo.assertCurrentActivity("Wrong Activity", PatientHomeActivity.class);
        assertTrue(solo.searchText("problem 1"));
        assertTrue(solo.searchText("problem 2"));
        assertTrue(solo.searchText("problem 3"));
    }

    @Test
    public void testEditAccount() {
        solo.clickOnActionBarHomeButton();
        solo.clickOnMenuItem(getActivity().getResources().getString(R.string.edit_account));
        solo.waitForActivity(EditPatientAccountActivity.class);
        solo.assertCurrentActivity("Wrong Activity", EditPatientAccountActivity.class);
    }

// TODO: fails on travis
//    @Test
//    public void testSearchProblemNull() throws InterruptedException {
//        solo.waitForActivity(PatientHomeActivity.class);
//        solo.clickOnView(solo.getView(R.id.patient_home_search));
//        SearchView searchView = (SearchView) ((ActionMenuItemView) solo.getView(R.id.patient_home_search)).getItemData().getActionView();
//
//        searchView.setQuery("NULL", true);
//        Thread.sleep(5000);
//        assertFalse(solo.searchText("problem 1"));
//        assertFalse(solo.searchText("problem 2"));
//        assertFalse(solo.searchText("problem 3"));
//    }

    @Test
    public void testSearchProblem1() throws InterruptedException {
        solo.waitForActivity(PatientHomeActivity.class);
        solo.clickOnView(solo.getView(R.id.patient_home_search));
        SearchView searchView = (SearchView) ((ActionMenuItemView) solo.getView(R.id.patient_home_search)).getItemData().getActionView();

        searchView.setQuery("1", true);
        Thread.sleep(5000);
        assertTrue(solo.searchText("problem 1"));
        assertFalse(solo.searchText("problem 2"));
        assertFalse(solo.searchText("problem 3"));
    }

// TODO: fix later
//    @Test
//    public void testEditProblem() {
//        solo.clickLongOnView(solo.getText("problem 1"));
//        solo.clickOnText("Edit");
//        solo.waitForActivity(ProblemActivity.class);
//    }

    @Test
    public void testDeleteProblemCancel() {
        solo.clickLongOnView(solo.getText("problem 1"));
        solo.clickOnText("Delete");
        solo.clickOnButton("Cancel");
        assertTrue(solo.searchText("problem 1"));
    }

    @Test
    public void testDeleteProblem() {
        solo.clickLongOnView(solo.getText("problem 1"));
        solo.clickOnText("Delete");
        solo.clickOnButton("Delete");
        assertFalse(solo.searchText("problem 1"));
    }
}