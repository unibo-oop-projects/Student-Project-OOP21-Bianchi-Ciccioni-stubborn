package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomSpawnStrategy implements SpawnStrategy{

    @Override
    public Set<Pair<Integer, Integer>> getSpawnPoints(int width, int height, int numPoints) {
        Random r = new Random();
        Set<Pair<Integer,Integer>> spawnPoints = new HashSet<>();
        while(spawnPoints.size() < numPoints) {
            spawnPoints.add(new Pair<>(r.nextInt(width), r.nextInt(height)));
        }
        return spawnPoints;
    }
    
    private Set<Pair<Integer,Integer>> getDoubleSpawnPoints(Set<Pair<Integer,Integer>> points1, Set<Pair<Integer,Integer>> points2){
        int points1Size = points1.size();
        points1.addAll(points2);
        if((points1Size+points2.size()) - points1.size() != 0) {
           points1.addAll(getSpawnPoints(BOARD_WIDTH, BOARD_HEIGHT, (points1Size+points2.size()) - points1.size()));
        }
        return points1;
    }

    @Override
    public boolean checkNumPoints(int boardDimension, int numPoints) {
        return boardDimension > numPoints;
    }

}
