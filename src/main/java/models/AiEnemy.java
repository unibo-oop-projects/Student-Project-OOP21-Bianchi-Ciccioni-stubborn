package models;

import java.util.Map;
import java.util.Optional;

public interface AiEnemy {
    
    public Pair<Integer, Integer> move(Map<Point2D,Optional<Entity>> board, Point2D playerPosition, Point2D position);
    
}
