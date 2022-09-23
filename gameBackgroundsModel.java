import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class gameBackgroundsModel {
 
    private static final Integer STARTTIME = 0;
    private static final Integer STARTLIVES = 3;
    private static final Integer MAXLIVES = 3;
    private static final Integer STARTSCORE = 0;
    private Timeline timeline;
    private Integer timeSeconds = STARTTIME;
    private Label timerLabel = new Label();
    private Integer lifePoints = STARTLIVES;
    private Label lifeLabel = new Label();
    private Integer scorePoints = STARTSCORE;
    private Label scoreLabel = new Label();
 
    public static void main(String[] args) {
        Application.launch(args);
    }
 
    @Override
    public void start(Stage primaryStage) {
 
        primaryStage.setTitle("Game backgrounds");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
 
        timerLabel.setText(timeSeconds.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
            new KeyFrame(Duration.seconds(1),
                new EventHandler() {
                    public void advanceTime(ActionEvent event) {
                        timeSeconds++;
                        timerLabel.setText(timeSeconds.toString());
                    }
                }
            )
        );
        timerLabel.setStyle("-fx-font-size: 4em;");
 
     
        refillLife();
        lifeLabel.setTextFill(Color.RED);
        lifeLabel.setStyle("-fx-font-size: 4em;");
 
        scoreLabel.setText(scorePoints.toString());
        scoreLabel.setStyle("-fx-font-size: 4em;");
 
        HBox hbTopLeft = new HBox(8);
        HBox hbTopCenter = new HBox(8);
        HBox hbBottomLeft = new HBox(8);
        hbTopLeft.setAlignment(Pos.LEFT);
        hbTopLeft.setPrefWidth(scene.getWidth());
        hbTopLeft.getChildren().addAll(timerLabel);
        root.getChildren().add(hbTopLeft);
 
        hbTopCenter.setAlignment(Pos.LEFT);
        hbTopCenter.setPrefWidth(scene.getWidth());
        hbTopCenter.setLayoutX(200);
        hbTopCenter.getChildren().add(lifeLabel);
        root.getChildren().add(hbTopCenter);
 
        hbBottomLeft.setAlignment(Pos.LEFT);
        hbBottomLeft.setPrefWidth(scene.getWidth());
        hbBottomLeft.setLayoutY(980);
        hbBottomLeft.getChildren().add(scoreLabel);
        root.getChildren().add(hbBottomLeft);
 
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
