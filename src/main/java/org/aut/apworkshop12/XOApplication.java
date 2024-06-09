package org.aut.apworkshop12;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class XOApplication extends Application {
    private static final FXMLLoader menuFxml = new FXMLLoader(XOApplication.class.getResource("menu.fxml"));
    private static final FXMLLoader loginFxml = new FXMLLoader(XOApplication.class.getResource("login.fxml"));
    private static final FXMLLoader gameFxml = new FXMLLoader(XOApplication.class.getResource("game.fxml"));

    private static Scene primaryScene;

    enum SceneLevel {
        MENU, LOGIN, GAME
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        setScene(SceneLevel.MENU);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(primaryScene);

        setResponsiveUI(stage);
        stage.show();
    }

    static void setScene(SceneLevel level) {
        try {
            switch (level) {
                case MENU -> primaryScene = new Scene(menuFxml.load());
                case LOGIN -> primaryScene = new Scene(loginFxml.load());
                case GAME -> primaryScene = new Scene(gameFxml.load());
            }
        } catch (IOException e) {
            System.exit(1);
        }
    }

    private static void setResponsiveUI(Stage stage) {
        double width = Screen.getPrimary().getBounds().getWidth();
        double height = Screen.getPrimary().getBounds().getHeight();

        stage.getScene().getRoot().setStyle("-fx-font-size: " + (int) (20 * width * height / 1920 / 1080) + ";");
        stage.setMinWidth(width / 1920 * 400);
        stage.setMinHeight(height / 1080 * 500);
        stage.setResizable(false);
    }
}