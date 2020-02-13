package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
import fr.rphstudio.chess.game.ChessUtils;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class King implements IMove {
    /**
     * Gets possible moves for the King piece
     *
     * @param p     position of the piece on the board
     * @param board where the piece is located
     * @return list of the possible positions for the piece
     */
    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> positions = new ArrayList<>();

        int kingLine = IChess.BOARD_POS_Y_BLACK_PIECES;

        if (board.getPiece(p).getPieceColor() == IChess.ChessColor.CLR_WHITE) {
            kingLine = IChess.BOARD_POS_Y_WHITE_PIECES;
        }

        positions.addAll(ChessUtils.getMoveDiagonal(p, board, 1));
        positions.addAll(ChessUtils.getMoveOrthogonal(p, board, 1));
        //gets all the positions 1 case around the king

        try {
            if (board.getPiece(p).getNumberOfTurns() == 0) { // if king hasn't moved

                //check if big roque is possible
                // check if the leftmost piece is a rook and if the positions between the king and the rook are empty
                if (board.getPiece(new IChess.ChessPosition(0, kingLine)).getPieceType() == IChess.ChessType.TYP_ROOK &&
                        board.getPiece(new IChess.ChessPosition(1, kingLine)) == null &&
                        board.getPiece(new IChess.ChessPosition(2, kingLine)) == null &&
                        board.getPiece(new IChess.ChessPosition(3, kingLine)) == null) {
                    positions.add(new IChess.ChessPosition(2, kingLine));
                }

                // check if small roque is possible
                // check if the rightmost piece is a rook and if the positions between the king and the rook are empty
                if (board.getPiece(new IChess.ChessPosition(0, kingLine)).getPieceType() == IChess.ChessType.TYP_ROOK &&
                        board.getPiece(new IChess.ChessPosition(5, kingLine)) == null &&
                        board.getPiece(new IChess.ChessPosition(6, kingLine)) == null) {
                    positions.add(new IChess.ChessPosition(6, kingLine));
                }
            }
        } catch (NullPointerException ignored) {
        }
        // throws error if rook doesn't exist and thus exits the roque move 

        return positions;
    }


}

