package ca.klapstein.baudit.views;

import android.graphics.Bitmap;

public interface DisplayQRCodeView extends View {
    void updateQRCodeImage(Bitmap bitmap);

    void updateQRCodeError();
}
