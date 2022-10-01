package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class GameBackgrounds extends Application {

    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 300;

    @Override
    public void startGame(final Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/gameBackgroundsView.fxml"));
        final Scene gameBackgroundsScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        primaryStage.setTitle("Game backgrounds");
        primaryStage.setScene(gameBackgroundsScene);
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch();
    }

}
