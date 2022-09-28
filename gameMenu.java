import javafx.scene.control.Button;

public static void main(String[] args) {
    Application.launch(args);
}
 
public void startMenu(Stage menuStage) {
	 
    menuStage.setTitle("Game menu");
    Group menu = new Group();
    Scene menuscene = new Scene(menu, 300, 250);

    Label lbTopCenter = new Label("Stubborn");
    lbTopCenter.setAlignment(Pos.CENTER);
    lbTopCenter.setLayoutX(50);
    lbTopCenter.setLayoutY(250);
    menu.getChildren().add(lbTopCenter);

    Button btBottomLeft = new Button("New game");
    lbTopCenter.setLayoutX(400);
    lbTopCenter.setLayoutY(250);
    btBottomLeft.setOnAction(new EventHandler() {
    	public void handle(ActionEvent event) {
    		startGame();
    	}
    });
    menu.getChildren().add(btBottomLeft);
    
    Button btBottomRight = new Button("Scoreboards");
    btBottomLeft.setOnAction(new EventHandler() {
    	public void handle(ActionEvent event) {
    		startScoreboard(); //inizializzare la variabile che memorizza i punteggi a 0
    	}
    });
    menu.getChildren().add(btBottomRight);
    
    menuStage.setScene(menuscene);
    menuStage.show();
}