package models;

import java.util.Map;
import java.util.Optional;

public interface AiEnemy {
    
    public Point2D move(Map<Point2D,Optional<Entity>> board, Point2D playerPosition, Point2D position);
    
}
