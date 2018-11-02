package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.ProblemPresenter;
import ca.klapstein.baudit.views.ProblemView;

/**
 * Abstract Class for interacting with a instance of a {@code Problem}.
 *
 * @see ca.klapstein.baudit.data.Problem
 */
abstract public class ProblemActivity<P extends ProblemPresenter> extends AppCompatActivity implements ProblemView {
    private static final String TAG = "ProblemActivity";

    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
    }
}
