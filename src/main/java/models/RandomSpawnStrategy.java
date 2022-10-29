package models;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomSpawnStrategy implements SpawnStrategy{
    

    @Override
    public Set<Point2D> getSpawnPoints(int width, int height, int numPoints) {
        Random r = new Random();
        Set<Point2D> spawnPoints = new HashSet<>();
        while(spawnPoints.size() < numPoints) {
            Point2D newPos = new Point2D(r.nextInt(width), r.nextInt(height));
            if(!newPos.equals(new Point2D(width/2, height/2))) {
                spawnPoints.add(newPos);
            }
        }
        return spawnPoints;
    }
    
    @Override
    public Set<Point2D> getDoubleSpawnPoints(int width, int height, Set<Point2D> points1, Set<Point2D> points2){
        Set<Point2D> allPoints = new HashSet<>();
        allPoints.addAll(points1);
        allPoints.addAll(points2);
        while(allPoints.size() - (points1.size() + points2.size()) != 0) {
            allPoints.addAll(getSpawnPoints(width, height, (points1.size() + points2.size()) - allPoints.size()));
        }
        //points1.addAll(getSpawnPoints(BOARD_WIDTH, BOARD_HEIGHT, (points1Size+points2.size()) - points1.size()));
        return allPoints;
    }

    @Override
    public boolean checkNumPoints(int boardDimension, int numPoints) {
        return boardDimension > numPoints;
    }

}
