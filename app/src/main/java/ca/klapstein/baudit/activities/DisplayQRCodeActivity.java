package ca.klapstein.baudit.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.DisplayQRCodePresenter;
import ca.klapstein.baudit.views.DisplayQRCodeView;

public class DisplayQRCodeActivity extends AppCompatActivity implements DisplayQRCodeView {

    DisplayQRCodePresenter presenter;
    ImageView accountQRCodeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_qr_code);
        presenter = new DisplayQRCodePresenter(this, this);
        accountQRCodeImage = findViewById(R.id.AccountQRCodeImageView);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted();
    }

    @Override
    public void setQRCodeImage(Bitmap bitmap) {
        accountQRCodeImage.setImageBitmap(bitmap);
    }

    @Override
    public void setQRCodeError() {
        // TODO: error
    }
}
