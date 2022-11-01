package view.javafx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.sun.prism.paint.Color;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import models.EnemyImpl;
import models.Entity;
import models.MOVEMENT;
import models.Pair;
import models.Point2D;
import models.WorldMap;
import view.StubbornView;

public class StubbornViewJavaFX implements StubbornView {
    
    private final int width;
    private final int height;
    
    @FXML
    private Pane mainPane;
    private Stage boardStage;
    private Canvas playerCanvas;
    private Map<Point2D,Canvas> collCanvas = new HashMap<>();
    private Map<Point2D,Canvas> enCnavas = new HashMap<>();

    public StubbornViewJavaFX(final Pane mainPane, final int height, final int width) {
        this.mainPane = mainPane;
        this.width = width;
        this.height = height;
        this.boardStage = (Stage)this.mainPane.getScene().getWindow();
        this.boardStage.setWidth(this.width * 10);
        this.boardStage.setHeight(this.height * 10);
        Scene boardScene = this.mainPane.getScene();
        boardScene.getRoot().requestFocus();
        BackgroundFill bf = new BackgroundFill(Paint.valueOf("#000000"),
                CornerRadii.EMPTY , Insets.EMPTY);
        this.mainPane.setBackground(new Background(bf));
    }
    
    @Override
    public void initializeView(Point2D playerPos, List<Pair<Point2D,Class<? extends Entity>>> allEntities) {
        //this.mainPane.getChildren().add(selectedImage);
        this.playerCanvas = new Canvas(30, 30);
        this.playerCanvas.setLayoutX(playerPos.getX()*9);
        this.playerCanvas.setLayoutY(playerPos.getY()*9);
        //File spriteStandard = new File("src/main/resources/sprites/playerStandard.png");
        //InputStream targetStream = new FileInputStream(spriteStandard);
        //Image playerSprite = new Image(targetStream,WIDTH,HEIGHT,true,true);
        //ImageView selectedImage = new ImageView(playerSprite); 
        GraphicsContext gc = this.playerCanvas.getGraphicsContext2D();
        //gc.drawImage(playerSprite, 0, 0, WIDTH, HEIGHT);
        gc.setFill(Paint.valueOf("#009630"));
        gc.fillRect(0, 0, this.width, this.height);
        /*
         * PUNTO 3: evitare che il Player esca dalla mappa
         * PUNTO 4: sostituire colore con Sprite
         * PUNTO 5: Incapsulare in View
         */
        for(Pair<Point2D,Class<? extends Entity>> i : allEntities) {
            if(i.getY().equals(EnemyImpl.class)) {
                this.enCnavas.put(i.getX(),new Canvas(30,30));
            }
            else {
                this.collCanvas.put(i.getX(),new Canvas(30,30));
            }
        }
        
        this.enCnavas.forEach((pos, en) -> {
            en.setLayoutX(pos.getX()*5);
            en.setLayoutY(pos.getY()*5);
            en.getGraphicsContext2D().setFill(Paint.valueOf("#555555"));
            en.getGraphicsContext2D().fillRect(0, 0, this.width, this.height);
        });
        this.collCanvas.forEach((pos, coll) -> {
            coll.setLayoutX(pos.getX()*5);
            coll.setLayoutY(pos.getY()*5);
            coll.getGraphicsContext2D().setFill(Paint.valueOf("#FFFF00"));
            coll.getGraphicsContext2D().fillRect(0, 0, this.width, this.height);
        });
        System.out.println(this.enCnavas);
        /*
        Canvas enCanvas = this.enCnavas.get(enPos);
        enCanvas.setLayoutX(enPos.getX()*3);
        enCanvas.setLayoutY(enPos.getY()*3);
        GraphicsContext gEnemy = enCanvas.getGraphicsContext2D();
        gEnemy.setFill(Paint.valueOf("#555555"));
        gEnemy.fillRect(0, 0, WIDTH, HEIGHT);*/
        System.out.println(this.collCanvas);
        //this.mainPane.getChildren().add(selectedImage);
        this.mainPane.getChildren().add(this.playerCanvas);
        this.mainPane.getChildren().addAll(this.enCnavas.values());
        this.mainPane.getChildren().addAll(this.collCanvas.values());
    }
    
    @Override
    public void updateWorldMap(Point2D playerPos) {
        GraphicsContext gc = this.playerCanvas.getGraphicsContext2D();
        gc.setFill(Paint.valueOf("#009630"));
        gc.clearRect(0, 0, this.width, this.height);
        this.playerCanvas.setLayoutX(playerPos.getX()* 9);
        this.playerCanvas.setLayoutY(playerPos.getY()* 9);
        gc.fillRect(0, 0, this.width, this.height);
    }

    @Override
    public void addDirectionalKeyPressHandler(Consumer<MOVEMENT> handler) {
        // TODO Auto-generated method stub
        
    }

}
