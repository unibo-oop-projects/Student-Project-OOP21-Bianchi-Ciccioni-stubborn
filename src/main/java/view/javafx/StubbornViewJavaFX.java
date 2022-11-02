package view.javafx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


import com.sun.prism.paint.Color;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import models.Player;
import models.Point2D;
import models.WorldMap;
import view.BoardView;

public class StubbornViewJavaFX implements BoardView {
    
    private static final int SCREEN_SIZE_MATCH = 10;
    
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
        //also get
        //this.boardStage.setWidth(this.width * 10);
        //this.boardStage.setHeight(this.height * 10);
        //set dimension of scene correctly (there is a constructor for this
        Scene boardScene = this.mainPane.getScene();
        this.boardStage.setScene(boardScene);
        System.out.println(boardScene.getHeight());
        System.out.println(boardScene.getWidth());
        System.out.println(this.boardStage.getHeight());
        System.out.println(this.boardStage.getWidth());
        boardScene.getRoot().requestFocus();
        BackgroundFill bf = new BackgroundFill(Paint.valueOf("#000000"),
                CornerRadii.EMPTY , Insets.EMPTY);
        this.mainPane.setBackground(new Background(bf));
    }
    
    @Override
    public void initializeView(Point2D playerPos, List<Pair<Point2D,Class<? extends Entity>>> allEntities) {
        //this.mainPane.getChildren().add(selectedImage);
        this.playerCanvas = new Canvas(this.width, this.height);
        this.playerCanvas.setLayoutX(playerPos.getX() * SCREEN_SIZE_MATCH);
        this.playerCanvas.setLayoutY(playerPos.getY() * SCREEN_SIZE_MATCH);
        //String playerStandard = "./src/main/resources/sprites/playerStandard.png";
        //File spriteStandard = new File("./src/main/resources/sprites/playerStandard.png");
        //InputStream targetStream = new FileInputStream(spriteStandard);
        //Image playerSprite = new Image(playerStandard,this.width,this.height,true, true);
        //Image playerSprite = new Image(targetStream,this.width,this.height,true,true);
        //ImageView selectedImage = new ImageView(playerSprite); 
        GraphicsContext gc = this.playerCanvas.getGraphicsContext2D();
        //gc.drawImage(playerSprite, 0, 0, this.width,this.height);
        gc.setFill(Paint.valueOf("#009630"));
        gc.fillRect(0, 0, this.width, this.height);
        /*
         * PUNTO 3: evitare che il Player esca dalla mappa
         * PUNTO 4: sostituire colore con Sprite
         */
        for(Pair<Point2D,Class<? extends Entity>> i : allEntities) {
            if(i.getY().equals(EnemyImpl.class)) {
                this.enCnavas.put(i.getX(),new Canvas(this.width, this.height));
            }
            else {
                this.collCanvas.put(i.getX(),new Canvas(this.width, this.height));
            }
        }
        
        this.enCnavas.forEach((pos, en) -> {
            en.setLayoutX(pos.getX() * SCREEN_SIZE_MATCH);
            en.setLayoutY(pos.getY() * SCREEN_SIZE_MATCH);
            en.getGraphicsContext2D().setFill(Paint.valueOf("#555555"));
            en.getGraphicsContext2D().fillRect(0, 0, this.width, this.height);
        });
        this.collCanvas.forEach((pos, coll) -> {
            coll.setLayoutX(pos.getX() * SCREEN_SIZE_MATCH);
            coll.setLayoutY(pos.getX() * SCREEN_SIZE_MATCH);
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
    public void updateWorldMap(Point2D playerPos, int numEntitiesRemaining) {
        GraphicsContext gc = this.playerCanvas.getGraphicsContext2D();
        gc.setFill(Paint.valueOf("#009630"));
        gc.clearRect(0, 0, this.width, this.height);
        this.playerCanvas.setLayoutX(playerPos.getX() * SCREEN_SIZE_MATCH);
        this.playerCanvas.setLayoutY(playerPos.getY() * SCREEN_SIZE_MATCH);
        gc.fillRect(0, 0, this.width, this.height);
        System.out.println(this.playerCanvas.getLayoutX());
        System.out.println(this.playerCanvas.getLayoutY());
        if(this.enCnavas.size() + this.collCanvas.size() < numEntitiesRemaining) {
            GraphicsContext gColl = this.collCanvas.get(playerPos).getGraphicsContext2D();
            gColl.clearRect(0, 0, this.width, this.height);
        }
    }

    @Override
    public void addDirectionalKeyPressHandler(Consumer<MOVEMENT> handler) {
        // TODO Auto-generated method stub
        /*
         * else {
            Player player = (Player) this.board.get(this.playerPosition).get();
            player.setHealth(player.getHealth() - 1);
            }
         */
    }

    @Override
    public void takeDamage(Player player) { 
        /*
        String musicFile = "Hit_sound.mp3";
        AudioClip sound = new AudioClip(new File(musicFile).toURI().toString());
        mediaPlayer.play();*/
        player.setHealth(-1);
        System.out.println("Damage Taken");
        System.out.println(player.getHealth());
        if(player.getHealth() <= 0) {
            this.gameOver();
        }
    }

    private void gameOver() {
        Parent root = null;
        try {
            root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Scene scene = new Scene(root, this.boardStage.getHeight(), this.boardStage.getWidth());
        boardStage.setScene(scene);
        boardStage.show();
    }

}
