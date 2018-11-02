package ca.klapstein.baudit.views;

import android.graphics.Bitmap;

import java.util.ArrayList;

public interface SlideShowView extends View {
    void setImages(ArrayList<Bitmap> images);
}
