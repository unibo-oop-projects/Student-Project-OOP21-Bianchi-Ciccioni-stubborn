package models;

import java.util.Set;

public interface SpawnStrategy {

    Set<Pair<Integer,Integer>> getSpawnPoints(int width, int height,int numPoints);
    
    boolean checkNumPoints(int boardDimension, int numPoints);
    
    Set<Pair<Integer,Integer>> getDoubleSpawnPoints(Set<Pair<Integer,Integer>> points1, Set<Pair<Integer,Integer>> points2);
}
