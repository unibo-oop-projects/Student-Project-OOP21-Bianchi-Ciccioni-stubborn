package view;

import java.util.List;

import models.Entity;
import models.Pair;
import models.Point2D;

public interface BoardView {
    
    public void initializeView(Point2D playerPos, List<Pair<Point2D,Class<? extends Entity>>> allEntities);
    
    public void updateWorldMap(Point2D playerPos, int numEntitiesRemaining, List<Pair<Point2D,Class<? extends Entity>>> allEntities);
    
    public void gameOver();
    
   
}
