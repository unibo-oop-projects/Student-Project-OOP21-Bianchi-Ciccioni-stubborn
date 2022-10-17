package models;

public class EnemyImpl implements Enemy {
    
    private Point2D position;
    private int health;
    private final static int MAXHEALTH = 1;
    private AiEnemy aiEnemy;
    
    public EnemyImpl(Point2D position, int health, AiEnemy aiEnemy) {
        this.position = position;
        this.health = health;
        this.aiEnemy = aiEnemy;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int value) {
        if(this.health < 1) {
            this.health = this.health + value;
        }
    }

    @Override
    public Point2D getPosition() {
        // TODO Auto-generated method stub
        return this.position;
    }

    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

}
