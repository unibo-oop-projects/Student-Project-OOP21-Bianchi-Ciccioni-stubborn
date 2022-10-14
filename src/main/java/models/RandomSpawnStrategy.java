package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomSpawnStrategy implements SpawnStrategy{
    

    @Override
    public Set<Point2D> getSpawnPoints(int width, int height, int numPoints) {
        Random r = new Random();
        Set<Point2D> spawnPoints = new HashSet<>();
        while(spawnPoints.size() < numPoints) {
            spawnPoints.add(new Point2D(r.nextInt(width), r.nextInt(height)));
        }
        return spawnPoints;
    }
    
    @Override
    public Set<Point2D> getDoubleSpawnPoints(int width, int height, Set<Point2D> points1, Set<Point2D> points2){
        Set<Point2D> allPoints = new HashSet<>();
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
