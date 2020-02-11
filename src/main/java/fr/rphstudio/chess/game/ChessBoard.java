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

            this.board[IChess.BOARD_POS_X_QUEENSIDE_ROOK][line] = new Piece(pieceColor, IChess.ChessType.TYP_ROOK);
            this.board[IChess.BOARD_POS_X_QUEENSIDE_KNIGHT][line] = new Piece(pieceColor, IChess.ChessType.TYP_KNIGHT);
            this.board[IChess.BOARD_POS_X_QUEENSIDE_BISHOP][line] = new Piece(pieceColor, IChess.ChessType.TYP_BISHOP);
            this.board[IChess.BOARD_POS_X_QUEEN][line] = new Piece(pieceColor, IChess.ChessType.TYP_QUEEN);
            this.board[IChess.BOARD_POS_X_KING][line] = new Piece(pieceColor, IChess.ChessType.TYP_KING);
            this.board[IChess.BOARD_POS_X_KINGSIDE_BISHOP][line] = new Piece(pieceColor, IChess.ChessType.TYP_BISHOP);
            this.board[IChess.BOARD_POS_X_KINGSIDE_KNIGHT][line] = new Piece(pieceColor, IChess.ChessType.TYP_KNIGHT);
            this.board[IChess.BOARD_POS_X_KINGSIDE_ROOK][line] = new Piece(pieceColor, IChess.ChessType.TYP_ROOK);
        }


        for (int pawnX = 0; pawnX < IChess.BOARD_WIDTH; pawnX++) {
            this.board[pawnX][IChess.BOARD_POS_Y_BLACK_PAWNS] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_PAWN);
            this.board[pawnX][IChess.BOARD_POS_Y_WHITE_PAWNS] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_PAWN);
        }
    }

    public Piece getPiece(IChess.ChessPosition pos) {
        return board[pos.x][pos.y];
    }

    public Piece removePiece(IChess.ChessPosition pos) {
        Piece removedPiece = this.board[pos.x][pos.y];
        this.board[pos.x][pos.y] = null;
        return removedPiece;
    }

    public void addPiece(IChess.ChessPosition pos, Piece chessPiece) {
        this.board[pos.x][pos.y] = chessPiece;
        return;
    }

    public Piece movePiece(IChess.ChessPosition pos0, IChess.ChessPosition pos1) {
        Piece pieceToMove = this.removePiece(pos0);
        Piece removedPiece = this.removePiece(pos1);

        this.addPiece(pos1, pieceToMove);
        return removedPiece;
    }


}
