package ca.klapstein.baudit.data;

/**
 * DataClass representing the coordinates of a location specified within a {@code BodyPhoto}.
 *
 * @see BodyPhoto
 */
public class BodyPhotoCoords {
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

    public void setCoords(int posX, int posY) throws IllegalArgumentException {
        if (!isValid(posX, posY)) {
            throw new IllegalArgumentException("Y coord < 0");
        }
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
