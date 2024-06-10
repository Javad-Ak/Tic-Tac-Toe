package org.aut.apworkshop12;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;

public class XOApplication extends Application {
    private static Scene menuScene;
    private static Scene loginScene;
    private static Scene gameScene;

    private static Stage primaryStage;

    enum SceneLevel {
        MENU, LOGIN, GAME
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        menuScene = new Scene(new FXMLLoader(XOApplication.class.getResource("menu.fxml")).load());
        loginScene = new Scene(new FXMLLoader(XOApplication.class.getResource("menu.fxml")).load());
        gameScene = new Scene(new FXMLLoader(XOApplication.class.getResource("menu.fxml")).load());

        primaryStage = stage;
        setScene(SceneLevel.MENU);
        primaryStage.setTitle("Tic Tac Toe");

        setResponsiveUI();
        primaryStage.show();
    }

    static void setScene(SceneLevel level) {
        try {
            if (primaryStage == null || menuScene == null || gameScene == null || loginScene == null)
                throw new IllegalStateException();

            switch (level) {
                case MENU -> primaryStage.setScene(menuScene);
                case LOGIN -> primaryStage.setScene(loginScene);
                case GAME -> primaryStage.setScene(gameScene);
            }
        } catch (IllegalStateException e) {
            System.exit(1);
        }
    }

    private static void setResponsiveUI() {
        double width = Screen.getPrimary().getBounds().getWidth();
        double height = Screen.getPrimary().getBounds().getHeight();

        primaryStage.getScene().getRoot().setStyle("-fx-font-size: " + (int) (20 * width * height / 1920 / 1080) + ";");
        primaryStage.setMinWidth(width / 1920 * 400);
        primaryStage.setMinHeight(height / 1080 * 500);
        primaryStage.setResizable(false);
    }
}