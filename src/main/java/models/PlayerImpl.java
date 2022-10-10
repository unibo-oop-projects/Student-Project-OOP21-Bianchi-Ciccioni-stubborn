package models;

public class PlayerImpl implements Player {
    
    private Pair<Integer, Integer> position;
    private int health;
    private final int maxHealth = 3;

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int value) {
        this.health = this.health + value;
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
