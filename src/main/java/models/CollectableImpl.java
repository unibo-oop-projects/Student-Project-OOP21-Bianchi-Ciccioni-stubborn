package models;

import java.util.Random;

public class CollectableImpl implements Collectable{
    
    private Pair<Integer,Integer> position;
    private COLLECTABLETYPE type;
    
    public CollectableImpl() {
        type = COLLECTABLETYPE.getRandomType();
    }
    

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public int getPoints() {
        return type.getPoints();
    }

}
