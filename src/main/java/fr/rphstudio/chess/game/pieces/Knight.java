package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
import fr.rphstudio.chess.game.ChessUtils;
import fr.rphstudio.chess.game.Piece;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Knight implements IMove {

    public Knight() {
    }

    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board) {

        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        int[] posX = {p.x + 1, p.x + 1, p.x + 2, p.x + 2, p.x - 1, p.x - 1, p.x - 2, p.x - 2};
        int[] posY = {p.y - 2, p.y + 2, p.y - 1, p.y + 1, p.y - 2, p.y + 2, p.y - 1, p.y + 1};

        for (int i = 0; i < posX.length; i++) {
            IChess.ChessPosition pos = ChessUtils.checkPositionOnBoard(posX[i], posY[i], board);
            if (pos != null) {
                Piece pieceAtPos = board.getPiece(pos);
                if (pieceAtPos == null) {
                    possibleMoves.add(pos);
                } else if (pieceAtPos.getPieceColor() != board.getPiece(p).getPieceColor()) {
                    possibleMoves.add(pos);
                }

            }
        }
        return possibleMoves;
    }
}
