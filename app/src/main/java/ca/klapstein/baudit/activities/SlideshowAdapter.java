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

/**
 * Adapter providing a "slideshow" of photos to be easily viewed.
 */

public class SlideshowAdapter extends PagerAdapter implements SlideShowView {

    //Stored temporary photos in drawable to test slideshow
    private int[] image_resource = {R.drawable.sample1,R.drawable.sample2,R.drawable.sample3,R.drawable.sample4, R.drawable.sample5 }; //TEST, Remove images when finished
    private ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();
    private Context context;
    private LayoutInflater layoutInflater;



    public SlideshowAdapter(Context context){
        this.context=context;
    }


    @Override
    public int getCount() {
        //TODO: Get length of the photos stored in bitmap Array
        return image_resource.length;  //TEStS
    }

    /**
     *  Determines whether a page View is associated with a specific key object as returned by instantiateItem(ViewGroup, int).
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view ==(ConstraintLayout)object);
    }


    /**
     * Create the page for the given position
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view =layoutInflater.inflate(R.layout.slideshow_layout,container,false);



        ImageView imageView = item_view.findViewById(R.id.image_view);
        imageView.setImageResource(image_resource[position]);  //TEST CASE

        //TODO:update empty bitmapArray & setImageResource to the images on the list
        //updateImageList(bitmapArray);
        //imageView.setImageResource(bitmapArray.get(position))


        container.addView(item_view);
        return item_view;
    }


    /**
     * Get List of images of the record
     */
    @Override
    public void updateImageList(ArrayList<Bitmap> images) {

        //TODO: Update ArrayList<Bitmap> with list of photos of the records, this.bitmapArray = record.getPhotoList()
    }

    /**
     * Remove a page for the given position
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }


    @Override
    public void onStart() {

    }
}
