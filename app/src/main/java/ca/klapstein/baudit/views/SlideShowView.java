package ca.klapstein.baudit.views;

import android.media.Image;

import java.util.ArrayList;

public interface SlideShowView extends View {
    void setImages(ArrayList<Image> images);
}
