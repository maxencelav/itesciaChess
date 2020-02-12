package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.pieces.Queen;
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

    private ChessBoard(ChessBoard originalBoard) {
        this.board = new Piece[IChess.BOARD_WIDTH][IChess.BOARD_HEIGHT];
        for (int x = 0; x < IChess.BOARD_WIDTH; x++) {
            for (int y = 0; y < IChess.BOARD_HEIGHT; y++) {

                Piece pieceToClone = originalBoard.board[x][y];
                if (pieceToClone != null) {
                    this.board[x][y] = pieceToClone.clone();
                }
            }
        }
    }

    public Piece getPiece(IChess.ChessPosition pos) {
        try {
            return board[pos.x][pos.y];

        } catch (Exception e) {
            //System.out.println(e);
            return null;
        }
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

        if (pieceToMove.getPieceType() == IChess.ChessType.TYP_PAWN && pieceToMove.getPieceColor() == IChess.ChessColor.CLR_WHITE && pos1.y == IChess.BOARD_POS_Y_BLACK_PIECES) {
            this.addPiece(pos1, new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_QUEEN));
        } else if (pieceToMove.getPieceType() == IChess.ChessType.TYP_PAWN && pieceToMove.getPieceColor() == IChess.ChessColor.CLR_BLACK && pos1.y == IChess.BOARD_POS_Y_WHITE_PIECES) {
            this.addPiece(pos1, new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_QUEEN));
        } else if (pieceToMove.getPieceType() == IChess.ChessType.TYP_KING && pieceToMove.getNumberOfTurns() == 0 && pieceToMove.getPieceColor() == IChess.ChessColor.CLR_WHITE && pos1.y == IChess.BOARD_POS_Y_WHITE_PIECES) {
            this.addPiece(pos1, pieceToMove);

            if (pos1.x == 2) {
                Piece rookToMove = this.removePiece(new IChess.ChessPosition(0, IChess.BOARD_POS_Y_WHITE_PIECES));
                this.addPiece(new IChess.ChessPosition(3, IChess.BOARD_POS_Y_WHITE_PIECES), rookToMove);
            } else if (pos1.x == 6) {
                Piece rookToMove = this.removePiece(new IChess.ChessPosition(7, IChess.BOARD_POS_Y_WHITE_PIECES));
                this.addPiece(new IChess.ChessPosition(5, IChess.BOARD_POS_Y_WHITE_PIECES), rookToMove);
            }


        } else if (pieceToMove.getPieceType() == IChess.ChessType.TYP_KING && pieceToMove.getNumberOfTurns() == 0 && pieceToMove.getPieceColor() == IChess.ChessColor.CLR_BLACK && pos1.y == IChess.BOARD_POS_Y_BLACK_PIECES) {
            this.addPiece(pos1, pieceToMove);

            if (pos1.x == 2) {
                Piece rookToMove = this.removePiece(new IChess.ChessPosition(0, IChess.BOARD_POS_Y_BLACK_PIECES));
                this.addPiece(new IChess.ChessPosition(3, IChess.BOARD_POS_Y_BLACK_PIECES), rookToMove);
            } else if (pos1.x == 6) {
                Piece rookToMove = this.removePiece(new IChess.ChessPosition(7, IChess.BOARD_POS_Y_BLACK_PIECES));
                this.addPiece(new IChess.ChessPosition(5, IChess.BOARD_POS_Y_BLACK_PIECES), rookToMove);
            }


        } else {
            this.addPiece(pos1, pieceToMove);
        }
        pieceToMove.increaseNbTurns();


        return removedPiece;
    }

    public ChessBoard clone() {
        return new ChessBoard(this);
    }


}
