package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    public static final void main() {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/gameMenuView.fxml"));
        final Scene menuScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        
        menuStage.setTitle("Game menu");
        menuStage.initStyle(StageStyle.DECORATED);
        menuStage.setScene(menuScene);
    	openMenu();
    }

}
