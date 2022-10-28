package view;

import java.util.function.Consumer;

import models.MOVEMENT;
import models.WorldMap;

public interface StubbornView {
    
    public void updateWorldMap(WorldMap board);
    
    public void addDirectionalKeyPressHandler(Consumer<MOVEMENT> handler);
    
    //per completismo (probabilmente inutile per questo programma
    public void removeDirectionalKeyPressHandler(Consumer<MOVEMENT> handler);
    
    //questo è per far partire il menù
    public void addStartHandler(Runnable startHandler);
    
}
