package view;

import java.util.List;
import java.util.function.Consumer;

import models.Entity;
import models.MOVEMENT;
import models.Pair;
import models.Point2D;
import models.WorldMap;

public interface StubbornView {
    
    public void initializeView(Point2D playerPos, List<Pair<Point2D,Class<? extends Entity>>> allEntities);
    
    public void updateWorldMap(Point2D playerPos);
    
    public void addDirectionalKeyPressHandler(Consumer<MOVEMENT> handler);
    
}
