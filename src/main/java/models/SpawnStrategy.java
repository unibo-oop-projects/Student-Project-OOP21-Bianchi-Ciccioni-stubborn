package models;

import java.util.List;

public interface SpawnStrategy {

    List<Pair<Integer,Integer>> getSpawnPoints(int width, int height,int numPoints);
}
