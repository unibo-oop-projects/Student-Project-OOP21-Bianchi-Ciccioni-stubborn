package models;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WorldMap {
    public void movePlayer(MOVEMENT movement);
    
    public Map<Point2D,Optional<Entity>> getBoard();
    
    public Point2D getPlayerPos();
    
    public List<Pair<Point2D,Class<? extends Entity>>> getEntitiesPos();
}
