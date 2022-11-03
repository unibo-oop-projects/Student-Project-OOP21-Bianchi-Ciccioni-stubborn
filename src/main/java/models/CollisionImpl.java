package models;

import java.util.Map;
import java.util.Optional;

public class CollisionImpl implements CollisionStrategy {

    @Override
    public boolean checkCollisions(Map<Point2D, Optional<Entity>> board, Point2D newPos, int width, int height) {
        if(newPos.getX() >= width || newPos.getX() < 0 || newPos.getY() >= height || newPos.getY() < 0) {
            return true;
        }
        return board.get(newPos).isPresent() && board.get(newPos).get() instanceof Enemy;
    }
    
}
