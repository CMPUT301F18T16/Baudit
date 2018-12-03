package ca.klapstein.baudit.views;

import android.graphics.Bitmap;

import java.util.ArrayList;

public interface SlideshowView extends View {
    void updateImageList(ArrayList<Bitmap> images);
}
