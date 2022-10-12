package worldMapTest;

import org.junit.jupiter.api.Test;

import models.Enemy;
import models.Entity;
import models.MOVEMENT;
import models.Pair;
import models.Player;
import models.PlayerImpl;
import models.RandomSpawnStrategy;
import models.SpawnStrategy;
import models.WorldMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class worldMapTest {
    
    private WorldMap worldMap = new WorldMap();
    private SpawnStrategy randomStrategy = new RandomSpawnStrategy();
    Pair<Integer,Integer> startPlayerPos = new Pair<>(25,25);
    
    
    @Test
    public void testRandomSpawnStrategy() {
        Set<Pair<Integer,Integer>> set1 = this.randomStrategy.getSpawnPoints(10, 10, 5);
        Set<Pair<Integer,Integer>> set2 = this.randomStrategy.getSpawnPoints(10, 10, 5);
        Set<Pair<Integer,Integer>> allSet = this.randomStrategy.getDoubleSpawnPoints(10, 10, set1, set2);
        assertEquals(5,set1.size());
        assertEquals(10,allSet.size());
        assertTrue(allSet.containsAll(set1));
        assertTrue(allSet.containsAll(set2));
    }
    
    @Test
    public void testWorldMapCreation() {
        Map<Pair<Integer,Integer>,Optional<Entity>> board = worldMap.getBoard();
        System.out.println(board);
        System.out.println(board.size());
        assertEquals(51*51,board.size());
        assertTrue(board.get(startPlayerPos).get() instanceof Player);
    }
    
    @Test
    public void testMovePlayer() {
        worldMap.movePlayer(MOVEMENT.LEFT);
        worldMap.movePlayer(MOVEMENT.UP);
        Map<Pair<Integer,Integer>,Optional<Entity>> board = worldMap.getBoard();
        Pair<Integer,Integer> leftPosition = new Pair<>(24,26);
        assertTrue(board.get(leftPosition).isPresent());
        assertTrue(board.get(leftPosition).get() instanceof Player);
        assertTrue(board.get(startPlayerPos).isEmpty());
        
    }
    

}
