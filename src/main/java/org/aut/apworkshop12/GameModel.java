package org.aut.apworkshop12;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.util.Random;

public class GameModel {
    private final BooleanProperty isPlayerTurn;
    private final BooleanProperty isGameOver;
    private StringProperty[][] board;
    private WinState winState;

    public enum WinState {
        WON, LOST, DRAW
    }

    public GameModel() {
        isGameOver = new SimpleBooleanProperty(false);
        winState = null;

        isPlayerTurn = new SimpleBooleanProperty(true);
        isPlayerTurn.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue && !isGameOver.get()) {
                new Thread(
                        () -> { // Runnable
                            try {
                                Thread.sleep(800); // prevents too fast response
                            } catch (InterruptedException ignored) {
                            }
                            while (true) {
                                int x = new Random().nextInt(board.length);
                                int y = new Random().nextInt(board.length);
                                if (board[x][y].get().isEmpty()) {
                                    Platform.runLater(() -> computerMove(x, y));
                                    break;
                                }
                            }
                            isPlayerTurn.setValue(true);
                        }
                ).start();
            }
        });

        board = new StringProperty[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) board[i][j] = new SimpleStringProperty("");
        }
    }

    public StringProperty[][] getBoard() {
        return board;
    }

    public void playerMove(int x, int y) {
        board[x][y].setValue("X");
        winState = getGameOverState();
        if (winState != null) isGameOver.set(true);
        isPlayerTurn.setValue(false);
    }

    public void computerMove(int x, int y) {
        board[x][y].setValue("O");
        winState = getGameOverState();
        if (winState != null) isGameOver.set(true);
        isPlayerTurn.setValue(true);
    }

    public void timeUp() {
        winState = WinState.DRAW;
        isGameOver.setValue(true);
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn.get();
    }

    private WinState getGameOverState() {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0].get().equals("X") && board[i][1].get().equals("X") && board[i][2].get().equals("X")) {
                return WinState.WON;
            }

            if (board[0][i].get().equals("X") && board[1][i].get().equals("X") && board[2][i].get().equals("X")) {
                return WinState.WON;
            }

            if (board[0][0].get().equals("X") && board[1][1].get().equals("X") && board[2][2].get().equals("X")) {
                return WinState.WON;
            }

            if (board[2][0].get().equals("X") && board[1][1].get().equals("X") && board[0][2].get().equals("X")) {
                return WinState.WON;
            }

            if (board[i][0].get().equals("O") && board[i][1].get().equals("O") && board[i][2].get().equals("O")) {
                return WinState.LOST;
            }

            if (board[0][i].get().equals("O") && board[1][i].get().equals("O") && board[2][i].get().equals("O")) {
                return WinState.LOST;
            }

            if (board[0][0].get().equals("O") && board[1][1].get().equals("O") && board[2][2].get().equals("O")) {
                return WinState.LOST;
            }

            if (board[2][0].get().equals("O") && board[1][1].get().equals("O") && board[0][2].get().equals("O")) {
                return WinState.LOST;
            }

            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].get().isEmpty()) return null;
            }
        }

        return WinState.DRAW;
    }

    public BooleanProperty getGameOverProperty() {
        return isGameOver;
    }

    public WinState getWinState() {
        return winState;
    }
}
