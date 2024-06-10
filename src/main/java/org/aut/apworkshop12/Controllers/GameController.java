package org.aut.apworkshop12.Controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.aut.apworkshop12.utils.GameModel;
import org.aut.apworkshop12.utils.MusicPlayer;
import org.aut.apworkshop12.XOApplication;

import java.util.Timer;
import java.util.TimerTask;

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

    private static BooleanProperty isGameStarted;

    @FXML
    public void initialize() {
        MusicPlayer.addToggle(soundToggle);

        isGameStarted = new SimpleBooleanProperty(false);
        labels = new Label[][]{{label00, label01, label02}, {label10, label11, label12}, {label20, label21, label22}};

        isGameStarted.addListener((observable, oldValue, newValue) -> {
            if (newValue) setUp();
        });
    }

    public void setUp() {
        gameModel = new GameModel();
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels.length; j++) {
                labels[i][j].textProperty().bind(gameModel.getBoard()[i][j]);
            }
        }

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

        gameModel.getGameOverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                endGame(gameModel.getWinState());
            }
        });
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

        gameModel.playerMove(x, y);
    }

    public static void startGame() {
        isGameStarted.setValue(true);
        XOApplication.switchScene(XOApplication.SceneLevel.GAME);
    }

    public static void endGame(GameModel.WinState state) {
        isGameStarted.setValue(false);

        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(() -> MenuController.setGameOver(state));
            }
        };
        Timer timer = new Timer();

        if (state == GameModel.WinState.DRAW)
            timer.schedule(task, 0);
        else
            timer.schedule(task, 800);
    }
}
