package ca.klapstein.baudit.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.views.SlideShowView;

public class SlideshowAdapter extends PagerAdapter implements SlideShowView {

    private int[] image_resource = {R.drawable.sample1,R.drawable.sample2,R.drawable.sample3,R.drawable.sample4, R.drawable.sample5 }; //replace to list of records
    private Context context;
    private LayoutInflater layoutInflater;

    //imageResource = getRecordPhotoList

    public SlideshowAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return image_resource.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view ==(ConstraintLayout)object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view =layoutInflater.inflate(R.layout.slideshow_layout,container,false);

        ImageView imageView = item_view.findViewById(R.id.image_view);  //Change to grabbing from list
        imageView.setImageResource(image_resource[position]);

        container.addView(item_view);
        return item_view;
    }

    @Override
    public void updateImageList(ArrayList<Bitmap> images) {

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);

    }


    @Override
    public void onStart() {

    }
}
