package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class ChessBoard {

    private Piece[][] board;

    public ChessBoard() {

        this.board = new Piece[IChess.BOARD_WIDTH][IChess.BOARD_HEIGHT];
        this.board[0][0] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KING);
    }

    public Piece getPiece(IChess.ChessPosition pos) {
        return board[pos.x][pos.y];
    }
}
