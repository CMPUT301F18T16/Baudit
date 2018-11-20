package ca.klapstein.baudit.data;

import android.graphics.Bitmap;

/**
 * Data class representing a {@code Record}'s photo.
 * <p>
 * A {@code Record} can optionally have a {@code RecordPhoto}.
 *
 * @see Record
 */
public class RecordPhoto implements Photo {
    private static final String TAG = "RecordPhoto";

    private Bitmap bitmap;

    public RecordPhoto(Bitmap bitmap) throws IllegalArgumentException {
        this.setBitmap(bitmap);
    }

    /**
     * Check if a given {@code Bitmap} image is valid for usage as a {@code RecordPhoto}.
     *
     * @param bitmap {@code Bitmap} the Bitmap image to validate
     * @return {@code boolean} {@code true} if the {@code Bitmap} is valid, otherwise {@code false}
     */
    static public boolean isValid(Bitmap bitmap) {
        // TODO: implement
        return true;
    }

    /**
     * Get the {@code Bitmap} of the {@code RecordPhoto}
     *
     * @return {@code Bitmap}
     */
    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * Setter for a {@code RecordPhoto}'s {@code Bitmap}.
     *
     * @param bitmap {@code Bitmap}
     */
    @Override
    public void setBitmap(Bitmap bitmap) throws IllegalArgumentException {
        if (!isValid(bitmap)) {
            throw new IllegalArgumentException("invalid bitmap for RecordPhoto");
        } else {
            this.bitmap = bitmap;
        }
    }
}
