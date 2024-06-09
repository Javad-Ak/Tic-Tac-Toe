package org.aut.apworkshop12;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameModel {
    private boolean isPlayerTurn;
    private IntegerProperty[][] board;

    public GameModel() {
        isPlayerTurn = true;

        board = new IntegerProperty[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new SimpleIntegerProperty();
            }
        }
    }

    public enum WinState {
        WON, LOST, DRAW
    }

    public IntegerProperty[][] getBoard() {
        return board;
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }
}
