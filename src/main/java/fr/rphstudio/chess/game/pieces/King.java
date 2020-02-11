package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
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

        for (int i = 0; i < 8; i++) {
            IChess.ChessPosition dest = new IChess.ChessPosition(posX[i], posY[i]);
            if ((posX[i] >= 0 && posX[i] < 8 && posY[i] >= 0 && posY[i] < 8) && board.getPiece(dest) == null) {
                possibleMoves.add(dest);

            }

        }


        return possibleMoves;
    }


}

