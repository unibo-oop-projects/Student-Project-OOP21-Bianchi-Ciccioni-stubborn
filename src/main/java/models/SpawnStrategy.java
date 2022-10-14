package models;

import java.util.Set;

public interface SpawnStrategy {

    Set<Point2D> getSpawnPoints(int width, int height,int numPoints);
    
    boolean checkNumPoints(int boardDimension, int numPoints);
    
    Set<Point2D> getDoubleSpawnPoints(int width, int height, Set<Point2D> points1, Set<Point2D> points2);
}
