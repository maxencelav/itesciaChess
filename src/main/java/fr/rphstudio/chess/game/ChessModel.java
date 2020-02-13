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
        chessBoard = new ChessBoard();
        timer = new TimeManager();
        pieceMoves = new PieceMoves(chessBoard, 0, ChessColor.CLR_WHITE);
    }

    /**
     * Instance unique pré-initialisée
     */
    private static ChessModel INSTANCE = new ChessModel();


    public static IChess getInstance() {
        return INSTANCE;
    }

    @Override
    public void reinit() {
        this.chessBoard = new ChessBoard();
        this.timer.resetTimer();
        this.pieceMoves = new PieceMoves(chessBoard, 0, ChessColor.CLR_WHITE);
    }

    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {

        Piece currentPiece = chessBoard.getPiece(p);
        if (currentPiece != null) {
            return currentPiece.getPieceType();
        } else {
            throw new EmptyCellException();
        }
    }

    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {

        Piece currentPiece = chessBoard.getPiece(p);
        if (currentPiece != null) {
            return currentPiece.getPieceColor();
        } else {
            throw new EmptyCellException();
        }
    }

    @Override
    public int getNbRemainingPieces(ChessColor color) {

        int colorCounter = 0;

        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                try {
                    if (chessBoard.getPiece(new ChessPosition(x, y)).getPieceColor() == color) {
                        colorCounter++;
                    }
                } catch (NullPointerException e) {
                }

            }
        }
        return colorCounter;
    }

    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        //rendre robuste (check null par try catch

        Piece currentPiece = chessBoard.getPiece(p);

        List<ChessPosition> moveList = chessBoard.getPiece(p).getMoves(p, chessBoard);
        List<ChessPosition> safeMoveList = new ArrayList<>();

        // get all moves and only return ones that wont kill the king
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

    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        ChessBoard boardBeforeMove = chessBoard.clone();
        Piece removedPiece = chessBoard.movePiece(p0, p1);
        long turnTime = timer.changeTurn(chessBoard.getPiece(p1).getPieceColor());
        pieceMoves.addMove(boardBeforeMove.clone(), turnTime, chessBoard.getPiece(p1).getPieceColor());

        if (removedPiece != null) { // if a piece was removed
            chessBoard.addRemovedPiece(removedPiece.getPieceType(), removedPiece.getPieceColor());
        }
    }

    @Override
    public ChessKingState getKingState(ChessColor color) {
        boolean isKingInDanger = ChessUtils.isKingInDanger(color, chessBoard);
        return (isKingInDanger ? ChessKingState.KING_THREATEN : ChessKingState.KING_SAFE);
    }

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        return chessBoard.getRemovedPieces(color);
    }

    @Override
    public boolean undoLastMove() {
        boolean aaa = false;
        Move lastMove = pieceMoves.getLastMove();

        //if (lastMove != null) {
        if (pieceMoves.getNumberOfTurns() > 1) {

            this.timer.decreaseTime(lastMove.playerColor,lastMove.getTurnTime());
            this.chessBoard = lastMove.getSavedBoard();
            pieceMoves.removeLastMove();
            aaa = true;
        }
        return aaa; // a changer pour que si jamais lastMove = null ça return false
    }

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return timer.getPlayerTime(color, isPlaying);
    }
}
