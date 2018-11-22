package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.views.DisplayQRCodeView;
import net.glxn.qrgen.android.QRCode;

public class DisplayQRCodePresenter extends Presenter<DisplayQRCodeView> {
    public DisplayQRCodePresenter(DisplayQRCodeView view, Context context) {
        super(view, context);
    }

    public void viewStarted() {
        Account account = dataManager.getLoggedInAccount();
        if (account == null) {
            view.setQRCodeError();
        } else {
            Bitmap myBitmap = QRCode.from(account.getUsername().toString()).bitmap();
            view.setQRCodeImage(myBitmap);
        }
    }
}
