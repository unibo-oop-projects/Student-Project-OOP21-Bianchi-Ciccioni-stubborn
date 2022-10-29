package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import models.Pair;
import models.Scores;
import models.ScoresImpl;

public class ScoreController {
    
    @FXML
    private Pane scorePane;
     
    @FXML
    private TableView<List<Pair<String, Integer>>> scoreTable;

    @FXML
    private TableColumn<Scores, Integer> score;

    @FXML
    private TableColumn<Scores, String> name;
    
    // questo metodo causa errore perche' non definito nel namespace, quale metodo dovrei usare o dove vovrei dichiarare questo? 
    public void loadData(ArrayList<Pair<String, Integer>> scores) {
        ObservableList<Pair<String, Integer>> data = FXCollections.<Pair<String, Integer>>observableArrayList();
        data.addAll(scores);

        data.forEach(s -> {
            System.out.println("name: " +  s.getX() + "score: " + s.getY());
            //scoreTable.getItems().addAll(new Pair<String, Integer>(s.getX(), s.getY())); //e' qui il problema, come faccio ad aggiungere ad ogni colonna il suo valore?
        }); 
    }
    
}


