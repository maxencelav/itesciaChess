package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class ChessUtils {
    public static IChess.ChessPosition checkPositionOnBoard(int x, int y, ChessBoard board) {
        IChess.ChessPosition dest = new IChess.ChessPosition(x, y);
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            return dest;
        }
        return null;
    }

    public static List<IChess.ChessPosition> getMovesDirection(int dX, int dY, IChess.ChessPosition p, ChessBoard board, int size) {
        List<IChess.ChessPosition> possibleMoves = new ArrayList<IChess.ChessPosition>();

        for (int dist = 1; dist <= size; dist++) {
            IChess.ChessPosition pos = checkPositionOnBoard(p.x + (dist * dX), p.y + (dist * dY), board);


            if (pos != null) {
                Piece pieceAtPos = board.getPiece(pos);

                if (pieceAtPos != null) {
                    if (pieceAtPos.getPieceColor() != board.getPiece(p).getPieceColor()) {
                        possibleMoves.add(pos);
                    }
                    break;
                } else {
                    possibleMoves.add(pos);

                }
            }

        }
        return possibleMoves;
    }

    public static List<IChess.ChessPosition> getMoveDiagonal(IChess.ChessPosition p, ChessBoard board, int size) {
        List<IChess.ChessPosition> tempList = new ArrayList<>();
        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        tempList = getMovesDirection(1, 1, p, board, size);
        possibleMoves.addAll(tempList);

        tempList = getMovesDirection(1, -1, p, board, size);
        possibleMoves.addAll(tempList);

        tempList = getMovesDirection(-1, 1, p, board, size);
        possibleMoves.addAll(tempList);

        tempList = getMovesDirection(-1, -1, p, board, size);
        possibleMoves.addAll(tempList);


        return possibleMoves;

    }

    public static List<IChess.ChessPosition> getMoveOrthogonal(IChess.ChessPosition p, ChessBoard board, int size) {
        List<IChess.ChessPosition> tempList = new ArrayList<>();
        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        tempList = getMovesDirection(1, 0, p, board, size);
        possibleMoves.addAll(tempList);

        tempList = getMovesDirection(-1, 0, p, board, size);
        possibleMoves.addAll(tempList);

        tempList = getMovesDirection(0, 1, p, board, size);
        possibleMoves.addAll(tempList);

        tempList = getMovesDirection(0, -1, p, board, size);
        possibleMoves.addAll(tempList);


        return possibleMoves;

    }
}
