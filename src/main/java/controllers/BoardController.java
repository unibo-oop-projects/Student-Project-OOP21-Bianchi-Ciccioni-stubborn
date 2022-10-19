package controllers;

import models.MOVEMENT;
import models.RandomSpawnStrategy;
import models.SpawnStrategy;
import models.WorldMap;
import view.StubbornView;
import view.javafx.StubbornViewJavaFX;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.stream.Collectors;

import com.sun.prism.paint.Color;

public final class BoardController {
    
    private static final int WIDTH = 51;
    private static final int HEIGHT = 51;
    private static final int ENEMIES = 8;
    private static final int COLLECTABLES = 10;
    
    private final SpawnStrategy spawnStrat;
    private final WorldMap gameWorldMap;
    private final StubbornView worldMapView;
    @FXML
    private Pane mainPane;
    
    public BoardController() {
        this.spawnStrat  = new RandomSpawnStrategy();
        this.gameWorldMap = new WorldMap(WIDTH, HEIGHT, ENEMIES, COLLECTABLES, spawnStrat);
        this.worldMapView = new StubbornViewJavaFX();
        this.worldMapView.addDirectionalKeyPressHandler(this::onDirectionalKeyPress);
        //così il player può fare solo una partita perchè salvo la worldMap nel controller
        //per fare + partite posso fare un metodo privato che rigenera la mappa
        //MAI METTERE UN HANDLER DENTRO UPDATEWORLDMAP (altrimenti ciclo)
        this.worldMapView.addStartHandler(() -> this.worldMapView.updateWorldMap(this.gameWorldMap));
        /*
         * javaFX offre metodo chiamato dopo costruttore (quanto è caricato main thread di JavaFX).
         * 
         */
    }
    
    private void onDirectionalKeyPress(MOVEMENT m) {
        this.gameWorldMap.movePlayer(m);
        this.worldMapView.updateWorldMap(this.gameWorldMap);
    }
    
    @FXML
    private void initialize() {
        Platform.runLater(() -> this.initalizeView());
    }

    
    private void initalizeView() {
        //the real code were the friends we made along the way
        System.out.println("the real code were the friends we made along the way");
        Stage boardStage = (Stage)this.mainPane.getScene().getWindow();
        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Paint.valueOf("#009688"));
        this.mainPane.getChildren().add(canvas);
    }
    
    
}
