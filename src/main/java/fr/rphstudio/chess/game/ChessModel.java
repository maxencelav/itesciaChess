package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.List;

public class ChessModel implements IChess {

    private ChessBoard chessBoard;
    private TimeManager timer;
    private PieceMoves pieceMoves;


    private ChessModel() {
        chessBoard = new ChessBoard(); // create a chess board
        timer = new TimeManager(); // create a timer
        pieceMoves = new PieceMoves(chessBoard, 0, ChessColor.CLR_WHITE);
        // generate a piece movement history with the first movement being the basic board
    }

    /**
     * Unique pre-initialized instance
     * (Instance unique pré-initialisée)
     */
    private static ChessModel INSTANCE = new ChessModel();


    public static IChess getInstance() {
        return INSTANCE;
    }

    /**
     * Reset the chess board, the timer and pieces's movement
     */
    @Override
    public void reinit() {
        this.chessBoard = new ChessBoard(); // generate a new chessboard
        this.timer.resetTimer(); // reset the timer
        this.pieceMoves = new PieceMoves(chessBoard, 0, ChessColor.CLR_WHITE);
        // generate a piece movement history with the first movement being the (new) basic board

    }

    /**
     * Get a piece's type
     *
     * @param p x/y position on the board where we want to get the piece type.
     * @return the piece type
     * @throws EmptyCellException
     * @throws OutOfBoardException
     */
    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {

        Piece currentPiece = chessBoard.getPiece(p);
        if (currentPiece != null) {
            return currentPiece.getPieceType();
        } else {
            throw new EmptyCellException();
        }
    }

    /**
     * Get a piece's color
     *
     * @param p x/y position on the board where we want to get the piece color.
     * @return the piece color
     * @throws EmptyCellException
     * @throws OutOfBoardException
     */
    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {

        Piece currentPiece = chessBoard.getPiece(p);
        if (currentPiece != null) {
            return currentPiece.getPieceColor();
        } else {
            throw new EmptyCellException();
        }
    }

    /**
     * Get the number of pieces remaining on the board
     *
     * @param color the requested color of the pieces to count.
     * @return the number of piece remaining for the given color
     */
    @Override
    public int getNbRemainingPieces(ChessColor color) {

        int colorCounter = 0;

        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) { // for each piece on the board
                try {
                    if (chessBoard.getPiece(new ChessPosition(x, y)).getPieceColor() == color) {
                        // if piece is of the color wanted
                        // incrementation of the counter of remaining pieces for the given color
                        colorCounter++;
                    }
                } catch (NullPointerException e) {
                }
            }
        }
        return colorCounter;
    }

    /**
     * Gets the possible positions for a piece's movement
     *
     * @param p requested piece position.
     * @return a list containing ChessPosition elements, that are the possible moves
     */
    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        Piece currentPiece = chessBoard.getPiece(p);

        List<ChessPosition> moveList = chessBoard.getPiece(p).getMoves(p, chessBoard);
        List<ChessPosition> safeMoveList = new ArrayList<>();

        // get all moves and only return ones that won't kill the king
        for (ChessPosition positionToCheck : moveList) {
            ChessBoard clonedBoard = chessBoard.clone();
            clonedBoard.movePiece(p, positionToCheck);

            //if king is safe
            if (!ChessUtils.isKingInDanger(currentPiece.getPieceColor(), clonedBoard)) {
                safeMoveList.add(positionToCheck); // add to list
            }
        }
        return safeMoveList;
    }

    /**
     * Moves a piece on the board. Adds the removed piece to the list of the removed piece types.
     *
     * @param p0 source position on the board.
     * @param p1 destination position on the board.
     */
    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        ChessBoard boardBeforeMove = chessBoard.clone(); // clones the board
        Piece removedPiece = chessBoard.movePiece(p0, p1); // gets the removed piece
        long turnTime = timer.changeTurn(chessBoard.getPiece(p1).getPieceColor()); // gets the time of the move in milliseconds
        pieceMoves.addMove(boardBeforeMove.clone(), turnTime, chessBoard.getPiece(p1).getPieceColor()); // adds the move to the move history

        if (removedPiece != null) { // if a piece was removed
            chessBoard.addRemovedPiece(removedPiece.getPieceType(), removedPiece.getPieceColor());
            // add the removed piece to the list of the removed pieces
        }
    }

    /**
     * Gets the status of the King for a given color
     *
     * @param color the requested king color.
     * @return KING_THREATEN if the King is in danger, or KING_SAFE if not
     */
    @Override
    public ChessKingState getKingState(ChessColor color) {
        boolean isKingInDanger = ChessUtils.isKingInDanger(color, chessBoard);
        return (isKingInDanger ? ChessKingState.KING_THREATEN : ChessKingState.KING_SAFE);
    }

    /**
     * Get a list of the removed piece types for display
     *
     * @param color color of the removed pieces
     * @return a list containing all of the removed piece types for the color given
     */
    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        return chessBoard.getRemovedPieces(color);
    }

    /**
     * Goes back one move in time
     *
     * @return a boolean to indicate if a move has been removed, or not if it is the first move on the board
     */
    @Override
    public boolean undoLastMove() {
        boolean hasMoveBeenReverted = false;
        Move lastMove = pieceMoves.getLastMove(); // gets the last Move object added to the list

        if (pieceMoves.getNumberOfTurns() > 1) { // if it's NOT the first turn

            this.timer.decreaseTime(lastMove.playerColor, lastMove.getTurnTime()); // remove time from the player
            this.chessBoard = lastMove.getSavedBoard(); // get the board from the previous move
            pieceMoves.removeLastMove(); // remove the move from the list now that it's back in the game
            hasMoveBeenReverted = true;
        }
        return hasMoveBeenReverted;
    }

    /**
     * Gets the current time of the player
     *
     * @param color     The color of the player from whom we want to get the current duration.
     * @param isPlaying Indicates if the player color is the one currently playing.
     * @return time of the player's turn(s) in milliseconds
     */
    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return timer.getPlayerTime(color, isPlaying);
    }
}
