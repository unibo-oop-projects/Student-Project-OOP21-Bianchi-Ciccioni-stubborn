package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Menu extends Application {

    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 300;

    @Override
    public void startMenu(final Stage menuStage) throws Exception {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/gameMenuView.fxml"));
        final Scene menuScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        menuStage.setTitle("Game menu");
        menuStage.setScene(menuScene);
        menuStage.show();
    }

    public static void main(final String[] args) {
        launch();
    }

}
