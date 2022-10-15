package collisionsTest;

import org.junit.jupiter.api.Test;

import models.Collectable;
import models.CollectableImpl;
import models.CollisionImpl;
import models.CollisionStrategy;
import models.Entity;
import models.Pair;
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
    //WorldMap worldMap = new WorldMap(4,4);
    //Map<Point2D,Optional<Entity>> board = worldMap.getBoard();
    
    @Test
    public void testCollision() {
        
    }
    
    

}
