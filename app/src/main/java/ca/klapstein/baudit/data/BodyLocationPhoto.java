package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public class BodyLocationPhoto extends RecordPhoto {

    @NonNull
    private String label;

    public BodyLocationPhoto(@NonNull Bitmap bitmap, @NonNull String label) {
        super(bitmap);
        this.label = label;
    }

    @NonNull
    public String getLabel() {
        return label;
    }

    public void setLabel(@NonNull String label) {
        this.label = label;
    }
}
