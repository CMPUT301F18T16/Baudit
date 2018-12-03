package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

public class BodyLocationPhoto {

    @NonNull
    private Bitmap photo;
    @NonNull
    private String label;

    public BodyLocationPhoto(@NonNull Bitmap photo, @NonNull String label) {
        setPhoto(photo);
        this.label = label;
    }

    @NotNull
    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(@NotNull Bitmap photo) {
        this.photo = photo;
    }

    @NotNull
    public String getLabel() {
        return label;
    }

    public void setLabel(@NotNull String label) {
        this.label = label;
    }
}
