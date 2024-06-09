package org.aut.apworkshop12;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class LoginController {
    private static String username = null;

    @FXML
    private TextField usernameLabel;

    @FXML
    private ToggleButton soundToggle;

    @FXML
    public void initialize() {
        soundToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) MusicPlayer.play();
            else MusicPlayer.pause();
        });
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

        XOApplication.setScene(XOApplication.SceneLevel.GAME);
    }

    public static String getUsername() {
        return username;
    }
}
