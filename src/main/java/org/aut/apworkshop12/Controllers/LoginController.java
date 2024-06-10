package org.aut.apworkshop12.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import org.aut.apworkshop12.utils.MusicPlayer;

public class LoginController {
    private static String username = null;

    @FXML
    private TextField usernameLabel;

    @FXML
    private ToggleButton soundToggle;

    @FXML
    public void initialize() {
        MusicPlayer.addToggle(soundToggle);
    }

    @FXML
    void playPressed(ActionEvent event) {
        username = usernameLabel.getText().trim();
        if (username.isEmpty()) {
            usernameLabel.setText("Please enter a username");
            usernameLabel.selectAll();
            usernameLabel.requestFocus();

            return;
        }

        GameController.startGame();
    }

    public static String getUsername() {
        return username;
    }
}
