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

    public static boolean canGetEaten(IChess.ChessPosition pos, ChessBoard board) {
        ArrayList<IChess.ChessPosition> positions;

        if (pos != null) { // if pos exists
            Piece pieceAtPos = board.getPiece(pos); // get piece
            if (pieceAtPos != null) { // if piece exists
                positions = getPosInDanger(pieceAtPos.getPieceColor(), board);
                for (IChess.ChessPosition posToCheck : positions) {
                    if (posToCheck.equals(pos)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Returns
     *
     * @param color Color of the pieces that will get returned
     */
    public static ArrayList<IChess.ChessPosition> getPosInDanger(IChess.ChessColor color, ChessBoard board) {
        ArrayList<IChess.ChessPosition> positions = new ArrayList<>();

        for (int x = 0; x < IChess.BOARD_WIDTH; x++) {
            for (int y = 0; y < IChess.BOARD_HEIGHT; y++) {
                IChess.ChessPosition pos = ChessUtils.checkPositionOnBoard(x, y, board);
                if (pos != null) { // if pos exists
                    Piece pieceAtPos = board.getPiece(pos); // get piece
                    if (pieceAtPos != null && pieceAtPos.getPieceColor() != color) { // if piece exists
                        positions.addAll(pieceAtPos.getMoves(pos, board));
                    }
                }
            }
        }
        return positions;
    }

    public static boolean isKingInDanger(IChess.ChessColor color, ChessBoard chessBoard) {
        boolean isKingInDanger = false;

        for (int x = 0; x < IChess.BOARD_WIDTH; x++) {
            for (int y = 0; y < IChess.BOARD_HEIGHT; y++) {
                IChess.ChessPosition pos = ChessUtils.checkPositionOnBoard(x, y, chessBoard);
                if (pos != null) { // if pos exists
                    Piece pieceAtPos = chessBoard.getPiece(pos); // get piece
                    if (pieceAtPos != null) { // if piece exists
                        if (pieceAtPos.getPieceType() == IChess.ChessType.TYP_KING && pieceAtPos.getPieceColor() == color) {
                            isKingInDanger = ChessUtils.canGetEaten(pos, chessBoard);

                        }
                    }
                }

            }
        }

        return isKingInDanger;
    }
}
