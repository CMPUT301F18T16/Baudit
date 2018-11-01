package ca.klapstein.baudit.Data;

public class BodyPhotoCoords{
    private int x;
    private int y;

    public BodyPhotoCoords(int x, int y) throws IllegalArgumentException{
        this.setX(x);
        this.setY(y);
    }

    public void setX(int x) throws  IllegalArgumentException{
        if(x <= 0) {
            throw new IllegalArgumentException("X coord < 0");
        }
        else {
            this.x = x;
        }
    }

    public void setY(int y)throws IllegalArgumentException{
        if(y <=0){
            throw new IllegalArgumentException("Y coord < 0");
        }
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
