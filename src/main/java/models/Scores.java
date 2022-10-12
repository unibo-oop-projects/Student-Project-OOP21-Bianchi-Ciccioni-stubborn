package models;

import java.util.List;

public interface Scores {
    
    public void setScore(String name, int score);
    
    public List<Pair<String, Integer>> getScore();

}
