package controllers;

/*
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Label;
import javafx.scene.Image;
import javafx.scene.ImageView;

public class backgroundsUIController {
    private static final Integer STARTTIME = 0;
    private static final Integer STARTLIVES = 3;
    private static final Integer MAXLIVES = 3;
    private static final Integer STARTSCORE = 0;
    private static final Image heartSprite = new Image("sprites/heart.png");
    private static final ImageView heartSpriteView = new ImageView(heartSprite);
    private static Timeline timeline;
    private static Integer timeSeconds = STARTTIME;
    private static Integer lifePoints = STARTLIVES;
    private static Integer scorePoints = STARTSCORE;
    private static Label lblTimer;
    private static Label lblLife;
    private static Label lblScore;

    public static void startTimer() {
    	lblTimer.setText(timeSeconds.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
            new KeyFrame(Duration.seconds(1),
                new EventHandler() {
                    public void advanceTime(ActionEvent event) {
                        timeSeconds++;
                        lblTimer.setText(timeSeconds.toString());
                    }
                }
            )
         );
    };
		
    public static void gameOver() {
		Integer achievedScore;
		achievedScore = scorePoints;
		application.Backgrounds.endGame();
		application.Menu.openMenu();
		/* scoreBoard.add(finalScore);	sostituire a
		 * scoreBoard il nome dato al set dei punteggi */ /*
	};

    public static void refillLife() {
    	for (Integer i = 0; i < lifePoints; i++) {
    		lblLife.addGraphic(heartSpriteView);
    	};
    };
    
    public static void hurtLife() {
    	lifePoints--;
    	if (lifePoints == 0) {
    		gameOver();
    	}
    	lblLife.setText("!!GOT HIT!!");
    	refillLife();
    };
    
    public static void healLife() {
    	if (lifePoints < MAXLIVES) {
       		lifePoints++;
       		lblLife.setText("!!LIFE GAINED!!");
       		refillLife();
    	}
    }
    public static void updateScore(Integer gain) {
    	scorePoints += gain;
    	lblScore.setText(scorePoints.toString());
    }
}*/
