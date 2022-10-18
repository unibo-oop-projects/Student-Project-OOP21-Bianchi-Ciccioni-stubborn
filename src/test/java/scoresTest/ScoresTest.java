package scoresTest;

import models.Player;
import models.PlayerImpl;
import models.Point2D;
import models.Scores;
import models.ScoresImpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


public class ScoresTest {
    
    private Scores s = new ScoresImpl();
    
    @Test
    public void writeScoreTest() {
        s.setScore("Marco:130");
    }
    
    @Test
    public void readScoreTest() {
        String expected = "Marco:130";
        String result = s.getScore();
        System.out.println(result);
        assertEquals(expected, result);
    }
    

}
