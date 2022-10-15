package models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class FocusAiEnemy implements AiEnemy {

    @Override
    public Point2D move(Map<Point2D, Optional<Entity>> board, Point2D playerPosition, Point2D position) {
        List<MOVEMENT> movements = Arrays.asList(MOVEMENT.UP, MOVEMENT.DOWN, MOVEMENT.LEFT, MOVEMENT.RIGHT);
        Map<Point2D, Integer> open = getDirections(movements, position);
        Point2D q = new Point2D(-1, -1);
        int g;
        int fMin = 100000;
        
        for(var e : open.entrySet()) {
            g = ((e.getKey().getX() - playerPosition.getX()) + (e.getKey().getY() - playerPosition.getY()));
            e.setValue(g);
        }
        
        for(var e : open.entrySet()) {
            if(e.getValue() < fMin) {
                fMin = e.getValue();
                q = e.getKey();
            }
        }
        
        return q;
    }
    
    private Map<Point2D, Integer> getDirections(List<MOVEMENT> movements, Point2D position) {
        Map<Point2D, Integer> directions = new HashMap<>();
        for(MOVEMENT i : movements) {
            directions.put(Point2D.sum(position, i.movement), 0);
        }
        return directions;
    }

}
