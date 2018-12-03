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

    /**
     * Get the label of the {@code BodyLocationPhoto}.
     *
     * @return {@code String}
     */
    @NonNull
    public String getLabel() {
        return label;
    }

    /**
     * Setter for a {@code BodyLocationPhoto}'s label.
     *
     * @param label {@code String}
     */
    public void setLabel(@NonNull String label) {
        this.label = label;
    }
}
