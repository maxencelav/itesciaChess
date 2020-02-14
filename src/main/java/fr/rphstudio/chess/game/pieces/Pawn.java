package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
import fr.rphstudio.chess.game.ChessUtils;
import fr.rphstudio.chess.game.Piece;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the movement of Pawn pieces using IMove
 */
public class Pawn implements IMove {

    /**
     * Gets possible moves for the Pawn piece
     *
     * @param p     position of the piece on the board
     * @param board where the piece is located
     * @return list of possible positions for the piece
     */
    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        int size = 1;
        if (board.getPiece(p).getNumberOfTurns() == 0) { //if this is the first turn of the pawn
            size = 2; //make it so it can go 2 blocks forward
        }

        int dY = -1; // make the piece go up on the board
        if (board.getPiece(p).getPieceColor() == IChess.ChessColor.CLR_BLACK) {
            dY = 1; // make the piece go down on the board
        }

        // for the number of blocks the pawn can advance
        for (int dist = 1; dist <= size; dist++) {
            IChess.ChessPosition pos = ChessUtils.checkPositionOnBoard(p.x, p.y + (dist * dY));

            if (pos != null) { // if position exists on the board
                Piece pieceAtPos = board.getPiece(pos);

                if (pieceAtPos != null) { // if piece exists on position
                    break; // can't go there, break the loop
                } else { // if the position is empty
                    possibleMoves.add(pos); // can go there, add to the loop
                }
            }
        }

        // check two front pieces diagonally
        for (int dX = -1; dX <= 1; dX += 2) {
            IChess.ChessPosition frontPos = ChessUtils.checkPositionOnBoard(p.x + (dX), p.y + (1 * dY));

            if (frontPos != null) { // if position exists on the board
                Piece pieceAtPos = board.getPiece(frontPos);
                if (pieceAtPos != null && pieceAtPos.getPieceColor() != board.getPiece(p).getPieceColor()) {
                    // if piece exists & is diff. color
                    possibleMoves.add(frontPos); // can kill that piece, add the position to the list
                }
            }
        }

        return possibleMoves;
    }
}
