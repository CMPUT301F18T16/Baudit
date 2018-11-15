package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.klapstein.baudit.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ViewProblemActivityTest extends ActivityTestRule<ViewProblemActivity> {

    private Solo solo;
    private TextView titleView;
    private EditText titleInput;
    private TextView descriptionView;
    private EditText descriptionInput;

    public ViewProblemActivityTest() {
        super(ViewProblemActivity.class);
    }

    @Before
    public void setUp() {
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
        titleView = (TextView) solo.getView(R.id.problem_title_view);
        titleInput = (EditText) solo.getView(R.id.problem_title_edit_text);
        descriptionView = (TextView) solo.getView(R.id.problem_description_view);
        descriptionInput = (EditText) solo.getView(R.id.problem_description_edit_text);
    }

    @After
    public void tearDown() {
        solo.finishOpenedActivities();
    }

    @Test
    public void testOnCreate() {
        solo.assertCurrentActivity("Wrong Activity", ViewProblemActivity.class);
    }

    @Test
    public void testEditAndSaveTitle() {
        titleView.setText("Before");
        solo.clickOnView(solo.getView(R.id.problem_title_edit_button));
        solo.waitForText(getActivity().getResources().getString(R.string.edit_problem));
        solo.clearEditText(titleInput);
        solo.enterText(titleInput, "After");
        solo.clickOnView(solo.getView(R.id.problem_title_save_button));
        solo.waitForText("After");
        assertEquals("After", titleView.getText().toString());
    }

    @Test
    public void testEditAndCancelTitle() {
        titleView.setText("Before");
        solo.clickOnView(solo.getView(R.id.problem_title_edit_button));
        solo.waitForText(getActivity().getResources().getString(R.string.edit_problem));
        solo.clearEditText(titleInput);
        solo.enterText(titleInput, "After");
        solo.clickOnView(solo.getView(R.id.problem_title_cancel_button));
        solo.waitForText("Before");
        assertEquals("Before", titleView.getText().toString());
    }

    @Test
    public void testEditAndSaveDescription() {
        descriptionView.setText("Before");
        solo.clickOnView(solo.getView(R.id.problem_description_edit_button));
        solo.waitForText(getActivity().getResources().getString(R.string.edit_problem));
        solo.clearEditText(descriptionInput);
        solo.enterText(descriptionInput, "After");
        solo.clickOnView(solo.getView(R.id.problem_description_save_button));
        solo.waitForText("After");
        assertEquals("After", descriptionView.getText().toString());
    }

    @Test
    public void testEditAndCancelDescription() {
        descriptionView.setText("Before");
        solo.clickOnView(solo.getView(R.id.problem_description_edit_button));
        solo.waitForText(getActivity().getResources().getString(R.string.edit_problem));
        solo.clearEditText(descriptionInput);
        solo.enterText(descriptionInput, "After");
        solo.clickOnView(solo.getView(R.id.problem_description_cancel_button));
        solo.waitForText("Before");
        assertEquals("Before", descriptionView.getText().toString());
    }


    @Test
    public void testNewRecord() {
        solo.clickOnView(solo.getView(R.id.problem_add_record_button));
        solo.waitForActivity(EditRecordActivity.class);
        solo.assertCurrentActivity("Wrong Activity", EditRecordActivity.class);
    }
}