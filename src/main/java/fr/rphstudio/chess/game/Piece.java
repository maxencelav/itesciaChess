package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

public class Piece  {

    private IChess.ChessColor pieceColor;
    private IChess.ChessType pieceType;
    private IMove moveType;

    public Piece(IChess.ChessColor pieceColor, IChess.ChessType pieceType, IMove moveType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
        this.moveType = moveType;
    }

    public IChess.ChessColor getPieceColor() {
        return pieceColor;
    }


    public IChess.ChessType getPieceType() {
        return pieceType;
    }

    public IMove getMoveType() {
        return moveType;
    }
}
