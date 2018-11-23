package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;
import android.widget.TextView;
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


@RunWith(AndroidJUnit4.class)
public class ProblemActivityTest extends ActivityTestRule<ProblemActivity> {

    private Solo solo;
    private DataModel dataModel;

    public ProblemActivityTest() {
        super(ProblemActivity.class);
    }

    @Before
    public void setUp() {
        dataModel = new DataModel(InstrumentationRegistry.getTargetContext());
        dataModel.setOfflineLoginAccount(new Patient(
                new Username("TESTPatient1"),
                new ContactInfo(new Email("cp@example.com"), new PhoneNumber("111-111-1111"))
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
    public void testOnCreate() {
        solo.assertCurrentActivity("Wrong Activity", ProblemActivity.class);
    }

    @UiThreadTest
    public void testEditAndSaveTitle() {
        ((TextView)solo.getView(R.id.problem_title_view)).setText("Before");
        solo.clickOnView(solo.getView(R.id.problem_title_edit_button));
        solo.waitForText(getActivity().getResources().getString(R.string.edit_problem));
        solo.clearEditText((EditText)solo.getView(R.id.problem_title_edit_text));
        solo.enterText((EditText)solo.getView(R.id.problem_title_edit_text), "After");
        solo.clickOnView(solo.getView(R.id.problem_title_save_button));
        solo.waitForText("After");
        assertEquals(
            "After",
            ((TextView)solo.getView(R.id.problem_title_view)).getText().toString()
        );
    }

    @UiThreadTest
    public void testEditAndCancelTitle() {
        ((TextView)solo.getView(R.id.problem_title_view)).setText("Before");
        solo.clickOnView(solo.getView(R.id.problem_title_edit_button));
        solo.waitForText(getActivity().getResources().getString(R.string.edit_problem));
        solo.clearEditText((EditText)solo.getView(R.id.problem_title_edit_text));
        solo.enterText((EditText)solo.getView(R.id.problem_title_edit_text), "After");
        solo.clickOnView(solo.getView(R.id.problem_title_cancel_button));
        assertEquals(
            "Before",
            ((TextView)solo.getView(R.id.problem_title_view)).getText().toString()
        );
    }

    @UiThreadTest
    public void testEditAndSaveDescription() {
        ((TextView)solo.getView(R.id.problem_description_view)).setText("Before");
        solo.clickOnView(solo.getView(R.id.problem_description_edit_button));
        solo.waitForText(getActivity().getResources().getString(R.string.edit_problem));
        solo.clearEditText((EditText)solo.getView(R.id.problem_description_edit_text));
        solo.enterText((EditText)solo.getView(R.id.problem_description_edit_text), "After");
        solo.clickOnView(solo.getView(R.id.problem_description_save_button));
        solo.waitForText("After");
        assertEquals(
            "After",
            ((TextView)solo.getView(R.id.problem_description_view)).getText().toString()
        );
    }

    @UiThreadTest
    public void testEditAndCancelDescription() {
        ((TextView)solo.getView(R.id.problem_description_view)).setText("Before");
        solo.clickOnView(solo.getView(R.id.problem_description_edit_button));
        solo.waitForText(getActivity().getResources().getString(R.string.edit_problem));
        solo.clearEditText((EditText)solo.getView(R.id.problem_description_edit_text));
        solo.enterText((EditText)solo.getView(R.id.problem_description_edit_text), "After");
        solo.clickOnView(solo.getView(R.id.problem_description_cancel_button));
        assertEquals(
            "Before",
            ((TextView)solo.getView(R.id.problem_description_view)).getText().toString()
        );
    }

    @Test
    public void testNewRecord() {
        solo.clickOnView(solo.getView(R.id.problem_add_record_button));
        solo.waitForActivity(RecordActivity.class);
        solo.assertCurrentActivity("Wrong Activity", RecordActivity.class);
    }
}