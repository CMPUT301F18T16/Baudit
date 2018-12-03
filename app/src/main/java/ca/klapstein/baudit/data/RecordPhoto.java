package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import ca.klapstein.baudit.util.BitmapEncoderUtil;

public class RecordPhoto {
    private static final int MAX_PHOTO_BYTES = 65535;

    private String bitmapString;

    public RecordPhoto(@NonNull Bitmap bitmap) {
        setPhoto(bitmap);
    }

    public Bitmap getBitmap() {
        return BitmapEncoderUtil.decodeBase64(bitmapString);
    }

    public void setPhoto(@NonNull Bitmap bitmap) {
        if (bitmap.getByteCount() > MAX_PHOTO_BYTES) {
            bitmapString = BitmapEncoderUtil.encodeTobase64(
                    ThumbnailUtils.extractThumbnail(bitmap, 255, 255)
            );
        } else {
            bitmapString = BitmapEncoderUtil.encodeTobase64(bitmap);
        }
    }
}
