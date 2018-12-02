package ca.klapstein.baudit.data;

/**
 * DataClass representing the coordinates of a location specified for a body photo.
 */
public class BodyPhotoCoords {
    private static final String TAG = "BodyPhotoCoords";

    private int posX;
    private int posY;

    public BodyPhotoCoords(int posX, int posY) throws IllegalArgumentException {
        setCoords(posX, posY);
    }

    /**
     * Check that a the given integer pair can represent a {@code BodyPhotoCoords}.
     * <p>
     * Note: posX and posY must both be greater than or equal to 0.
     *
     * @param posX {@code int}
     * @param posY {@code int}
     * @return {@code boolean} {@code true} if the integer pair is valid, otherwise {@code false}.
     */
    static public boolean isValid(int posX, int posY) {
        return posX >= 0 && posY >= 0;
    }

    /**
     * Setter for a {@code BodyPhotoCoords}'s {@code posX} and {@code poxY}.
     *
     * @param posX {@code posX}
     * @param posY {@code posY}
     */
    public void setCoords(int posX, int posY) throws IllegalArgumentException {
        if (!isValid(posX, posY)) {
            throw new IllegalArgumentException("Y coord < 0");
        }
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Get the X-axis point of the body photo coordinate.
     *
     * @return {@code int}
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Get the Y-axis point of the body photo coordinate.
     *
     * @return {@code int}
     */
    public int getPosY() {
        return posY;
    }
}
