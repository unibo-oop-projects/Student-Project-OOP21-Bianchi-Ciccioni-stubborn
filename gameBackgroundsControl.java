
public class gameBackgroundsControl {
    public void gameOver() {
	Integer finalScore;
	finalScore = scorePoints;
	primaryStage.close();
	startMenu();
	scoreBoard.add(finalScore); /* sostituire a
	scoreBoard il nome dato al set dei punteggi */ 
    }
    
    private void refillLife() {
	lifeLabel.setText("");
	for (Integer i = 0; i < lifePoints; i++) {
		lifeLabel.addText("♥");
	}
    }
    new EventHandler() {
    	public void hurtLife(ActionEvent event) {
    		lifePoints--;
    		if (lifePoints == 0) {
    			gameOver();
		} end if;
    		lifeLabel.setText("!!GOT HIT!!");
		wait(Duration.seconds(1));
		refillLife();
    	}
    }
    new EventHandler() {
    	public void healLife(ActionEvent event) {
    		if (lifePoints < MAXLIVES) {
        		lifePoints++;
	    		lifeLabel.setText("!!LIFE GAINED!!");
			wait(Duration.seconds(1));
			refillLife();
		} end if;
    	}
    }
    new EventHandler() {
    	public void updateScore(ActionEvent event, Integer gain) {
    		scorePoints += gain;
    		scoreLabel.setText(scorePoints.toString());
    	}
    }
}
