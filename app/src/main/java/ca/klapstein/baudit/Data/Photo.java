package ca.klapstein.baudit.Data;

import android.media.Image;
import android.support.annotation.NonNull;

abstract public class Photo{ // Todo: "extends Image' to be added later
    private Photo photo;

    public Photo(@NonNull Photo photo) throws IllegalArgumentException{
        this.setPhoto(photo);
    }

    public Photo getPhoto(){
        return this.photo;
    }

    public void setPhoto(@NonNull Photo photo) throws IllegalArgumentException{
        this.photo = photo;
    }
    public int size(Photo photo){
        return 1;
    }

}
