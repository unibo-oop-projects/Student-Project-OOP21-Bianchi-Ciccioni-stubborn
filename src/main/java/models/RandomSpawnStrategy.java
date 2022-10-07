package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSpawnStrategy implements SpawnStrategy{

    @Override
    public List<Pair<Integer, Integer>> getSpawnPoints(int width, int height, int numPoints) {
        Random r = new Random();
        List<Pair<Integer,Integer>> spawnPoints = new ArrayList<>();
        for (int i = 0; i < numPoints; i++) {
            spawnPoints.add(new Pair<>(r.nextInt(width), r.nextInt(height)));
        }
        return spawnPoints;
    }

}
