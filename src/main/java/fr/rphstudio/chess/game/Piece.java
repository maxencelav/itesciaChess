package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.pieces.Knight;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

public class Piece  {

    private IChess.ChessColor pieceColor;
    private IChess.ChessType pieceType;
    private IMove moveType = new Knight();

    public Piece(IChess.ChessColor pieceColor, IChess.ChessType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public IChess.ChessColor getPieceColor() {
        return pieceColor;
    }


    public IChess.ChessType getPieceType() {
        return pieceType;
    }

    public IMove getMoveType(){
       return this.moveType;
    }

}
