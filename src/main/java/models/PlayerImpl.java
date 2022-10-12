package models;

public class PlayerImpl implements Player {
    
    private Pair<Integer, Integer> position;
    private int health;
    private final static int MAXHEALTH = 3;

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int value) {
        if(this.health + value < MAXHEALTH) {
            this.health = this.health + value;
        }
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

}
