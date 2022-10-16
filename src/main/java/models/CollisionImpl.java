package models;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CollisionImpl implements CollisionStrategy {

    @Override
    public boolean checkCollisions(Map<Point2D, Optional<Entity>> board, Point2D newPos, int width, int height) {
        //TODO mettere tutto su una sola riga (usando un &&)
        if(newPos.getX() > width || newPos.getX() < 0 || newPos.getY() > height || newPos.getY() < 0) {
            return true;
        }
        return board.get(newPos).isPresent() && board.get(newPos).get() instanceof Enemy;
    }
    
    
    
    /*
    private static final boolean PLAYER = true;
    public static Pair<Integer,Integer> enemyTile = null;
    public static Integer scoreGain = null;*/
    
    
    
    
    /*
    public Pair<Integer,Integer> checkEnemyCollision() {
        Pair<Integer,Integer> collisionTile = enemyTile;
        enemyTile = null;
        return collisionTile;
    }
    
    public Integer checkCollectableCollision() {
        Integer collisionScore = scoreGain;
        scoreGain = null;
        return collisionScore;
    }
    
    public void playerCollision(Pair<Integer,Integer> tileReached, Entity tileContent) {
        if (tileContent instanceof Enemy) {
            enemyTile = tileReached;
       } else {
            Collectable  coll = (Collectable) tileContent;
            scoreGain = coll.getPoints();
       }        
    }
    
    public void enemyCollision(Pair<Integer,Integer> tilePosition, Entity tileContent) {
        if (tileContent instanceof Player) {
            enemyTile = tilePosition;
        }        
    }
    
    public boolean checkMovement(Pair<Integer,Integer> tilePosition, Pair<Integer,Integer> tileReached, Integer boundX, Integer boundY, Optional<Entity> tileContent, boolean type) {
        if (tileReached.getX() < boundX && tileReached.getX() >= 0 && tileReached.getY() < boundY && tileReached.getY() >= 0) {
            if (!tileContent.isEmpty()) {
                if(type == PLAYER) {
                    playerCollision(tileReached, tileContent.get());
                } else {
                    enemyCollision(tilePosition, tileContent.get());
                    return false;
                }
            }
            return true;
        }
        return false;
    };*/
    
}
