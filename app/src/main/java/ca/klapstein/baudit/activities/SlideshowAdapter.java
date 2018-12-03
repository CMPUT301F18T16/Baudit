package ca.klapstein.baudit.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.views.SlideShowView;

/**
 * Adapter providing a "slideshow" of photos to be easily viewed.
 */

public class SlideshowAdapter extends PagerAdapter implements SlideShowView {

    //Stored temporary photos in drawable to test slideshow
    //TEST: image_resource
    //private int[] image_resource = {R.drawable.baudit_logo_240x120, R.drawable.baudit_logo_240x120, R.drawable.baudit_logo_240x120};
    private ArrayList<String> bitmapStringArray = new ArrayList<String>();
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();


    public SlideshowAdapter(Context context, ArrayList<String> bitmapStringArray){
        this.context=context;
        this.bitmapStringArray = bitmapStringArray;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    @Override
    public int getCount() {
        //TODO: Get length of the photos stored in bitmap Array
        // return image_resource.length; TEST
        return bitmapArray.size();
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
        // imageView.setImageResource(image_resource[position]);  //TEST CASE

        //TODO:update empty bitmapArray & setImageResource to the images on the list
        updateImageList();
        imageView.setImageBitmap(bitmapArray.get(position));


        container.addView(item_view);
        return item_view;
    }


    /**
     * Get List of images of the record
     */
    @Override
    public void updateImageList() {
        for(String encodedString : bitmapStringArray){
            bitmapArray.add(decodeBase64(encodedString));
        }
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
