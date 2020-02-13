package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;

public class PieceMoves {
    private ArrayList<Move> moves;


    public PieceMoves(ChessBoard savedBoard, long roundTime, IChess.ChessColor playerColor) {

        moves = new ArrayList<>();
        this.addMove(savedBoard, roundTime, playerColor);

    }

    public void reset(long roundTime, IChess.ChessColor playerColor) {

        moves = new ArrayList<>();


    }

    public void addMove(ChessBoard savedBoard, long roundTime, IChess.ChessColor playerColor) {
        moves.add(new Move(savedBoard, roundTime, playerColor));
    }


    public Move getLastMove() {
        try {

            return moves.get(moves.size() - 1);

        } catch (Exception e) {

        }
        return null;
    }

    public void removeLastMove() {
        try {
            moves.remove(moves.size() - 1);
        } catch (Exception e) {

        }

    }

    public int getNumberOfTurns() {
        return moves.size();
    }

}
