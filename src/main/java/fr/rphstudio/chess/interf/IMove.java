package fr.rphstudio.chess.interf;

import fr.rphstudio.chess.game.ChessBoard;

import java.util.List;

public interface IMove {

    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board);
}
