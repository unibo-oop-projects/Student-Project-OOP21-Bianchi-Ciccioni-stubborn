package scoresTest;

import models.Player;
import models.PlayerImpl;
import models.Point2D;
import models.Scores;
import models.ScoresImpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoresTest {
    
    private Scores s = new ScoresImpl();

    private Point2D pPlayer = new Point2D(1,1);
    private Player p = new PlayerImpl(pPlayer, 3);
    
    @Test
    public void writeScoreTest() {
        List<String> list = new ArrayList<>();
        
        s.setScore("Marco:130");
        s.setScore("Matteo:80");
        
    }
    
    @Test
    public void readScoreTest() {
        
    }
    

}
