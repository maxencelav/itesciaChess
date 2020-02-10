package fr.rphstudio.chess.interf;

import java.util.List;

public interface IMove {

    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p);
}
