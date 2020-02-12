package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
import fr.rphstudio.chess.game.ChessUtils;
import fr.rphstudio.chess.game.Piece;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements IMove {

    private boolean firstTurn = true;

    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        int size = 1;
        if (board.getPiece(p).getNumberOfTurns() == 0) {
            size = 2;
        }

        int dY = -1;
        if (board.getPiece(p).getPieceColor() == IChess.ChessColor.CLR_BLACK) {
            dY = 1;
        }


        for (int dist = 1; dist <= size; dist++) {
            IChess.ChessPosition pos = ChessUtils.checkPositionOnBoard(p.x, p.y + (dist * dY), board);

            if (pos != null) { // if position exists on the board
                Piece pieceAtPos = board.getPiece(pos);

                if (pieceAtPos != null) { // if piece exists on position
                    break;
                } else {
                    possibleMoves.add(pos);
                }
            }
        }

        // check two front pieces diagonally
        for (int dX = -1; dX <= 1; dX += 2) {
            IChess.ChessPosition frontPos = ChessUtils.checkPositionOnBoard(p.x + (dX), p.y + (1 * dY), board);

            if (frontPos != null) { // if pos exists
                Piece pieceAtPos = board.getPiece(frontPos);
                if (pieceAtPos != null && pieceAtPos.getPieceColor() != board.getPiece(p).getPieceColor()) {
                    // if piece exists & is diff. color
                    possibleMoves.add(frontPos);
                }
            }
        }

        return possibleMoves;
    }
}
