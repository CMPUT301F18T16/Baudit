package ca.klapstein.baudit.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.Data.Record;
import ca.klapstein.baudit.R;

abstract public class RecordActivity extends AppCompatActivity {
    private static final String TAG = "RecordActivity";

    private Record record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
    }
}
