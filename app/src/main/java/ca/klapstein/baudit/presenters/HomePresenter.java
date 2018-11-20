package ca.klapstein.baudit.presenters;

import android.content.Context;

import ca.klapstein.baudit.views.HomeView;

public abstract class HomePresenter<V extends HomeView> extends Presenter<V> {

    public HomePresenter(V view, Context context) {
        super(view, context);
    }
}
