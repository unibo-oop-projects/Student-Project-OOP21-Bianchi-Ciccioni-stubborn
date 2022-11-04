package view;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import view.BoardView;

public class BoardViewJavaFX implements BoardView {
    
    private static final int SCREEN_SIZE_MATCH = 10;
    
    private final int width;
    private final int height;
    
    @FXML
    private Pane mainPane;
    private Stage boardStage;
    private Canvas playerCanvas;
    private Map<Point2D,Canvas> collCanvas = new HashMap<>();
    private Map<Point2D,Canvas> enCanvas = new HashMap<>();

    public BoardViewJavaFX(final Pane mainPane, final int height, final int width) {
        this.mainPane = mainPane;
        this.width = width;
        this.height = height;
        this.boardStage = (Stage)this.mainPane.getScene().getWindow();
        Scene boardScene = this.mainPane.getScene();
        this.boardStage.setScene(boardScene);
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
        for(Pair<Point2D,Class<? extends Entity>> i : allEntities) {
            if(i.getY().equals(EnemyImpl.class)) {
                this.enCanvas.put(i.getX(),new Canvas(this.width, this.height));
            }
            else {
                this.collCanvas.put(i.getX(),new Canvas(this.width, this.height));
            }
        }
        
        this.enCanvas.forEach((pos, en) -> {
            en.setLayoutX(pos.getX() * SCREEN_SIZE_MATCH);
            en.setLayoutY(pos.getY() * SCREEN_SIZE_MATCH);
            en.getGraphicsContext2D().setFill(Paint.valueOf("#555555"));
            en.getGraphicsContext2D().fillRect(0, 0, this.width, this.height);
        });
        this.collCanvas.forEach((pos, coll) -> {
            coll.setLayoutX(pos.getX() * SCREEN_SIZE_MATCH);
            coll.setLayoutY(pos.getY() * SCREEN_SIZE_MATCH);
            coll.getGraphicsContext2D().setFill(Paint.valueOf("#FFFF00"));
            coll.getGraphicsContext2D().fillRect(0, 0, this.width, this.height);
        });
        this.mainPane.getChildren().add(this.playerCanvas);
        this.mainPane.getChildren().addAll(this.enCanvas.values());
        this.mainPane.getChildren().addAll(this.collCanvas.values());
    }
    
    @Override
    public void updateWorldMap(Point2D playerPos, int numEntitiesRemaining, List<Pair<Point2D,Class<? extends Entity>>> allEntities) {
        GraphicsContext gc = this.playerCanvas.getGraphicsContext2D();
        gc.setFill(Paint.valueOf("#009630"));
        gc.clearRect(0, 0, this.width, this.height);
        this.playerCanvas.setLayoutX(playerPos.getX() * SCREEN_SIZE_MATCH);
        this.playerCanvas.setLayoutY(playerPos.getY() * SCREEN_SIZE_MATCH);
        gc.fillRect(0, 0, this.width, this.height);
        this.enCanvas.forEach((pos, en) -> {
            en.getGraphicsContext2D().clearRect(0, 0, this.width, this.height);
            this.mainPane.getChildren().remove(en);
        });
        this.enCanvas.clear();
        allEntities.removeIf(el -> !el.getY().equals(EnemyImpl.class));
        allEntities.forEach(p -> this.enCanvas.put(p.getX(), new Canvas(this.width, this.height)));
        this.enCanvas.forEach((pos, en) -> {
            en.setLayoutX(pos.getX() * SCREEN_SIZE_MATCH);
            en.setLayoutY(pos.getY() * SCREEN_SIZE_MATCH);
            en.getGraphicsContext2D().setFill(Paint.valueOf("#555555"));
            en.getGraphicsContext2D().fillRect(0, 0, this.width, this.height);
        });
        this.mainPane.getChildren().addAll(this.enCanvas.values());
        if(this.enCanvas.size() + this.collCanvas.size() > numEntitiesRemaining) {
            GraphicsContext gColl = this.collCanvas.get(playerPos).getGraphicsContext2D();
            gColl.clearRect(0, 0, this.width, this.height);
            Canvas el = this.collCanvas.get(playerPos);
            this.collCanvas.remove(playerPos);
            this.mainPane.getChildren().remove(el);
        }
    }

    @Override
    public void gameOver() {
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
