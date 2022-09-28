package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public final class menuUIController {
    @FXML
    private Button btnPlay;
    
    @FXML
    private Button btnScore;

    @FXML
    public void btnPlayOnClickHandler() {
        menuStage.close();
        startGame();
    }

    @FXML
    public void btnSOnClickHandler() {
        menuStage.close();
        /* startBoard();	sostituire a startBoard il nome dato alla
         *  funzione che apre la visualizzazione del set dei punteggi */
    }
}
