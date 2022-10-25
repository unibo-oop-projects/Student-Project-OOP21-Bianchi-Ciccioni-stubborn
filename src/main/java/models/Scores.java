package models;

import java.util.List;

public interface Scores {
    
    public void setScore(String name, Integer score);

    public List<Pair<String, Integer>> getAllScores();
    
    public String getScore();

}
