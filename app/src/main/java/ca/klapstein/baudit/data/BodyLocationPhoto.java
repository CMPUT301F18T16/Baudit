package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import ca.klapstein.baudit.util.BitmapEncoderUtil;

public class BodyLocationPhoto {
    @NonNull
    private String bitmapString;
    @NonNull
    private String label;

    public BodyLocationPhoto(@NonNull Bitmap bitmap, @NonNull String label) {
        setBitmap(bitmap);
        this.label = label;
    }

    public Bitmap getBitmap() {
        return BitmapEncoderUtil.decodeBase64(bitmapString);
    }

    public void setBitmap(@NonNull Bitmap bitmap) {
        bitmapString = BitmapEncoderUtil.encodeTobase64(bitmap);
    }

    @NonNull
    public String getLabel() {
        return label;
    }

    public void setLabel(@NonNull String label) {
        this.label = label;
    }
}
