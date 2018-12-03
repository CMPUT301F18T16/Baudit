package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import ca.klapstein.baudit.util.BitmapEncoder;

public class BodyLocationPhoto {

    private String bitmapString;

    private String label;

    public BodyLocationPhoto(Bitmap photo, String label) {
        setPhoto(photo);
        this.label = label;
    }

    public Bitmap getPhoto() {
        return BitmapEncoder.decodeBase64(bitmapString);
    }

    public void setPhoto(Bitmap bitmap) {
        bitmapString = BitmapEncoder.encodeTobase64(bitmap);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
