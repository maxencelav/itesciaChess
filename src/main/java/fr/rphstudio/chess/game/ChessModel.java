package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.List;

public class ChessModel implements IChess {

    private ChessBoard chessBoard;

    private ChessModel() {

        chessBoard = new ChessBoard();
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
        //rendre robuste (check null par try catch)
        return chessBoard.getPiece(p).getMoves(p, chessBoard);

    }

    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        chessBoard.movePiece(p0, p1);
    }

    @Override
    public ChessKingState getKingState(ChessColor color) {
        boolean isKingInDanger = false;

        for (int x = 0; x < IChess.BOARD_WIDTH; x++) {
            for (int y = 0; y < IChess.BOARD_HEIGHT; y++) {
                IChess.ChessPosition pos = ChessUtils.checkPositionOnBoard(x, y, chessBoard);
                if (pos != null) { // if pos exists
                    Piece pieceAtPos = chessBoard.getPiece(pos); // get piece
                    if (pieceAtPos != null) { // if piece exists
                        if (pieceAtPos.getPieceType() == ChessType.TYP_KING && pieceAtPos.getPieceColor() == color) {
                            isKingInDanger = ChessUtils.canGetEaten(pos, chessBoard);

                        }
                    }
                }

            }
        }

        return (isKingInDanger ? ChessKingState.KING_THREATEN : ChessKingState.KING_SAFE);
    }

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {

        return new ArrayList<ChessType>();
    }

    @Override
    public boolean undoLastMove() {
        return false;
    }

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {

        System.currentTimeMillis();

        //Stocker heure début dans long avec time mili
        //qd qqun joue, noter temps -> faire la diff pour avoir le temps du tour
        //stocker dans une jauge/var correspondant au temps de jeu

        // 3 infos : h dernier coup, jauge de tous les coups, calc depuis dernier coup
        return 0;
    }
}
