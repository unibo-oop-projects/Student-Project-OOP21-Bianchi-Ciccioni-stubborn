package models;

import java.util.List;

public interface Scores {

    void setScore(String name, Integer score);

    List<Pair<String, Integer>> getAllScores();

    String getScore();

}
