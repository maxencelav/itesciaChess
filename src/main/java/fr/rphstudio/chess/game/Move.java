package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class Move {

    ChessBoard savedBoard;
    long turnTime;
    IChess.ChessColor playerColor;

    /**
     * Constructor for a Move
     * @param savedBoard Board at the start of the move
     * @param turnTime Time taken by the player during the move in milliseconds
     * @param playerColor Color of the player who's turn it was
     */
    public Move(ChessBoard savedBoard, long turnTime, IChess.ChessColor playerColor) {
        this.savedBoard = savedBoard;
        this.turnTime = turnTime;
        this.playerColor = playerColor;
    }

    /**
     * Gets the board saved in the move
     * @return ChessBoard of the move
     */
    public ChessBoard getSavedBoard() {
        return savedBoard;
    }

    /**
     * Gets the time a move took
     * @return Time it took for the move to occur
     */
    public long getTurnTime() {
        return turnTime;
    }

    /**
     * Gets the color of the player who's turn it was
     * @return ChessColor of the player
     */
    public IChess.ChessColor getPlayerColor() {
        return playerColor;
    }
}
