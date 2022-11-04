package models;


public class EnemyImpl implements Enemy {

    private Point2D position;
    private int health;
    private static final int MAXHEALTH = 1;
    private AiEnemy aiEnemy;

    public EnemyImpl(final Point2D position, final int health, final AiEnemy aiEnemy) {
        this.position = position;
        this.health = health;
        this.aiEnemy = aiEnemy;
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
        if (this.health < MAXHEALTH) {
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

    /**
     * 
     */
    @Override
    public AiEnemy getAi() {
        return this.aiEnemy;
    }

}
