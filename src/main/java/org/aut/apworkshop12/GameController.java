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

    private GameModel gameModel;

    private Label[][] labels;

    @FXML
    public void initialize() {
        soundToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) MusicPlayer.play();
            else MusicPlayer.pause();
        });

        setUp();
    }

    public void setUp() {
        labels = new Label[][]{{label00, label01, label02}, {label10, label11, label12}, {label20, label21, label22}};
        gameModel = new GameModel();
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels.length; j++) {
                labels[i][j].textProperty().bind(gameModel.getBoard()[i][j]);
            }
        }

        gameModel.getGameOverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) MenuController.setGameOver(gameModel.getWinState());
        });

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(timeBar.progressProperty(), 0)), // -> first frame
                new KeyFrame(// -> last frame
                        Duration.minutes(1)
                        , e -> gameModel.timeUp()
                        , new KeyValue(timeBar.progressProperty(), 1))
        );
        timeline.setAutoReverse(false);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void pressed00(MouseEvent event) {
        cross(0, 0);
    }

    @FXML
    void pressed01(MouseEvent event) {
        cross(0, 1);
    }

    @FXML
    void pressed02(MouseEvent event) {
        cross(0, 2);
    }

    @FXML
    void pressed10(MouseEvent event) {
        cross(1, 0);
    }

    @FXML
    void pressed11(MouseEvent event) {
        cross(1, 1);
    }

    @FXML
    void pressed12(MouseEvent event) {
        cross(1, 2);
    }

    @FXML
    void pressed20(MouseEvent event) {
        cross(2, 0);
    }

    @FXML
    void pressed21(MouseEvent event) {
        cross(2, 1);
    }

    @FXML
    void pressed22(MouseEvent event) {
        cross(2, 2);
    }

    private void cross(int x, int y) {
        if (!gameModel.isPlayerTurn() || !gameModel.getBoard()[x][y].isEqualTo("").get() || gameModel.getGameOverProperty().get())
            return;

        gameModel.playerMoved();
        gameModel.getBoard()[x][y].set("X");
        gameModel.playerMoved();
    }
}
