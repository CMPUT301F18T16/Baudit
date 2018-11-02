package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.adapters.ProblemAdapter;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.presenters.ProblemListPresenter;
import ca.klapstein.baudit.views.ProblemListView;

/**
 * Activity for listing {@code Problem}s.
 *
 * @see ca.klapstein.baudit.data.Problem
 */
public class ProblemListActivity extends AppCompatActivity implements DeleteProblemDialog.onDeleteProblemListener, ProblemListView {
    private static final String TAG = "ProblemListActivity";

    private ProblemTreeSet problemTreeSet;
    private ProblemAdapter problemAdapter;
    private RecyclerView problemRecyclerView;
    private ProblemListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);

        // TODO: get problemTreeSet from local storage/remote
        problemAdapter = new ProblemAdapter(problemTreeSet);

        presenter = new ProblemListPresenter(this);
        // TODO: init problemRecyclerView
    }

    /**
     * Launch a EditProblemActivity with the selected Problem noted by its position within the {@code ProblemTreeSet}.
     *
     * @param view     {@code View}
     * @param position {@code int} the position of the selected Problem within the {@code ProblemTreeSet}.
     */
    private void onProblemListItemClick(View view, final int position) {
        Intent intent = new Intent(this, EditProblemActivity.class);
        // TODO: add required args
        startActivity(intent);
    }

    /**
     * Launch a AddProblemActivity.
     */
    private void onAddProblemClick() {
        Intent intent = new Intent(this, AddProblemActivity.class);
        // TODO: add required args
        startActivity(intent);
    }

    /**
     * Dialog callback for when a problem is deleted.
     *
     * @param problem {@code Problem}
     */
    @Override
    public void onDeleteProblem(Problem problem) {
        // TODO: implement
    }

    @Override
    public void addProblem(Problem problem) {

    }

    @Override
    public void editProblem(Problem problem, int position) {

    }

    @Override
    public void setProblemList(ProblemTreeSet problemTreeSet) {

    }
}
