package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.views.SlideShowView;

/**
 * MVP presenter for presenting a series of images on a {@code SlideShowView}.
 *
 * @see SlideShowView
 */
public class SlideShowPresenter extends Presenter<SlideShowView> {
    private static final String TAG = "SlideShowPresenter";
    public SlideShowPresenter(SlideShowView view, Context context) {
        super(view, context);
    }
}
