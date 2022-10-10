package models;

public class EnemyImpl implements Enemy {
    
    private Pair<Integer, Integer> position;
    private AiEnemy aiEnemy = new AiEnemyImpl();

    @Override
    public int getHealth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setHealth(int value) {
        // TODO Auto-generated method stub
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
