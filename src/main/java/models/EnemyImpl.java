package models;

import java.util.Random;

public class EnemyImpl implements Enemy {
    
    private Point2D position;
    private int health;
    private final static int MAXHEALTH = 1;
    private AiEnemy aiEnemy;
    
    public EnemyImpl(Point2D position, int health, AiEnemy aiEnemy) {
        this.position = position;
        this.health = health;
        
        Random r = new Random();
        int randomSelect = r.nextInt(2);
        this.aiEnemy = randomSelect == 0 ? new RandomAiEnemy() : new FocusAiEnemy();
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
