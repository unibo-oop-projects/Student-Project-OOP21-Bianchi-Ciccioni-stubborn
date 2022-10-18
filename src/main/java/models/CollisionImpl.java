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
    
}
