package controllers;

import models.Enemy;
import models.Entity;
import models.MOVEMENT;
import models.Player;
import models.Point2D;
import models.RandomSpawnStrategy;
import models.SpawnStrategy;
import models.WorldMap;
import view.StubbornView;
import view.javafx.StubbornViewJavaFX;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sun.prism.paint.Color;

public final class BoardController {
    
    private static final int WIDTH = 51;
    private static final int HEIGHT = 51;
    private static final int ENEMIES = 8;
    private static final int COLLECTABLES = 10;
    
    private final SpawnStrategy spawnStrat;
    private final WorldMap gameWorldMap;
    private StubbornView worldMapView;
    @FXML
    private Pane mainPane;
    private Canvas playerCanvas;
    
    public BoardController() {
        this.spawnStrat  = new RandomSpawnStrategy();
        this.gameWorldMap = new WorldMap(WIDTH, HEIGHT, ENEMIES, COLLECTABLES, spawnStrat);
        this.worldMapView = new StubbornViewJavaFX();
        //this.worldMapView.addDirectionalKeyPressHandler(this::onDirectionalKeyPress);
        //così il player può fare solo una partita perchè salvo la worldMap nel controller
        //per fare + partite posso fare un metodo privato che rigenera la mappa
        //MAI METTERE UN HANDLER DENTRO UPDATEWORLDMAP (altrimenti ciclo)
        this.worldMapView.addStartHandler(() -> this.worldMapView.updateWorldMap(this.gameWorldMap));
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
            case UP: this.updateMap(MOVEMENT.UP);
                break;
            case S:
            case DOWN: this.updateMap(MOVEMENT.DOWN);
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
        //the real code were the friends we made along the way
        System.out.println("the real code were the friends we made along the way");
        Stage boardStage = (Stage)this.mainPane.getScene().getWindow();
        boardStage.setWidth(200);
        boardStage.setHeight(200);
        BackgroundFill bf = new BackgroundFill(Paint.valueOf("#000000"),
                CornerRadii.EMPTY , Insets.EMPTY);
        this.mainPane.setBackground(new Background(bf));
        //Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Point2D playerPos = this.getPlayerPos(); 
        //Image playerSprite = new Image("src/main/resources/sprites/playerStandard.png");
        //ImageView selectedImage = new ImageView(playerSprite);  
        //this.mainPane.getChildren().add(selectedImage);
        this.playerCanvas = new Canvas(30, 25);
        this.playerCanvas.setLayoutX(playerPos.getX()*3);
        this.playerCanvas.setLayoutY(playerPos.getY()*3);
        GraphicsContext gc = this.playerCanvas.getGraphicsContext2D();
        gc.setFill(Paint.valueOf("#009630"));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        //this.mainPane.getChildren().add(selectedImage);
        this.mainPane.getChildren().add(this.playerCanvas);
        
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
        //gc.clearRect(0, 0, WIDTH, HEIGHT);
        //gc.fillRect(WIDTH, HEIGHT, WIDTH, HEIGHT);
    }
    
    private void updateMap(MOVEMENT movement) {
        GraphicsContext gc = this.playerCanvas.getGraphicsContext2D();
        gc.setFill(Paint.valueOf("#000000"));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        this.gameWorldMap.movePlayer(movement);
        Point2D playerPos = this.getPlayerPos(); 
        this.playerCanvas.setLayoutX(playerPos.getX()*3);
        this.playerCanvas.setLayoutY(playerPos.getY()*3);
        gc.setFill(Paint.valueOf("#009630"));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        //this.mainPane.getChildren().add(selectedImage);
    }
    
    private Point2D getPlayerPos() {
        Point2D playerPos = new Point2D(0,0);
        for(Entry<Point2D, Optional<Entity>> i : this.gameWorldMap.getBoard().entrySet()) {
            if( i.getValue().isPresent() && i.getValue().get() instanceof Player) {
                playerPos = i.getKey();
            }
        }
        return playerPos;
    }
    
    private List<Point2D> getEnemiesPos() {
        List<Point2D> enemiesPos = new ArrayList<>();
        for(Entry<Point2D, Optional<Entity>> i : this.gameWorldMap.getBoard().entrySet()) {
            if( i.getValue().isPresent() && i.getValue().get() instanceof Enemy) {
                enemiesPos.add(i.getKey());
            }
        }
        return enemiesPos;
    }
    
    private List<Point2D> getEntitiesPos(Entity entity) {
        List<Point2D> enemiesPos = new ArrayList<>();
        for(Entry<Point2D, Optional<Entity>> i : this.gameWorldMap.getBoard().entrySet()) {
            if( i.getValue().isPresent() && i.getValue().get().getClass().equals(entity.getClass())) {
                enemiesPos.add(i.getKey());
            }
        }
        return enemiesPos;
    }
    
    
}
