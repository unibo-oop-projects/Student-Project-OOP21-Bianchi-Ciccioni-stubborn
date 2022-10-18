package entitiesTest;

import org.junit.jupiter.api.Test;

import models.AiEnemy;
import models.Collectable;
import models.CollectableImpl;
import models.Enemy;
import models.EnemyImpl;
import models.Entity;
import models.FocusAiEnemy;
import models.Player;
import models.PlayerImpl;
import models.Point2D;
import models.RandomAiEnemy;
import models.RandomSpawnStrategy;
import models.SpawnStrategy;
import models.WorldMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    
    private Point2D startPlayerPosition = new Point2D(1,1);
    private int PlayerHealth = 3;
    private Player p = new PlayerImpl(startPlayerPosition, PlayerHealth);
    
    private Point2D startEnemy1Position = new Point2D(5,5);
    private int Enemy1Health = 1;
    
    private Point2D startEnemy2Position = new Point2D(5,5);
    private int Enemy2Health = 1;
    
    private AiEnemy ai1 = new RandomAiEnemy();
    private Enemy e1 = new EnemyImpl(startEnemy1Position, Enemy1Health, ai1);
    
    private AiEnemy ai2 = new FocusAiEnemy();
    private Enemy e2 = new EnemyImpl(startEnemy2Position, Enemy2Health, ai2);
    
    static int WIDTH = 51;
    static int HEIGHT = 51;
    static int NUM_ENEMIES = 5;
    static int NUM_COLLECTABLES = 15;
    
    private SpawnStrategy randomStrategy = new RandomSpawnStrategy();
    private WorldMap worldMap = new WorldMap(WIDTH,HEIGHT,NUM_ENEMIES,NUM_COLLECTABLES, randomStrategy);
    
    @Test
    public void testRandomAiEnemy() {
        Map<Point2D,Optional<Entity>> board = worldMap.getBoard();
        Point2D newEnemyPos = e1.getAi().move(board, startPlayerPosition, startEnemy1Position);
        e1.setPosition(newEnemyPos);
        assertEquals(e1.getAi(), ai1);
        assertNotEquals(e1.getPosition(), startEnemy1Position);
    }
    
    @Test
    public void testFocusAiEnemy() {
        //int distance = ((startEnemy2Position.getX() – startPlayerPosition.getX()) + (startEnemy2Position.getY() – startPlayerPosition.getY()));
        Map<Point2D,Optional<Entity>> board = worldMap.getBoard();
        Point2D newEnemyPos = e2.getAi().move(board, startPlayerPosition, startEnemy2Position);
        e2.setPosition(newEnemyPos);
        assertEquals(e1.getAi(), ai1);
        assertEquals(e1.getPosition(), startEnemy2Position);
    }

}
