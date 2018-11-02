package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.views.SlideShowView;

/**
 * MVP presenter for presenting a series of images on a {@code SlideShowView}.
 *
 * @see SlideShowView
 */
public class SlideShowPresenter {
    private static final String TAG = "SlideShowPresenter";

    private SlideShowView view;

    public SlideShowPresenter(SlideShowView view) {
        this.view = view;
    }
}
