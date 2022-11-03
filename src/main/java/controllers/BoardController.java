package controllers;

import models.Collectable;
import models.CollectableImpl;
import models.Enemy;
import models.EnemyImpl;
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
import view.javafx.BoardViewJavaFX;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

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
        //this.worldMapView = new StubbornViewJavaFX(this.mainPane, HEIGHT, WIDTH);
        //this.worldMapView.addDirectionalKeyPressHandler(this::onDirectionalKeyPress);
        //così il player può fare solo una partita perchè salvo la worldMap nel controller
        //per fare + partite posso fare un metodo privato che rigenera la mappa
        //MAI METTERE UN HANDLER DENTRO UPDATEWORLDMAP (altrimenti ciclo)
        //this.worldMapView.addStartHandler(() -> this.worldMapView.updateWorldMap(this.gameWorldMap));
        /*
         * javaFX offre metodo chiamato dopo costruttore (quanto è caricato main thread di JavaFX).
         * 
         */
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
        /*
         * cercare un modo per fare sì che il canvas si sposti di uno scacco (di una dimensione
         * specifica). Mappare spostamento logico in uno grafico.
         * Passo a controller il point2D del player che poi sarà mappato in spostamento individuale
         * a livello grafico (decidi te il livello grafico)
         * questi valori qui sotto servono per cambiarlo e spostarlo di un'altra posizione
         * (in caso mettere degli sprite specifici)
         * l'obiettivo è fare sì che quando chiamo il movement, allora si sposta anche il player
         * (devo recuperare la posizione del player e spostarla a livello grafico)
         */
    }
    
    private void updateMap(MOVEMENT movement) {
        this.gameWorldMap.movePlayer(movement);
        Point2D playerPos = this.gameWorldMap.getPlayerPos();
        System.out.println(this.gameWorldMap.getBoard().get(playerPos));
        if(playerPos.equals(this.previousPlayerPos)) {
            Player player = (Player)this.gameWorldMap.getBoard().get(playerPos).get();
            player.setHealth(-1);
            System.out.println("Damage Taken");
            System.out.println(player.getHealth());
            if(player.getHealth() <= 0) {
                this.worldMapView.gameOver();
            }
        } else {
            //System.out.println(this.gameWorldMap.getEntitiesPos().size());
            this.worldMapView.updateWorldMap(playerPos, this.gameWorldMap.getEntitiesPos().size(),this.gameWorldMap.getEntitiesPos());
        }
        this.previousPlayerPos = playerPos;
    }

    
}
