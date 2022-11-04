package controllers;


import models.Entity;
import models.MOVEMENT;
import models.Pair;
import models.Player;
import models.Point2D;
import models.RandomSpawnStrategy;
import models.SpawnStrategy;
import models.WorldMap;
import models.WorldMapImpl;
import view.BoardView;
import view.BoardViewJavaFX;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.Pane;

import java.util.List;


public final class BoardController {
    
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int ENEMIES = 8;
    private static final int COLLECTABLES = 10;
    
    private final SpawnStrategy spawnStrat;
    private final WorldMap gameWorldMap;
    private BoardView worldMapView;
    private Point2D previousPlayerPos;
    @FXML
    private Pane mainPane;
    
    public BoardController() {
        this.spawnStrat  = new RandomSpawnStrategy();
        this.gameWorldMap = new WorldMapImpl(WIDTH, HEIGHT, ENEMIES, COLLECTABLES, spawnStrat);
    }
    
    @FXML
    private void onDirectionalKeyPress(final KeyEvent keyEvent) {
        KeyCode key = keyEvent.getCode();
        switch(key) {
            case W: 
            case UP: this.updateMap(MOVEMENT.DOWN);
                break;
            case S:
            case DOWN: this.updateMap(MOVEMENT.UP);
                break;
            case A:
            case LEFT: this.updateMap(MOVEMENT.LEFT);
                break;
            case D:
            case RIGHT: this.updateMap(MOVEMENT.RIGHT);
                break;
        default:
            break;
        }
    }
    
    @FXML
    private void initialize() {
        Platform.runLater(() -> this.initalizeView());
    }

    
    private void initalizeView() {
        this.worldMapView = new BoardViewJavaFX(this.mainPane, HEIGHT, WIDTH);
        this.previousPlayerPos = this.gameWorldMap.getPlayerPos();
        List<Pair<Point2D,Class<? extends Entity>>> allEntities = this.gameWorldMap.getEntitiesPos();
        this.worldMapView.initializeView(this.previousPlayerPos, allEntities);
    }
    
    private void updateMap(MOVEMENT movement) {
        this.gameWorldMap.movePlayer(movement);
        Point2D playerPos = this.gameWorldMap.getPlayerPos();
        if(playerPos.equals(this.previousPlayerPos)) {
            Player player = (Player)this.gameWorldMap.getBoard().get(playerPos).get();
            player.setHealth(-1);
            System.out.println("Damage Taken");
            System.out.println(player.getHealth());
            if(player.getHealth() <= 0) {
                this.worldMapView.gameOver();
            }
        } else {
            this.worldMapView.updateWorldMap(playerPos, this.gameWorldMap.getEntitiesPos().size(),this.gameWorldMap.getEntitiesPos());
        }
        this.previousPlayerPos = playerPos;
    }

    
}
