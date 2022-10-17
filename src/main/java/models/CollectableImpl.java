package models;

import java.util.Random;

public class CollectableImpl implements Collectable{
    
    private Point2D position;
    private COLLECTABLETYPE type;
    
    public CollectableImpl() {
        type = COLLECTABLETYPE.getRandomType();
    }
    

    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

    @Override
    public int getPoints() {
        return type.getPoints();
    }

}
