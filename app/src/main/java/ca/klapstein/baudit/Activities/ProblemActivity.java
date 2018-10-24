package ca.klapstein.baudit.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.Data.Problem;
import ca.klapstein.baudit.R;

abstract public class ProblemActivity extends AppCompatActivity {
    private static final String TAG = "ProblemActivity";

    private Problem problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
    }
}
