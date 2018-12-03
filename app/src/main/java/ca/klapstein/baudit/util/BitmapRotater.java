package ca.klapstein.baudit.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class BitmapRotater {

    public static Bitmap RotateBitmap90(Bitmap source)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
}
