package ca.klapstein.baudit.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * A base64 encoder/decoder of {@code Bitmap}.
 */
public class BitmapEncoder {
    /**
     * Encode a {@code Bitmap} into a base64 string.
     *
     * @param image {@code Bitmap}
     * @return {@code String}
     */
    @NonNull
    public static String encodeTobase64(@NonNull Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 90, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    /**
     * Decode a base64 string into a {@code Bitmap}.
     *
     * @param input {@code String}
     * @return {@code Bitmap}
     */
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
