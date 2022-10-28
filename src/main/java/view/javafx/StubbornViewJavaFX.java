package view.javafx;

import java.util.function.Consumer;

import com.sun.prism.paint.Color;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import models.MOVEMENT;
import models.WorldMap;
import view.StubbornView;

public class StubbornViewJavaFX implements StubbornView {
    
    private Stage boardStage;

    public StubbornViewJavaFX() {
        /*try {
         this.boardStage = boardStage;
         this.boardStage.setTitle("Stubborn");
         Parent root = new Parent();
         // scene creation
         Scene sc = new Scene(, 280, 280);
         // background fill creation
         BackgroundFill bf = new BackgroundFill(Paint.valueOf("#009688"),
         CornerRadii.EMPTY , Insets.EMPTY);
         //Background creation
         Background bg = new Background(bf);
         // set background
         hb.setBackground(bg);
         // scene setting
         this.boardStage.setScene(sc);
         }
         catch (Exception e) {
         System.out.println(e.getMessage());
         }*/
    }
    
    @Override
    public void updateWorldMap(WorldMap board) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addDirectionalKeyPressHandler(Consumer<MOVEMENT> handler) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeDirectionalKeyPressHandler(Consumer<MOVEMENT> handler) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addStartHandler(Runnable startHandler) {
        // TODO Auto-generated method stub
        
    }

}
