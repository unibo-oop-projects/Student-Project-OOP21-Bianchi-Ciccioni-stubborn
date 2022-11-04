package models;

public class PlayerImpl implements Player {

    private Point2D position;
    private int health;
    private static final int MAXHEALTH = 3;

    public PlayerImpl(final Point2D position, final int health) {
        this.position = position;
        this.health = health;
    }

    /**
     * 
     */
    @Override
    public int getHealth() {
        return this.health;
    }

    /**
     * 
     */
    @Override
    public void setHealth(final int value) {
        if (this.health + value < MAXHEALTH) {
            this.health = this.health + value;
        }
    }

    /**
     * 
     */
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * 
     */
    @Override
    public void setPosition(final Point2D position) {
        this.position = position;
    }

}
