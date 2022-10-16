package collisionsTest;

import org.junit.jupiter.api.Test;

import models.Collectable;
import models.CollectableImpl;
import models.CollisionImpl;
import models.CollisionStrategy;
import models.Entity;
import models.MOVEMENT;
import models.Pair;
import models.Player;
import models.Point2D;
import models.WorldMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class CollisionTest {
    
    CollisionStrategy collisions = new CollisionImpl();
    WorldMap worldMap = new WorldMap(3,3,8,0);
    
    @Test
    public void testCollision() {
        Point2D playerPos = new Point2D(1,1);
        worldMap.movePlayer(MOVEMENT.LEFT);
        Map<Point2D,Optional<Entity>> board = worldMap.getBoard();
        System.out.println(board);
        assertTrue(board.get(playerPos).get() instanceof Player);
    }
    
    

}
