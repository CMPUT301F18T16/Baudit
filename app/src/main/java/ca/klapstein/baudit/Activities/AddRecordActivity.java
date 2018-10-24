package ca.klapstein.baudit.Activities;

import android.os.Bundle;
import ca.klapstein.baudit.R;

public class AddRecordActivity extends RecordActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
    }
}
