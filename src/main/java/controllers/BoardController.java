package controllers;

import models.MOVEMENT;
import models.RandomSpawnStrategy;
import models.SpawnStrategy;
import models.WorldMap;
import view.StubbornView;
import view.javafx.StubbornViewJavaFX;

import java.util.stream.Collectors;

public final class BoardController {
    
    private static final int WIDTH = 51;
    private static final int HEIGHT = 51;
    private static final int ENEMIES = 8;
    private static final int COLLECTABLES = 10;
    
    private final SpawnStrategy spawnStrat;
    private final WorldMap gameWorldMap;
    private final StubbornView worldMapView;
    
    public BoardController() {
        this.spawnStrat  = new RandomSpawnStrategy();
        this.gameWorldMap = new WorldMap(WIDTH, HEIGHT, ENEMIES, COLLECTABLES, spawnStrat);
        this.worldMapView = new StubbornViewJavaFX();
        this.worldMapView.addDirectionalKeyPressHandler(this::onDirectionalKeyPress);
        //così il player può fare solo una partita perchè salvo la worldMap nel controller
        //per fare + partite posso fare un metodo privato che rigenera la mappa
        //MAI METTERE UN HANDLER DENTRO UPDATEWORLDMAP (altrimenti ciclo)
        this.worldMapView.addStartHandler(() -> this.worldMapView.updateWorldMap(this.gameWorldMap)); 
    }
    
    private void onDirectionalKeyPress(MOVEMENT m) {
        this.gameWorldMap.movePlayer(m);
        this.worldMapView.updateWorldMap(this.gameWorldMap);
    }
    
    
}
