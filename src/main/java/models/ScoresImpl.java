package models;

import java.util.ArrayList;
import java.util.List;

public class ScoresImpl implements Scores {

    private List<Pair<String, Integer>> scores = new ArrayList<>();

    @Override
    public void setScore(String name, int score) {
        this.scores.add(new Pair<>(name, score));
    }

    @Override
    public List<Pair<String, Integer>> getScore() {
        return this.scores;
    }

}
