package org.aut.apworkshop12;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

public class MenuController {
    private static GameModel.WinState winState = null;

    @FXML
    private Label gameoverLabel;

    @FXML
    private ToggleButton soundToggle;

    @FXML
    public void initialize() {
        MusicPlayer.play();

        if (winState == null)
            gameoverLabel.setText("");
        else if (winState == GameModel.WinState.WON)
            gameoverLabel.setText("You won, " + LoginController.getUsername() + ".");
        else if (winState == GameModel.WinState.LOST)
            gameoverLabel.setText("You lost, " + LoginController.getUsername() + ".");
        else
            gameoverLabel.setText("Draw: Time up, " + LoginController.getUsername() + ".");

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
            XOApplication.setScene(XOApplication.SceneLevel.LOGIN);
        else
            XOApplication.setScene(XOApplication.SceneLevel.GAME);
    }

    public static void setGameOver(GameModel.WinState winState) {
        MenuController.winState = winState;
        XOApplication.setScene(XOApplication.SceneLevel.MENU);
    }
}
