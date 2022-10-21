package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class menuUIController {
    /*
    @FXML
    private static final Button btnPlay;
    
    @FXML
    private static final Button btnScore;

    @FXML
    public static void btnPlayOnClickHandler() {
        application.Menu.hideMenu();
        application.Backgrounds.main();
        controllers.backgroundsUIController.startTimer();
        controllers.backgroundsUIController.refillLife();
    }

    @FXML
    public static void btnScoreOnClickHandler() {
        application.Menu.hideMenu();
        /* startBoard();	sostituire a startBoard il nome dato alla
         *  funzione che apre la visualizzazione del set dei punteggi */ /*
    }
    */
    
    private static final int SCENE_WIDTH = 1000;
    private static final int SCENE_HEIGHT = 1000;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private Button scoreButton;
    
    @FXML
    private Pane mainPane;
    
    public menuUIController() {
        System.out.println("test");
    }
    
    public void newGame() {
        Stage boardStage = (Stage)this.mainPane.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/board.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        boardStage.setScene(scene);
        boardStage.show();
    }
    
    public void viewScores() {
        Stage boardStage = (Stage)this.mainPane.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/scores.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        boardStage.setScene(scene);
        boardStage.show();
    }
    
    public void exit() {
        System.out.println("I hope to see you again");
        System.exit(0);
    }
}
