package controllers;

import com.google.common.collect.ImmutableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import models.RandomSpawnStrategy;
import models.SpawnStrategy;
import models.WorldMap;
import javafx.scene.input.InputEvent;

import java.util.stream.Collectors;

public final class BoardController {
    
    static private final int WIDTH = 51;
    static private final int HEIGHT = 51;
    static private final int ENEMIES = 8;
    static private final int COLLECTABLES = 10;
    
    private SpawnStrategy spawnStrat = new RandomSpawnStrategy();
    private WorldMap gameWorldMap = new WorldMap(WIDTH, HEIGHT, ENEMIES, COLLECTABLES, spawnStrat);
    
    
    
    @FXML
    public void initialize() {
         
    }
    

}
