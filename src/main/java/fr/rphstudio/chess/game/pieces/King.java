package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
import fr.rphstudio.chess.game.Piece;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class King implements IMove {

    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        int[] posX = {p.x + 1, p.x + 1, p.x + 1, p.x, p.x , p.x - 1, p.x - 1, p.x - 1};
        int[] posY = {p.y, p.y + 1, p.y - 1, p.y + 1, p.y - 1, p.y , p.y - 1, p.y + 1};

        for (int i = 0; i < posX.length; i++) {
            IChess.ChessPosition pos = Piece.checkMoves(posX[i], posY[i], board);
            if (pos != null) {
                possibleMoves.add(pos);
            }
        }
        return possibleMoves;
    }


}

