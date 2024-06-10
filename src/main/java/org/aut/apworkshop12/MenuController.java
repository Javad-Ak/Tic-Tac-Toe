package org.aut.apworkshop12;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

public class MenuController {
    private static StringProperty winState;

    @FXML
    private Label gameoverLabel;

    @FXML
    private ToggleButton soundToggle;

    @FXML
    public void initialize() {
        MusicPlayer.play();

        winState = new SimpleStringProperty();
        winState.setValue("");
        gameoverLabel.textProperty().bind(winState);

        soundToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) MusicPlayer.play();
            else MusicPlayer.pause();
        });
    }

    @FXML
    void exitPressed(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void startPressed(ActionEvent event) {
        if (LoginController.getUsername() == null)
            XOApplication.switchScene(XOApplication.SceneLevel.LOGIN);
        else
            GameController.startGame();
    }

    public static void setGameOver(GameModel.WinState state) {
        if (state == null)
            winState.setValue("");
        else if (state == GameModel.WinState.WON)
            winState.setValue("You won, " + LoginController.getUsername() + ".");
        else if (state == GameModel.WinState.LOST)
            winState.setValue("You lost, " + LoginController.getUsername() + ".");
        else
            winState.setValue("You draw, " + LoginController.getUsername() + ".");

        XOApplication.switchScene(XOApplication.SceneLevel.MENU);
    }
}
