package org.aut.apworkshop12;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class GameController {

    @FXML
    private ProgressBar timeBar;

    @FXML
    private ToggleButton soundToggle;

    @FXML
    private Label label00;

    @FXML
    private Label label01;

    @FXML
    private Label label02;

    @FXML
    private Label label10;

    @FXML
    private Label label11;

    @FXML
    private Label label12;

    @FXML
    private Label label20;

    @FXML
    private Label label21;

    @FXML
    private Label label22;

    @FXML
    public void initialize() {
        soundToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) MusicPlayer.play();
            else MusicPlayer.pause();
        });

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(timeBar.progressProperty(), 0)),
                new KeyFrame(Duration.minutes(1), e -> {
                    MenuController.setGameOver(GameModel.WinState.DRAW);
                }, new KeyValue(timeBar.progressProperty(), 1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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
