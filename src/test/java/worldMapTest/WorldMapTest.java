package worldMapTest;

import org.junit.jupiter.api.Test;

import models.Enemy;
import models.Entity;
import models.MOVEMENT;
import models.Pair;
import models.Player;
import models.PlayerImpl;
import models.Point2D;
import models.RandomSpawnStrategy;
import models.SpawnStrategy;
import models.WorldMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class WorldMapTest {
    
    private WorldMap worldMap = new WorldMap(50,50,5,15);
    private SpawnStrategy randomStrategy = new RandomSpawnStrategy();
    Point2D startPlayerPos = new Point2D(25,25);
    
    
    @Test
    public void testRandomSpawnStrategy() {
        Set<Point2D> set1 = this.randomStrategy.getSpawnPoints(10, 10, 5);
        Point2D el = set1.iterator().next();
        Set<Point2D> set2 = this.randomStrategy.getSpawnPoints(10, 10, 4);
        set2.add(el);
        System.out.println(set2.size());
        Set<Point2D> allSet = this.randomStrategy.getDoubleSpawnPoints(10, 10, set1, set2);
        assertEquals(5,set1.size());
        assertEquals(10,allSet.size());
        assertTrue(allSet.containsAll(set1));
        assertTrue(allSet.containsAll(set2));
    }
    
    @Test
    public void testWorldMapCreation() {
        Map<Point2D,Optional<Entity>> board = worldMap.getBoard();
        long count = board.values().stream().filter(v -> v.isPresent()).count();
        assertEquals(51*51,board.size());
        assertTrue(board.get(startPlayerPos).get() instanceof Player);
        assertEquals(21,count);
    }
    
    @Test
    public void testMovePlayer() {
        worldMap.movePlayer(MOVEMENT.LEFT);
        worldMap.movePlayer(MOVEMENT.UP);
        Map<Point2D,Optional<Entity>> board = worldMap.getBoard();
        Point2D leftPosition = new Point2D(24,26);
        assertTrue(board.get(leftPosition).isPresent());
        assertTrue(board.get(leftPosition).get() instanceof Player);
        assertTrue(board.get(startPlayerPos).isEmpty());
        
    }
    

}
