package models;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class RandomAiEnemy implements AiEnemy {

    @Override
    public Point2D move(Map<Point2D,Optional<Entity>> board, Point2D playerPosition, Point2D position) {
        Random r = new Random();
        int randomSelect = r.nextInt(4);
        Point2D newPosition = position;
        
        switch(randomSelect) {
        case 0:
            newPosition = new Pair<>(position.getX()+MOVEMENT.LEFT.x, position.getY()+MOVEMENT.LEFT.y);
            break;
        case 1:
            newPosition = new Pair<>(position.getX()+MOVEMENT.RIGHT.x, position.getY()+MOVEMENT.RIGHT.y);
            break;
        case 2:
            newPosition = new Pair<>(position.getX()+MOVEMENT.UP.x, position.getY()+MOVEMENT.UP.y);
            break;
        case 3:
            newPosition = new Pair<>(position.getX()+MOVEMENT.DOWN.x, position.getY()+MOVEMENT.DOWN.y);
            break;
        }
        return newPosition;
    }
}
