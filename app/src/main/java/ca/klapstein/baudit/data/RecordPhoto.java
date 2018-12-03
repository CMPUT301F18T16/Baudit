package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import ca.klapstein.baudit.util.BitmapEncoderUtil;

public class RecordPhoto {
    private static final int MAX_PHOTO_BYTES = 65535;

    /**
     * Bitmap is saved as a base64 string for easy saving in local and remote storage.
     */
    private String bitmapString;

    public RecordPhoto(@NonNull Bitmap bitmap) {
        setPhoto(bitmap);
    }

    /**
     * Decode the {@code bitmapString} and return its representing {@code Bitmap} for usage.
     *
     * @return {@code Bitmap}
     */
    public Bitmap getBitmap() {
        return BitmapEncoderUtil.decodeBase64(bitmapString);
    }

    /**
     * Encode a given {@code Bitmap} into a base64 string and save it within the {@code bitmapString} field.
     *
     * @param bitmap {@code Bitmap}
     */
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
