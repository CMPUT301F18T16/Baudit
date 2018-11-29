package ca.klapstein.baudit.views;

import android.graphics.Bitmap;

import java.util.ArrayList;

public interface SlideShowView extends View {
    void updateImageList(ArrayList<Bitmap> images);
}
