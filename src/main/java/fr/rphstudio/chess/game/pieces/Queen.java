package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
import fr.rphstudio.chess.game.Piece;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Queen implements IMove {
    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> positions = new ArrayList<>();

        positions.addAll(Piece.getMoveDiagonal(p, board, IChess.BOARD_WIDTH));
        positions.addAll(Piece.getMoveOrthogonal(p, board, IChess.BOARD_WIDTH));

        return positions;
    }
}
