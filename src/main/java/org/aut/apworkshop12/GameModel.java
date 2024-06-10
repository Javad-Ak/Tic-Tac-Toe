package org.aut.apworkshop12;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Random;

public class GameModel {
    private boolean isPlayerTurn;
    private StringProperty[][] board;
    private BooleanProperty isGameOver;
    private WinState winState;

    public enum WinState {
        WON, LOST, DRAW
    }

    public GameModel() {
        isPlayerTurn = true;
        isGameOver = new SimpleBooleanProperty(false);
        winState = null;

        board = new StringProperty[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new SimpleStringProperty();
                board[i][j].set("");
                board[i][j].addListener((observable, oldValue, newValue) -> {
                    if (!newValue.contains("X") || isPlayerTurn() || isGameOver.get()) return;
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
                                        Platform.runLater(() -> {
                                            board[x][y].set("O");
                                            computerMoved();
                                        });
                                        break;
                                    }
                                }
                                isPlayerTurn = true;
                            }
                    ).start();
                });
            }
        }
    }

    public StringProperty[][] getBoard() {
        return board;
    }

    public void playerMoved() {
        isPlayerTurn = false;
        winState = getGameOverState();
        if (winState != null) isGameOver.set(true);
    }

    public void computerMoved() {
        isPlayerTurn = true;
        winState = getGameOverState();
        if (winState != null) isGameOver.set(true);
    }

    public void timeUp(){
        winState = WinState.DRAW;
        isGameOver.set(true);
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    private WinState getGameOverState() {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0].get().equals("X") || board[i][1].get().equals("X") || board[i][2].get().equals("X")) {
                return WinState.WON;
            }

            if (board[0][i].get().equals("X") || board[1][i].get().equals("X") || board[2][i].get().equals("X")) {
                return WinState.WON;
            }

            if (board[0][0].get().equals("X") || board[1][1].get().equals("X") || board[2][2].get().equals("X")) {
                return WinState.WON;
            }

            if (board[2][0].get().equals("X") || board[1][1].get().equals("X") || board[0][2].get().equals("X")) {
                return WinState.WON;
            }

            if (board[i][0].get().equals("O") || board[i][1].get().equals("O") || board[i][2].get().equals("O")) {
                return WinState.LOST;
            }

            if (board[0][i].get().equals("O") || board[1][i].get().equals("O") || board[2][i].get().equals("O")) {
                return WinState.LOST;
            }

            if (board[0][0].get().equals("O") || board[1][1].get().equals("O") || board[2][2].get().equals("O")) {
                return WinState.LOST;
            }

            if (board[2][0].get().equals("O") || board[1][1].get().equals("O") || board[0][2].get().equals("O")) {
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
