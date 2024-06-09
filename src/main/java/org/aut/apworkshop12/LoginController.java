package org.aut.apworkshop12;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class LoginController {
    private String username;

    @FXML
    private TextField usernameLabel;

    @FXML
    private ToggleButton soundToggle;


    @FXML
    void playPressed(ActionEvent event) {
        username = usernameLabel.getText();
        if (username.trim().isEmpty()) return;

        XOApplication.setScene(XOApplication.SceneLevel.GAME);
    }

    public String getUsername() {
        return username;
    }
}
