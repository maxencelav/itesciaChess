package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;

/**
 * Class for the moves/turns of a chess game
 */
public class PieceMoves {
    private ArrayList<Move> moves;


    /**
     * Creates a PieceMoves list and adds the board, round time, and player color given as the very first step
     *
     * @param savedBoard  Board of the move
     * @param roundTime   Time the move took
     * @param playerColor Color of the current player
     */
    public PieceMoves(ChessBoard savedBoard, long roundTime, IChess.ChessColor playerColor) {
        moves = new ArrayList<>();
        this.addMove(savedBoard, roundTime, playerColor);
    }

    /**
     * Adds a move to the PieceMoves list
     *
     * @param savedBoard  Board of the move
     * @param roundTime   Time the move took
     * @param playerColor Color of the current player
     */
    public void addMove(ChessBoard savedBoard, long roundTime, IChess.ChessColor playerColor) {
        moves.add(new Move(savedBoard, roundTime, playerColor));
    }


    /**
     * Gets the very last move played in the PieceMove
     *
     * @return last Move added to the PieceMove
     */
    public Move getLastMove() {
        try {
            return moves.get(moves.size() - 1);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Removes the very last move played in the PieceMove
     */
    public void removeLastMove() {
        try {
            moves.remove(moves.size() - 1);
        } catch (Exception e) {
        }

    }

    /**
     * Gets the number of turns (elements) in the PieceMove list
     *
     * @return number of turns played
     */
    public int getNumberOfTurns() {
        return moves.size();
    }

}
