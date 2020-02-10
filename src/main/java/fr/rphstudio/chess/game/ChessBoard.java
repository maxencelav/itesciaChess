package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class ChessBoard {

    private Piece[][] board;

    public ChessBoard() {

        this.board = new Piece[IChess.BOARD_WIDTH][IChess.BOARD_HEIGHT];
        for (int line = IChess.BOARD_POS_Y_BLACK_PIECES; line <= IChess.BOARD_POS_Y_WHITE_PIECES; line += IChess.BOARD_POS_Y_WHITE_PIECES) {
            IChess.ChessColor pieceColor;
            switch (line) {
                case IChess.BOARD_POS_Y_BLACK_PIECES:
                    pieceColor = IChess.ChessColor.CLR_BLACK;
                    break;
                default:
                    pieceColor = IChess.ChessColor.CLR_WHITE;
                    break;
            }

            this.board[IChess.BOARD_POS_X_QUEENSIDE_ROOK][line] = new Piece(pieceColor, IChess.ChessType.TYP_ROOK, moveType);
            this.board[IChess.BOARD_POS_X_QUEENSIDE_KNIGHT][line] = new Piece(pieceColor, IChess.ChessType.TYP_KNIGHT, moveType);
            this.board[IChess.BOARD_POS_X_QUEENSIDE_BISHOP][line] = new Piece(pieceColor, IChess.ChessType.TYP_BISHOP, moveType);
            this.board[IChess.BOARD_POS_X_QUEEN][line] = new Piece(pieceColor, IChess.ChessType.TYP_QUEEN, moveType);
            this.board[IChess.BOARD_POS_X_KING][line] = new Piece(pieceColor, IChess.ChessType.TYP_KING, moveType);
            this.board[IChess.BOARD_POS_X_KINGSIDE_BISHOP][line] = new Piece(pieceColor, IChess.ChessType.TYP_BISHOP, moveType);
            this.board[IChess.BOARD_POS_X_KINGSIDE_KNIGHT][line] = new Piece(pieceColor, IChess.ChessType.TYP_KNIGHT, moveType);
            this.board[IChess.BOARD_POS_X_KINGSIDE_ROOK][line] = new Piece(pieceColor, IChess.ChessType.TYP_ROOK, moveType);
        }


        for (int pawnX = 0; pawnX < IChess.BOARD_WIDTH; pawnX++) {
            this.board[pawnX][IChess.BOARD_POS_Y_BLACK_PAWNS] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_PAWN, moveType);
            this.board[pawnX][IChess.BOARD_POS_Y_WHITE_PAWNS] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_PAWN, moveType);
        }
    }

    public Piece getPiece(IChess.ChessPosition pos) {
        return board[pos.x][pos.y];
    }
}
