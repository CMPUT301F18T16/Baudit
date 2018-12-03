package ca.klapstein.baudit.data;

import android.graphics.Bitmap;

public class BodyLocationPhoto {

    private Bitmap photo;
    private String label;

    public BodyLocationPhoto(Bitmap photo, String label) {
        setPhoto(photo);
        this.label = label;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
