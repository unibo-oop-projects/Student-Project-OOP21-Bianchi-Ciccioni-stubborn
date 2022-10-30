package collisionsTest;

import org.junit.jupiter.api.Test;

import models.CollisionImpl;
import models.CollisionStrategy;
import models.Entity;
import models.MOVEMENT;
import models.Player;
import models.Point2D;
import models.RandomSpawnStrategy;
import models.SpawnStrategy;
import models.WorldMap;
import models.WorldMapImpl;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionTest {
    
    static int WIDTH = 3;
    static int HEIGHT = 3;
    static int NUM_ENEMIES = 7;
    static int NUM_COLLECTABLES = 1;
    
    CollisionStrategy collisions = new CollisionImpl();
    SpawnStrategy strategy = new RandomSpawnStrategy();
    WorldMap worldMap = new WorldMapImpl(WIDTH,HEIGHT,NUM_ENEMIES,NUM_COLLECTABLES, strategy);
    
    @Test
    public void testCollision() {
        Point2D playerPos = new Point2D(WIDTH/2,HEIGHT/2);
        worldMap.movePlayer(MOVEMENT.LEFT);
        Map<Point2D,Optional<Entity>> board = worldMap.getBoard();
        System.out.println(board);
        assertTrue(board.get(playerPos).get() instanceof Player);
    }
}
