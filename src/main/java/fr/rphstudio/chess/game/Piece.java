package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.pieces.*;
import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.List;

public class Piece  {

    private IChess.ChessColor pieceColor;
    private IChess.ChessType pieceType;
    private IMove pieceMove;

    public Piece(IChess.ChessColor pieceColor, IChess.ChessType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;

        IMove moveType;
        switch (this.pieceType){
            case TYP_KNIGHT:
                moveType = new Knight();
                break;
            case TYP_PAWN:
                moveType = new Pawn();
                break;
            case TYP_BISHOP :
                moveType = new Bishop();
                break;
            case TYP_ROOK :
                moveType = new Rook();
                break;
            case TYP_KING :
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

    public List<IChess.ChessPosition> getMoves(IChess.ChessPosition p, ChessBoard chessBoard){

       return pieceMove.getPossibleMoves(p, chessBoard);
    }

    public static IChess.ChessPosition checkMoves(int x, int y, ChessBoard board) {
        IChess.ChessPosition dest = new IChess.ChessPosition(x, y);
        if ((x >= 0 && x < 8 && y >= 0 && y < 8) && board.getPiece(dest) == null) {
            return dest;
        }
        return null;
    }

}