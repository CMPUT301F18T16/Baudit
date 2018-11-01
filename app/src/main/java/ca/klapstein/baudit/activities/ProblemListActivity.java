package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import ca.klapstein.baudit.Adapters.ProblemAdapter;
import ca.klapstein.baudit.Data.ProblemTreeSet;
import ca.klapstein.baudit.R;

/**
 * Activity for listing {@code Problem}s.
 *
 * @see ca.klapstein.baudit.Data.Problem
 */
public class ProblemListActivity extends AppCompatActivity {
    private static final String TAG = "ProblemListActivity";

    private ProblemTreeSet problemTreeSet;
    private ProblemAdapter problemAdapter;
    private RecyclerView problemRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);

        // TODO: get problemTreeSet from local storage/remote
        problemAdapter = new ProblemAdapter(problemTreeSet);

        // TODO: init problemRecyclerView
    }
}
