package models;
/*
 * robe fighissime
 */

public enum MOVEMENT {
    LEFT(-1,0),
    RIGHT(1,0),
    UP(0,1),
    DOWN(0,-1);
    
    public final int x;
    public final int y;

    MOVEMENT(int offsetX, int offsetY) {
        this.x = offsetX;
        this.y = offsetY;
    }
}
