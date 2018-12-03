package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.CareProviderProblemListPresenter;
import ca.klapstein.baudit.views.CareProviderProblemListView;

public class CareProviderProblemListActivity extends AppCompatActivity implements CareProviderProblemListView {

    private static final String TAG = "CareProviderProblemList";
    private CareProviderProblemListPresenter presenter;
    private String patientUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider_problem_list);
        presenter = new CareProviderProblemListPresenter(this, this);
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            patientUsername = bundle.getString("key");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void updateList() {

    }


}
