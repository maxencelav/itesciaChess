package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.pieces.*;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.List;

/**
 * Class for the creation of chess pieces
 */
public class Piece {

    private IChess.ChessColor pieceColor;
    private IChess.ChessType pieceType;
    private IMove pieceMove;
    private int numberOfTurns;

    /**
     * Creates a Piece
     * @param pieceColor Color of the Piece
     * @param pieceType Type of the Piece
     */
    public Piece(IChess.ChessColor pieceColor, IChess.ChessType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
        this.numberOfTurns = 0;

        IMove moveType;
        // Switch to create the moveType based off the PieceType
        switch (this.pieceType) {
            case TYP_KNIGHT:
                moveType = new Knight();
                break;
            case TYP_PAWN:
                moveType = new Pawn();
                break;
            case TYP_BISHOP:
                moveType = new Bishop();
                break;
            case TYP_ROOK:
                moveType = new Rook();
                break;
            case TYP_KING:
                moveType = new King();
                break;
            default: // TYP_QUEEN
                moveType = new Queen();
                break;
        }
        this.pieceMove = moveType;

    }

    /**
     * Gets the color of the Piece
     * @return pieceColor of the Piece
     */
    public IChess.ChessColor getPieceColor() {
        return pieceColor;
    }

    /**
     * Gets the type of the Piece
     * @return pieceType of the Piece
     */
    public IChess.ChessType getPieceType() {
        return pieceType;
    }

    /**
     * Gets the number of turns/moves done by the Piece
     * @return numberOfTurns of the Piece
     */
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    /**
     * Gets the list of all the possible moves for a Piece at a given position
     * @param p Position of the Piece
     * @param chessBoard Board where the Piece is in
     * @return List containing all the ChessPosition the Piece can end at
     */
    public List<IChess.ChessPosition> getMoves(IChess.ChessPosition p, ChessBoard chessBoard) {
        return pieceMove.getPossibleMoves(p, chessBoard);
    }

    /**
     * Increases the number of turns a Piece has done
     */
    public void increaseNbTurns() {
        numberOfTurns++;
    }

    /**
     * Checks the number of moves of the Piece and returns true if it already has moved
     * @return true if superior to 0, false if not
     */
    public boolean hasMoved() {
        return (this.numberOfTurns > 0);
    }

    /**
     * Sets the number of turns of a Piece to a given number
     * @param numberOfTurns
     */
    private void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    /**
     * Copies a Piece
     * @return Clone of the original piece
     */
    public Piece clone() {
        Piece clonedPiece = new Piece(this.pieceColor, this.pieceType);
        clonedPiece.setNumberOfTurns(this.numberOfTurns);
        return clonedPiece;
    }

}