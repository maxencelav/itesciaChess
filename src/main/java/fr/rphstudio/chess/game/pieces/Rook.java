package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
import fr.rphstudio.chess.game.ChessUtils;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.List;

/**
 * Class for the movement of Rook pieces using IMove
 */
public class Rook implements IMove {
    /**
     * Gets possible moves for the Rook piece
     * @param p position of the piece on the board
     * @param board where the piece is located
     * @return list of possible positions for the piece
     */
    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board) {
        return ChessUtils.getMoveOrthogonal(p, board, IChess.BOARD_WIDTH);
        // get all the positions orthogonally, and add them to the list
        // the positions go the the edge of the board, for maximum reach

    }
}
