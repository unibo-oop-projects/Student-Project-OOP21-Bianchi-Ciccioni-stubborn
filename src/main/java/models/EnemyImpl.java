package models;

public class EnemyImpl implements Enemy {
    
    private Pair<Integer, Integer> position;
    private int health;
    private final static int MAXHEALTH = 1;
    private AiEnemy aiEnemy = new RandomAiEnemy();

    /* for now not implemented, we may not need it */
    @Override
    public int getHealth() {
        return this.health;
    }

    /* for now not implemented, we may not need it */
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
