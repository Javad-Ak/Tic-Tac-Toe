package org.aut.apworkshop12;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class XOApplication extends Application {
    private static final FXMLLoader menuFxml = new FXMLLoader(XOApplication.class.getResource("menu.fxml"));
    private static final FXMLLoader loginFxml = new FXMLLoader(XOApplication.class.getResource("login.fxml"));
    private static final FXMLLoader gameFxml = new FXMLLoader(XOApplication.class.getResource("game.fxml"));

    private static Stage primaryStage;

    enum SceneLevel {
        MENU, LOGIN, GAME
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        setScene(SceneLevel.MENU);
        primaryStage.setTitle("Tic Tac Toe");

        setResponsiveUI();
        primaryStage.show();
    }

    static void setScene(SceneLevel level) {
        try {
            if (primaryStage == null) throw new IllegalStateException("No primary stage");

            switch (level) {
                case MENU -> primaryStage.setScene(new Scene(menuFxml.load()));
                case LOGIN -> primaryStage.setScene(new Scene(loginFxml.load()));
                case GAME -> primaryStage.setScene(new Scene(gameFxml.load()));
            }
        } catch (IOException | IllegalStateException e) {
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