package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class Move {

    ChessBoard savedBoard;
    long turnTime;
    IChess.ChessColor playerColor;

    public Move(ChessBoard savedBoard, long turnTime, IChess.ChessColor playerColor) {
        this.savedBoard = savedBoard;
        this.turnTime = turnTime;
        this.playerColor = playerColor;
    }

    public ChessBoard getSavedBoard() {
        return savedBoard;
    }

    public long getTurnTime() {
        return turnTime;
    }

    public IChess.ChessColor getPlayerColor() {
        return playerColor;
    }
}
