package entitiesTest;

import org.junit.jupiter.api.Test;

import models.Collectable;
import models.CollectableImpl;
import models.Player;
import models.PlayerImpl;
import models.Point2D;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    
    private Point2D startPosition = new Point2D(5,5);
    private int health = 3;
    private Player p = new PlayerImpl(startPosition, health);
    
    @Test
    public void testMovePlayer() {
        // TODO test for check if player can move inside the map
    }

}
