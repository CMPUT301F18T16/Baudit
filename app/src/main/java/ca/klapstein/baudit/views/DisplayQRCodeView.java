package ca.klapstein.baudit.views;

import android.graphics.Bitmap;

public interface DisplayQRCodeView extends View {
    void setQRCodeImage(Bitmap bitmap);
    void setQRCodeError();
}
