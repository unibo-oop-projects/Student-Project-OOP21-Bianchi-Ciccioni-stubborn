package models;

public class CollectableImpl implements Collectable{
    
    private Pair<Integer,Integer> position;

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

}
