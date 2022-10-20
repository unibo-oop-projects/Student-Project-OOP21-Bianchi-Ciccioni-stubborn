package scoresTest;

import models.Scores;
import models.ScoresImpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    
    @Test
    public void checkListScore() {
        s.setScore("Guest:200");
        s.setScore("Guest1:10");
        s.setScore("Guest2:120");
        assertEquals(s.getAllScores().size(), 3);
        assertTrue(s.getAllScores().get(0).equals("Guest:200"));
        assertTrue(s.getAllScores().get(2).equals("Guest2:120"));
    }
}
