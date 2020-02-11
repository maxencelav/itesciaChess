package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
import fr.rphstudio.chess.game.Piece;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements IMove {

    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, ChessBoard board) {

        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        List<Integer> posX = new ArrayList<Integer>();
        List<Integer> posY = new ArrayList<Integer>();

        // coordinates generator
        for (int i = 0; i < IChess.BOARD_WIDTH; i++) {

            posX.add(p.x + i);
            posX.add(p.x + i);
            posX.add(p.x - i);
            posX.add(p.x - i);

            posY.add(p.y + i);
            posY.add(p.y - i);
            posY.add(p.y + i);
            posY.add(p.y - i);

        }

        for (int i = 0; i < posX.size(); i++) {
            IChess.ChessPosition pos = Piece.checkPositionOnBoard(posX.get(i), posY.get(i), board);

            try {

                Piece currentPiece = board.getPiece(p);
                //System.out.println("cP " + currentPiece.getPieceColor());
                Piece wantedPiece = board.getPiece(new IChess.ChessPosition(posX.get(i), posY.get(i)));
                //System.out.println("wP " + wantedPiece.getPieceColor());

                if (pos != null || currentPiece.getPieceColor() != wantedPiece.getPieceColor()) {
                    possibleMoves.add(pos);
                }

            } catch (Exception e) {

            }


            // si couleur adverse on arrête au x de la case
            // si couleur alliée on arrête au x avant la case
        }
        return possibleMoves;
    }
}
