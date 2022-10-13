package models;

public class EnemyImpl implements Enemy {
    
    private Pair<Integer, Integer> position;
    private int health;
    private final static int MAXHEALTH = 1;
    private AiEnemy aiEnemy;
    
    public EnemyImpl(Pair<Integer, Integer> position, int health, AiEnemy aiEnemy) {
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
    public Pair<Integer, Integer> getPosition() {
        // TODO Auto-generated method stub
        return this.position;
    }

    @Override
    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

}
