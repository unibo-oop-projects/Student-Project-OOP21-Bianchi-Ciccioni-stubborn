package models;
/*
 * robe fighissime
 */

public enum MOVEMENT {
    LEFT(-1,0),
    RIGHT(1,0),
    UP(0,1),
    DOWN(0,-1);
    
    public final Point2D movement;

    MOVEMENT(int offsetX, int offsetY) {
        this.movement = new Point2D(offsetX, offsetY);
    }
}
