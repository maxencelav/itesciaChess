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

        positions.addAll(ChessUtils.getMoveDiagonal(p, board, 1));
        positions.addAll(ChessUtils.getMoveOrthogonal(p, board,1));

        return positions;
    }


}

