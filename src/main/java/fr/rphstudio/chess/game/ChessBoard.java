package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class ChessBoard {

    private Piece[][] board;

    public ChessBoard() {

        this.board = new Piece[IChess.BOARD_WIDTH][IChess.BOARD_HEIGHT];
        for (int line = 0; line <= 7; line += 7) {
            this.board[0][line] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_ROOK);
            this.board[1][line] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KNIGHT);
            this.board[2][line] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_BISHOP);
            this.board[3][line] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_QUEEN);
            this.board[4][line] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KING);
            this.board[5][line] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_BISHOP);
            this.board[6][line] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KNIGHT);
            this.board[7][line] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_ROOK);
        }


        for (int pawnX = 0; pawnX < IChess.BOARD_WIDTH; pawnX++) {
            this.board[pawnX][1] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_PAWN);
            this.board[pawnX][6] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_PAWN);
        }
    }

    public Piece getPiece(IChess.ChessPosition pos) {
        return board[pos.x][pos.y];
    }
}
