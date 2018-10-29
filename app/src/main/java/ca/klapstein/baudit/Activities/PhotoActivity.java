package ca.klapstein.baudit.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ca.klapstein.baudit.R;

public class PhotoActivity extends AppCompatActivity {
    private static final String TAG = "PhotoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
    }
}
