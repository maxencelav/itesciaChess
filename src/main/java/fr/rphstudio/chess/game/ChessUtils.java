package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class ChessUtils {
    /**
     * Returns a ChessPosition if the position can exist on the board
     *
     * @param x position column in the board
     * @param y position row in the board
     * @return Given coordinates as a ChessPosition or null if the position is out of bounds
     */
    public static IChess.ChessPosition checkPositionOnBoard(int x, int y) {
        IChess.ChessPosition dest = new IChess.ChessPosition(x, y);
        if (x >= 0 && x < IChess.BOARD_WIDTH && y >= 0 && y < IChess.BOARD_HEIGHT) { // if the piece is in the board
            return dest; // return the piece
        }
        return null; // else return null
    }

    /**
     * Get moves in a given direction
     *
     * @param dX    Direction for the x coordinates (-1: backwards, 0: not moving, 1: forward)
     * @param dY    Direction for the y coordinates (-1: upwards, 0: not moving, 1: downward)
     * @param p     Initial position
     * @param board Board where the piece is
     * @param size  How far will the method go (1 being all blocks touching the initial piece and 8 covering all the board)
     * @return List of all the possible ChessPosition
     */
    public static List<IChess.ChessPosition> getMovesDirection(int dX, int dY, IChess.ChessPosition p, ChessBoard board, int size) {
        List<IChess.ChessPosition> possibleMoves = new ArrayList<IChess.ChessPosition>();

        for (int dist = 1; dist <= size; dist++) { // from 0 to size
            IChess.ChessPosition pos = checkPositionOnBoard(p.x + (dist * dX), p.y + (dist * dY));

            if (pos != null) { // if the position exists
                Piece pieceAtPos = board.getPiece(pos); // get the piece at the position

                if (pieceAtPos != null) { // if there's a piece
                    if (pieceAtPos.getPieceColor() != board.getPiece(p).getPieceColor()) { // if it's an enemy piece
                        possibleMoves.add(pos); // add to the list since we can "eat" it
                    }
                    break;
                } else { // if no piece
                    possibleMoves.add(pos); // add to the lise since we can go there
                }
            }
        }
        return possibleMoves;
    }

    /**
     * Returns possible moves in diagonal on the board
     *
     * @param p     Position of the piece on the board
     * @param board Board where the piece is
     * @param size  Number of look for each case
     * @return List with the possible moves in diagonal
     */
    public static List<IChess.ChessPosition> getMoveDiagonal(IChess.ChessPosition p, ChessBoard board, int size) {
        List<IChess.ChessPosition> tempList = new ArrayList<>();
        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        // checking moves in all 4 diagonal directions and adding them to the list of possible moves

        tempList = getMovesDirection(1, 1, p, board, size); // down right ->
        possibleMoves.addAll(tempList);

        tempList = getMovesDirection(1, -1, p, board, size); // up right ->
        possibleMoves.addAll(tempList);

        tempList = getMovesDirection(-1, 1, p, board, size); // down left <-
        possibleMoves.addAll(tempList);

        tempList = getMovesDirection(-1, -1, p, board, size); // up left <-
        possibleMoves.addAll(tempList);


        return possibleMoves;
    }

    /**
     * Returns possible moves in orthogonal on the board
     *
     * @param p     Position of the piece on the board
     * @param board Board where the piece is
     * @param size  Number of look for each case
     * @return List with the possible moves in orthogonal
     */
    public static List<IChess.ChessPosition> getMoveOrthogonal(IChess.ChessPosition p, ChessBoard board, int size) {
        List<IChess.ChessPosition> tempList = new ArrayList<>();
        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        // checking moves in all 4 orthogonal directions and adding them to the list of possible moves


        tempList = getMovesDirection(1, 0, p, board, size); // right ->
        possibleMoves.addAll(tempList);

        tempList = getMovesDirection(-1, 0, p, board, size); // left <-
        possibleMoves.addAll(tempList);

        tempList = getMovesDirection(0, 1, p, board, size); // down
        possibleMoves.addAll(tempList);

        tempList = getMovesDirection(0, -1, p, board, size); // up
        possibleMoves.addAll(tempList);


        return possibleMoves;

    }

    /**
     * Returns a boolean if the piece it can be eaten
     *
     * @param pos   Position of the piece on the board
     * @param board Board where the piece is
     * @return true if the piece can get eaten, false if not
     */
    public static boolean canGetEaten(IChess.ChessPosition pos, ChessBoard board) {
        ArrayList<IChess.ChessPosition> positions;

        if (pos != null) { // if pos exists
            Piece pieceAtPos = board.getPiece(pos); // get piece
            if (pieceAtPos != null) { // if piece exists
                positions = getPosInDanger(pieceAtPos.getPieceColor(), board); // get all pieces in danger
                for (IChess.ChessPosition posToCheck : positions) { // for all pieces in danger
                    if (posToCheck.equals(pos)) { // if piece in danger
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Gets all the positions that could get "eaten" by the opposite color
     *
     * @param color Color that could get eaten
     * @param board Board where the pieces are
     * @return List of all the ChessPosition that are at risk for the given color
     */
    public static ArrayList<IChess.ChessPosition> getPosInDanger(IChess.ChessColor color, ChessBoard board) {
        ArrayList<IChess.ChessPosition> positions = new ArrayList<>();

        for (int x = 0; x < IChess.BOARD_WIDTH; x++) {
            for (int y = 0; y < IChess.BOARD_HEIGHT; y++) { // for all blocks on the board
                IChess.ChessPosition pos = ChessUtils.checkPositionOnBoard(x, y);
                if (pos != null) { // if pos exists
                    Piece pieceAtPos = board.getPiece(pos); // get piece
                    if (pieceAtPos != null && pieceAtPos.getPieceColor() != color) { // if piece exists and is of opposite color
                        positions.addAll(pieceAtPos.getMoves(pos, board)); // add all the moves of the enemy piece to the list
                    }
                }
            }
        }
        return positions;
    }

    /**
     * Returns a boolean if the King is in danger
     *
     * @param color      Color of the King to check
     * @param chessBoard Board where the King is
     * @return true if the King is in danger, false if not
     */
    public static boolean isKingInDanger(IChess.ChessColor color, ChessBoard chessBoard) {
        boolean isKingInDanger = false;

        for (int x = 0; x < IChess.BOARD_WIDTH; x++) {
            for (int y = 0; y < IChess.BOARD_HEIGHT; y++) { // for all blocks on the board
                IChess.ChessPosition pos = ChessUtils.checkPositionOnBoard(x, y);
                if (pos != null) { // if pos exists
                    Piece pieceAtPos = chessBoard.getPiece(pos); // get piece
                    if (pieceAtPos != null) { // if piece exists
                        if (pieceAtPos.getPieceType() == IChess.ChessType.TYP_KING && pieceAtPos.getPieceColor() == color) {
                            // if piece is a King and is of given color
                            isKingInDanger = ChessUtils.canGetEaten(pos, chessBoard); // check if the King can get eaten
                        }
                    }
                }
            }
        }

        return isKingInDanger;
    }
}
