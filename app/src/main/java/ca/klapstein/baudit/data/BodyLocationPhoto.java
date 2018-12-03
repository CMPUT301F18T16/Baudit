package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import ca.klapstein.baudit.util.BitmapEncoderUtil;

public class BodyLocationPhoto {

    private String bitmapString;

    private String label;

    public BodyLocationPhoto(@NonNull Bitmap bitmap, String label) {
        setBitmap(bitmap);
        this.label = label;
    }

    public Bitmap getBitmap() {
        return BitmapEncoderUtil.decodeBase64(bitmapString);
    }

    public void setBitmap(@NonNull Bitmap bitmap) {
        bitmapString = BitmapEncoderUtil.encodeTobase64(bitmap);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
