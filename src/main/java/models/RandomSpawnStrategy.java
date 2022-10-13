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
    
    @Override
    public Set<Pair<Integer,Integer>> getDoubleSpawnPoints(int width, int height, Set<Pair<Integer,Integer>> points1, Set<Pair<Integer,Integer>> points2){
        Set<Pair<Integer,Integer>> allPoints = new HashSet<>();
        allPoints.addAll(points1);
        allPoints.addAll(points2);
        while(allPoints.size() - (points1.size() + points2.size()) != 0) {
            //points1.addAll(getSpawnPoints(BOARD_WIDTH, BOARD_HEIGHT, (points1Size+points2.size()) - points1.size()));
            allPoints.addAll(getSpawnPoints(width, height, (points1.size() + points2.size()) - allPoints.size()));
        }
        return allPoints;
    }

    @Override
    public boolean checkNumPoints(int boardDimension, int numPoints) {
        return boardDimension > numPoints;
    }

}
