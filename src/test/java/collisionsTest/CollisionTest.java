package collisionsTest;

import org.junit.jupiter.api.Test;

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
    static int ZERO = 0;
    static int NUM_ENEMIES = 7;
    static int NUM_COLLECTABLES = 1;
    
    SpawnStrategy strategy = new RandomSpawnStrategy();
    
    @Test
    public void testCollision() {
        WorldMap worldMap = new WorldMapImpl(WIDTH,HEIGHT,NUM_ENEMIES,NUM_COLLECTABLES, strategy);
        Point2D playerPos = new Point2D(WIDTH/2,HEIGHT/2);
        worldMap.movePlayer(MOVEMENT.LEFT);
        Map<Point2D,Optional<Entity>> board = worldMap.getBoard();
        assertTrue(board.get(playerPos).get() instanceof Player);
    }
    
    public void testBorderCollision() {
        WorldMap bigMap = new WorldMapImpl(WIDTH, HEIGHT, ZERO, ZERO, strategy);
        Point2D playerPos = Point2D.sum(new Point2D(WIDTH/2,HEIGHT/2), MOVEMENT.UP.movement);
        bigMap.movePlayer(MOVEMENT.UP);
        Map<Point2D,Optional<Entity>> board = bigMap.getBoard();
        assertTrue(board.get(playerPos).get() instanceof Player);
        bigMap.movePlayer(MOVEMENT.UP);
        bigMap.movePlayer(MOVEMENT.UP);
        bigMap.movePlayer(MOVEMENT.UP);
        bigMap.movePlayer(MOVEMENT.UP);
        playerPos = new Point2D(WIDTH/2, HEIGHT);
        assertTrue(board.get(playerPos).get() instanceof Player);
    }
    
    public void testCollectableCollision() {
        WorldMap collMap = new WorldMapImpl(WIDTH, HEIGHT, ZERO, NUM_ENEMIES + NUM_COLLECTABLES, strategy);
        Point2D playerPos = Point2D.sum(new Point2D(WIDTH/2,HEIGHT/2), MOVEMENT.UP.movement);
        collMap.movePlayer(MOVEMENT.UP);
        Map<Point2D,Optional<Entity>> board = collMap.getBoard();
        assertTrue(board.get(playerPos).get() instanceof Player);
        long count = board.values().stream().filter(v -> v.isPresent()).count();
        assertEquals(NUM_ENEMIES + NUM_COLLECTABLES, count);
    }
}
