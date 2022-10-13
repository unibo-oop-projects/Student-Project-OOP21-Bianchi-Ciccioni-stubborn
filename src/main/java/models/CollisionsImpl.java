package models;

import java.util.Optional;

public class CollisionsImpl implements Collisions {
    private static final boolean PLAYER = true;
    public static Pair<Integer,Integer> enemyTile = null;
    public static Integer scoreGain = null;
    
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
    };
    
}
