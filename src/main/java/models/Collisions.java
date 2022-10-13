package models;

import java.util.Optional;

public interface Collisions {
    public Pair<Integer,Integer> checkEnemyCollision();

    public Integer checkCollectableCollision();

    public void playerCollision(Pair<Integer,Integer> tileReached, Entity tileContent);

    public void enemyCollision(Pair<Integer,Integer> tilePosition, Entity tileContent);

    public boolean checkMovement(Pair<Integer,Integer> tilePosition, Pair<Integer,Integer> tileReached, Integer boundX, Integer boundY, Optional<Entity> tileContent, boolean type);

}
