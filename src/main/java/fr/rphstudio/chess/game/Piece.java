package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.pieces.*;
import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Piece {

    private IChess.ChessColor pieceColor;
    private IChess.ChessType pieceType;
    private IMove pieceMove;
    private int numberOfTurns;

    public Piece(IChess.ChessColor pieceColor, IChess.ChessType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
        this.numberOfTurns = 0;

        IMove moveType;
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
            default:
                moveType = new Queen();
                break;
        }
        this.pieceMove = moveType;

    }

    public IChess.ChessColor getPieceColor() {
        return pieceColor;
    }

    public IChess.ChessType getPieceType() {
        return pieceType;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public List<IChess.ChessPosition> getMoves(IChess.ChessPosition p, ChessBoard chessBoard) {
        return pieceMove.getPossibleMoves(p, chessBoard);
    }

    public static IChess.ChessPosition checkPositionOnBoard(int x, int y, ChessBoard board) {
        IChess.ChessPosition dest = new IChess.ChessPosition(x, y);
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            return dest;
        }
        return null;
    }

    public static List<IChess.ChessPosition> getMovesDirection(int dX, int dY, IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> possibleMoves = new ArrayList<IChess.ChessPosition>();

        for (int dist = 1; dist <= 7; dist++) {
            IChess.ChessPosition pos = Piece.checkPositionOnBoard(p.x + (dist * dX), p.y + (dist * dY), board);


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

    public static List<IChess.ChessPosition> getMoveDiagonal(IChess.ChessPosition p, ChessBoard board){
        List<IChess.ChessPosition> tempList = new ArrayList<>();
        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        tempList = Piece.getMovesDirection(1, 1, p, board);
        possibleMoves.addAll(tempList);

        tempList = Piece.getMovesDirection(1, -1, p, board);
        possibleMoves.addAll(tempList);

        tempList = Piece.getMovesDirection(-1, 1, p, board);
        possibleMoves.addAll(tempList);

        tempList = Piece.getMovesDirection(-1, -1, p, board);
        possibleMoves.addAll(tempList);


        return possibleMoves;

    }

    public static List<IChess.ChessPosition> getMoveOrthogonal(IChess.ChessPosition p, ChessBoard board){
        List<IChess.ChessPosition> tempList = new ArrayList<>();
        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        tempList = Piece.getMovesDirection(1, 0, p, board);
        possibleMoves.addAll(tempList);

        tempList = Piece.getMovesDirection(-1, 0, p, board);
        possibleMoves.addAll(tempList);

        tempList = Piece.getMovesDirection(0, 1, p, board);
        possibleMoves.addAll(tempList);

        tempList = Piece.getMovesDirection(0, -1, p, board);
        possibleMoves.addAll(tempList);


        return possibleMoves;

    }

    public void increaseNbTurns() {
        numberOfTurns++;
    }

    public boolean hasMoved() {
        return (this.numberOfTurns > 0);
    }

}