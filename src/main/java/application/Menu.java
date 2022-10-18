package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public final class Menu extends Application {

    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 300;
    private static final Stage menuStage = new Stage();

    public static final void openMenu() {
        menuStage.show();
    }

    public static final void hideMenu() {
        menuStage.hide();
    }

    public static final void main(){
        Parent root = null;
        try {
            root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/gameMenuView.fxml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final Scene menuScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        
        menuStage.setTitle("Game menu");
        menuStage.initStyle(StageStyle.DECORATED);
        menuStage.setScene(menuScene);
    	openMenu();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        
    }

}
