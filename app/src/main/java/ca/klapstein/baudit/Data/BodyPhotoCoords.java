package ca.klapstein.baudit.Data;

public class BodyPhotoCoords {
    private static BodyPhotoCoords ourInstance = new BodyPhotoCoords();

    private BodyPhotoCoords() {
    }

    public static BodyPhotoCoords getInstance() {
        return ourInstance;
    }
}
