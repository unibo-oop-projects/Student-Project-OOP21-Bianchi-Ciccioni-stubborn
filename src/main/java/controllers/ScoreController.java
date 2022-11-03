package controllers;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Pair;
import models.Scores;
import models.ScoresImpl;

public class ScoreController {
    
    private final Scores s = new ScoresImpl();
    
    @FXML
    private Stage boardStage;
    
    @FXML
    private Pane scorePane;
     
    @FXML
    private TableView<List<Pair<String, Integer>>> scoreTable;

    @FXML
    private TableColumn<String, String> score;

    @FXML
    private TableColumn<String, String> name;
    
    @FXML
    private void initialize() {
        System.out.println(s.getAllScores());
        Platform.runLater(() -> this.initializeView());
    }
    
    private void initializeView() {
        List<String> n = new ArrayList<>();
        List<String> l = new ArrayList<>();
        for(var e : s.getAllScores()) {
            n.add(e.getX());
            l.add(e.getY().toString());
        }
        ObservableList<Pair<String, Integer>> data = FXCollections.<Pair<String, Integer>>observableArrayList();
        ObservableList<String> dataName = FXCollections.<String>observableArrayList(n);
        ObservableList<String> dataScore = FXCollections.<String>observableArrayList(l);
        System.out.println(n.toString());
        System.out.println(l.toString());
        data.addAll(s.getAllScores());
        //System.out.println("data print");
        //System.out.println(data.toString());
        //name.setCellValueFactory(new PropertyValueFactory<String, String>(n));
        //score.setCellValueFactory(new PropertyValueFactory<Pair<String, Integer>, Integer>(data.get(0).getY().toString()));
        dataName.forEach(s -> {
            //System.out.println("name: " +  s.getX() + " " + "score: " + s.getY());
            name.setCellValueFactory(new PropertyValueFactory<String, String>(s));
        });
        dataScore.forEach(s -> {
            //System.out.println("name: " +  s.getX() + " " + "score: " + s.getY());
            score.setCellValueFactory(new PropertyValueFactory<String, String>(s));
        });
        
        scoreTable.getItems().add(data);
    }
    
}


