package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
import fr.rphstudio.chess.game.ChessUtils;
import fr.rphstudio.chess.game.Piece;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the movement of Knight pieces using IMove
 */
public class Knight implements IMove {

    /**
     * Gets possible moves for the Knight piece
     *
     * @param p     position of the piece on the board
     * @param board where the piece is located
     * @return list of possible positions for the piece
     */
    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board) {

        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        // possible moves for the piece in position X
        int[] posX = {p.x + 1, p.x + 1, p.x + 2, p.x + 2, p.x - 1, p.x - 1, p.x - 2, p.x - 2};
        // possible moves for the piece in position Y
        int[] posY = {p.y - 2, p.y + 2, p.y - 1, p.y + 1, p.y - 2, p.y + 2, p.y - 1, p.y + 1};

        // for every move possible
        for (int i = 0; i < posX.length; i++) {
            // check the position on the board
            IChess.ChessPosition pos = ChessUtils.checkPositionOnBoard(posX[i], posY[i]);
            if (pos != null) { // if position is different of null
                Piece pieceAtPos = board.getPiece(pos); // get piece in this position
                if (pieceAtPos == null) { // if there is no piece
                    possibleMoves.add(pos); // add this position on the list of possible moves
                } else if (pieceAtPos.getPieceColor() != board.getPiece(p).getPieceColor()) { // if this piece is of the enemy color
                    possibleMoves.add(pos); // add this position on the list of possible moves
                }

            }
        }
        return possibleMoves;
    }
}
