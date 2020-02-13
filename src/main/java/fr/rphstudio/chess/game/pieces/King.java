package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
import fr.rphstudio.chess.game.ChessUtils;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class King implements IMove {

    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> positions = new ArrayList<>();

        int kingLine = IChess.BOARD_POS_Y_BLACK_PIECES;

        if (board.getPiece(p).getPieceColor() == IChess.ChessColor.CLR_WHITE) {
            kingLine = IChess.BOARD_POS_Y_WHITE_PIECES;
        }

        positions.addAll(ChessUtils.getMoveDiagonal(p, board, 1));
        positions.addAll(ChessUtils.getMoveOrthogonal(p, board, 1));

        try {
            if (board.getPiece(p).getNumberOfTurns() == 0) { // if king hasn't moved
                if (board.getPiece(new IChess.ChessPosition(0, kingLine)).getPieceType() == IChess.ChessType.TYP_ROOK &&
                        board.getPiece(new IChess.ChessPosition(1, kingLine)) == null &&
                        board.getPiece(new IChess.ChessPosition(2, kingLine)) == null &&
                        board.getPiece(new IChess.ChessPosition(3, kingLine)) == null) {
                    positions.add(new IChess.ChessPosition(2, kingLine));
                } else if (board.getPiece(new IChess.ChessPosition(0, kingLine)).getPieceType() == IChess.ChessType.TYP_ROOK &&
                        board.getPiece(new IChess.ChessPosition(5, kingLine)) == null &&
                        board.getPiece(new IChess.ChessPosition(6, kingLine)) == null) {
                    positions.add(new IChess.ChessPosition(6, kingLine));
                }
            }
        } catch (NullPointerException ignored){}
        // throws error if rook doesn't exist and thus exits the roque move 

        return positions;
    }


}

