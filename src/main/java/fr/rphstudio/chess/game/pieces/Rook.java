package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Rook implements IMove {
    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        List<Integer> posX = new ArrayList<Integer>();
        List<Integer> posY = new ArrayList<Integer>();

        // coordinates generator
        for (int i = 0; i < IChess.BOARD_WIDTH; i++) {

            posX.add(p.x + i);
            posX.add(p.x - i);
            posX.add(p.x);
            posX.add(p.x);

            posY.add(p.y);
            posY.add(p.y);
            posY.add(p.y + i);
            posY.add(p.y - i);

        }

        for (int i = 0; i < posX.size(); i++) {
            IChess.ChessPosition dest = new IChess.ChessPosition(posX.get(i), posY.get(i));
            if ((posX.get(i) >= 0 && posX.get(i) < 8 && posY.get(i) >= 0 && posY.get(i) < 8) && board.getPiece(dest) == null) {

                possibleMoves.add(dest);

                //si couleur adverse on arrête au x de la case
                // si couleur alliée on arrête au x avant la case

            }

        }
        return possibleMoves;
    }
}
