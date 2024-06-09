package org.aut.apworkshop12;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

public class MenuController {

    @FXML
    private Label gameoverLabel;

    @FXML
    private ToggleButton soundToggle;

    @FXML
    void exitPressed(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void startPressed(ActionEvent event) {
        XOApplication.setScene(XOApplication.SceneLevel.LOGIN);
    }

}
