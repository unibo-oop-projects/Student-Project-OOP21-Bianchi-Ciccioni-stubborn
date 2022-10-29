package controllers;

import models.Collectable;
import models.CollectableImpl;
import models.Enemy;
import models.Entity;
import models.MOVEMENT;
import models.Pair;
import models.Player;
import models.Point2D;
import models.RandomSpawnStrategy;
import models.SpawnStrategy;
import models.WorldMap;
import models.WorldMapImpl;
import view.StubbornView;
import view.javafx.StubbornViewJavaFX;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
    private Map<Point2D,Canvas> collCanvas = new HashMap<>();
    private Map<Point2D,Canvas> enCnavas = new HashMap<>();
    
    public BoardController() {
        this.spawnStrat  = new RandomSpawnStrategy();
        this.gameWorldMap = new WorldMapImpl(WIDTH, HEIGHT, ENEMIES, COLLECTABLES, spawnStrat);
        this.worldMapView = new StubbornViewJavaFX();
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
        Stage boardStage = (Stage)this.mainPane.getScene().getWindow();
        boardStage.setWidth(WIDTH * 10);
        boardStage.setHeight(HEIGHT * 10);
        Scene boardScene = this.mainPane.getScene();
        boardScene.getRoot().requestFocus();
        BackgroundFill bf = new BackgroundFill(Paint.valueOf("#000000"),
                CornerRadii.EMPTY , Insets.EMPTY);
        this.mainPane.setBackground(new Background(bf));
        //Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Point2D playerPos = this.gameWorldMap.getPlayerPos();
        System.out.println(playerPos); 
        //this.mainPane.getChildren().add(selectedImage);
        this.playerCanvas = new Canvas(30, 30);
        this.playerCanvas.setLayoutX(playerPos.getX()*9);
        this.playerCanvas.setLayoutY(playerPos.getY()*9);
        System.out.println(this.playerCanvas.getLayoutBounds());
        //File spriteStandard = new File("src/main/resources/sprites/playerStandard.png");
        //InputStream targetStream = new FileInputStream(spriteStandard);
        //Image playerSprite = new Image(targetStream,WIDTH,HEIGHT,true,true);
        //ImageView selectedImage = new ImageView(playerSprite); 
        GraphicsContext gc = this.playerCanvas.getGraphicsContext2D();
        //gc.drawImage(playerSprite, 0, 0, WIDTH, HEIGHT);
        gc.setFill(Paint.valueOf("#009630"));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        /*
         * PUNTO 1: distinguere Collectable da Enemy
         * PUNTO 2: farli apparire sulla mappa
         * PUNTO 3: evitare che il Player esca dalla mappa
         * PUNTo 4: sostituire colore con Sprite
         */
        List<Pair<Point2D,Class<? extends Entity>>> allEntities = this.gameWorldMap.getEntitiesPos();
        System.out.println(allEntities);
        for(Pair<Point2D,Class<? extends Entity>> i : allEntities) {
            if(i.getY().equals(Enemy.class)) {
                this.enCnavas.put(i.getX(),new Canvas(30,30));
            }
            else {
                this.collCanvas.put(i.getX(),new Canvas(30,30));
            }
        }
        /*
        for(Entry<Point2D, Canvas> i : this.enCnavas.entrySet()) {
            i.getValue().setLayoutX(i.getKey().getX()*5);
            i.getValue().setLayoutY(i.getKey().getY()*5);
            GraphicsContext gEnemy = i.getValue().getGraphicsContext2D();
            gEnemy.setFill(Paint.valueOf("#555555"));
            gEnemy.fillRect(0, 0, WIDTH, HEIGHT);
        }*/
        this.enCnavas.forEach((pos, en) -> {
            en.setLayoutX(pos.getX()*5);
            en.setLayoutY(pos.getY()*5);
            en.getGraphicsContext2D().setFill(Paint.valueOf("#555555"));
            en.getGraphicsContext2D().fillRect(0, 0, WIDTH, HEIGHT);
        });
        this.enCnavas.put(allEntities.get(0).getX(),new Canvas(30,30));
        GraphicsContext gEnemy = this.enCnavas.get(allEntities.get(0).getX()).getGraphicsContext2D();
        gEnemy.setFill(Paint.valueOf("#555555"));
        gEnemy.fillRect(0, 0, WIDTH, HEIGHT);
        System.out.println(this.collCanvas);
        //this.mainPane.getChildren().add(selectedImage);
        this.mainPane.getChildren().add(this.playerCanvas);
        this.mainPane.getChildren().addAll(this.enCnavas.values());
        
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
        /*
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        this.playerCanvas.setLayoutX(playerPos.getX()*5);
        this.playerCanvas.setLayoutY(playerPos.getY()*5);
        gc.fillRect(0, 0, WIDTH, HEIGHT);*/
    }
    
    private void updateMap(MOVEMENT movement) {
        GraphicsContext gc = this.playerCanvas.getGraphicsContext2D();
        gc.setFill(Paint.valueOf("#009630"));
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        this.gameWorldMap.movePlayer(movement);
        Point2D playerPos = this.gameWorldMap.getPlayerPos();
        this.playerCanvas.setLayoutX(playerPos.getX()* 9);
        this.playerCanvas.setLayoutY(playerPos.getY()* 9);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        //this.mainPane.getChildren().add(selectedImage);
    }
    
    
    
    
}
