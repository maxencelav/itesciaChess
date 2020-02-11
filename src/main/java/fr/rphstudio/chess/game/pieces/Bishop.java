package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements IMove {

    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        possibleMoves.add((new IChess.ChessPosition(3,5)));

        return possibleMoves;
    }
}
