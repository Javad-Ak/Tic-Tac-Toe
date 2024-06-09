package org.aut.apworkshop12;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class GameController {

    @FXML
    private ProgressBar timeBar;

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
    void pressed00(MouseEvent event) {

    }

    @FXML
    void pressed01(MouseEvent event) {

    }

    @FXML
    void pressed02(MouseEvent event) {

    }

    @FXML
    void pressed10(MouseEvent event) {

    }

    @FXML
    void pressed11(MouseEvent event) {

    }

    @FXML
    void pressed12(MouseEvent event) {

    }

    @FXML
    void pressed20(MouseEvent event) {

    }

    @FXML
    void pressed21(MouseEvent event) {

    }

    @FXML
    void pressed22(MouseEvent event) {

    }
}
