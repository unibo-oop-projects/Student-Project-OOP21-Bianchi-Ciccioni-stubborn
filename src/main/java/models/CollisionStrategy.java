package models;

import java.util.Map;
import java.util.Optional;

public interface CollisionStrategy {
    
    public boolean checkCollisions(Map<Point2D,Optional<Entity>> board, Point2D newPos, int width, int height);

}
