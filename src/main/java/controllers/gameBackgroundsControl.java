package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.*;

public class gameBackgroundsControl {
    private static final Integer STARTTIME = 0;
    private static final Integer STARTLIVES = 3;
    private static final Integer MAXLIVES = 3;
    private static final Integer STARTSCORE = 0;
    private Timeline timeline;
    private Integer timeSeconds = STARTTIME;
    private Image heartSprite = new Image("sprites/heart.png");
    private Image heartSpriteView = new ImageView(heartSprite);
    private Integer lifePoints = STARTLIVES;
    private Integer scorePoints = STARTSCORE;

    public void startTimer() {
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
         );
    };
    
	public void gameOver() {
		Integer finalScore;
		finalScore = scorePoints;
		primaryStage.close();
		startMenu();
		/* scoreBoard.add(finalScore);	sostituire a
		 * scoreBoard il nome dato al set dei punteggi */ 
	};

    public void refillLife() {
    	for (Integer i = 0; i < lifePoints; i++) {
    		lifeLabel.addGraphic(heartSpriteView);
    	};
    };
    
    public void hurtLife(ActionEvent event) {
    	lifePoints--;
    	if (lifePoints == 0) {
    		gameOver();
    	}
    	lblLife.setText("!!GOT HIT!!");
    	refillLife();
    }
    public void healLife(ActionEvent event) {
    	if (lifePoints < MAXLIVES) {
       		lifePoints++;
       		lblLife.setText("!!LIFE GAINED!!");
		refillLife();
    	}
    }
    public void updateScore(ActionEvent event, Integer gain) {
    	scorePoints += gain;
    	lblScore.setText(scorePoints.toString());
    }
}
