package models;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface CollisionStrategy<X> {
    /*
    public Pair<Integer,Integer> checkEnemyCollision();

    public Integer checkCollectableCollision();

    public void playerCollision(Pair<Integer,Integer> tileReached, Entity tileContent);

    public void enemyCollision(Pair<Integer,Integer> tilePosition, Entity tileContent);*/
    
    public boolean checkCollisions(Map<Point2D,Optional<Entity>> board, Point2D newPos, int width, int height);

    //public boolean checkMovement(Pair<Integer,Integer> tilePosition, Pair<Integer,Integer> tileReached, Integer boundX, Integer boundY, Optional<Entity> tileContent, boolean type);

}
