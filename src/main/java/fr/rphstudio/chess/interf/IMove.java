package fr.rphstudio.chess.interf;

import fr.rphstudio.chess.game.ChessBoard;

import java.util.List;

/**
 * Interface for the movement of chess pieces
 */
public interface IMove {

    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board);
}
