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


    private ChessModel() {

        chessBoard = new ChessBoard();
        timer = new TimeManager();
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
                //System.out.println(x + "," + y);
                try {
                    //System.out.println(chessBoard.getPiece(new ChessPosition(x, y)).getPieceColor());
                    if (chessBoard.getPiece(new ChessPosition(x, y)).getPieceColor() == color) {
                        colorCounter++;
                    }
                } catch (NullPointerException e) {
                    //System.out.println(e + "No color found");
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
        Piece removedPiece = chessBoard.movePiece(p0, p1);
        if (removedPiece != null) { // if a piece was removed
            chessBoard.addRemovedPiece(removedPiece.getPieceType(), removedPiece.getPieceColor());

        }
        timer.changeTurn(chessBoard.getPiece(p1).getPieceColor());

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
        return false;
    }

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {

        //Stocker heure début dans long avec time mili
        //qd qqun joue, noter temps -> faire la diff pour avoir le temps du tour
        //stocker dans une jauge/var correspondant au temps de jeu

        // 3 infos : h dernier coup, jauge de tous les coups, calc depuis dernier coup

        return timer.getPlayerTime(color, isPlaying);
    }
}
