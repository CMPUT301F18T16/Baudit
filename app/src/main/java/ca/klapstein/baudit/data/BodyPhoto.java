package ca.klapstein.baudit.data;

import android.graphics.Bitmap;

/**
 * Data class representing a {@code Patient}'s photo of their own body.
 * <p>
 * A {@code Patient} can only set one {@code BodyPhoto} of themselves to maintain consistency.
 *
 * @see Patient
 */
public class BodyPhoto implements Photo {
    private static final String TAG = "BodyPhoto";

    private Bitmap bitmap;

    public BodyPhoto(Bitmap bitmap) throws IllegalArgumentException {
        this.setBitmap(bitmap);
    }

    /**
     * Check if a given {@code Bitmap} image is valid for usage as a {@code BodyPhoto}.
     *
     * @param bitmap {@code Bitmap} the Bitmap image to validate
     * @return {@code boolean} {@code true} if the {@code Bitmap} is valid, otherwise {@code false}
     */
    static public boolean isValid(Bitmap bitmap) {
        // TODO: implement
        return true;
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public void setBitmap(Bitmap bitmap) throws IllegalArgumentException {
        if (!isValid(bitmap)) {
            throw new IllegalArgumentException("invalid bitmap for RecordPhoto");
        } else {
            this.bitmap = bitmap;
        }
    }
}
