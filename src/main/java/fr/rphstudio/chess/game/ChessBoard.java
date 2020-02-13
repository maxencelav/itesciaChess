package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.pieces.Queen;
import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    private Piece[][] board;
    private List<IChess.ChessType> removedWhitePieces = new ArrayList<IChess.ChessType>();
    private List<IChess.ChessType> removedBlackPieces = new ArrayList<IChess.ChessType>();
    private PieceMoves moveList;

    /**
     * Constructor for the board with the basic piece position for a game of chess
     */
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

    /**
     * Creates an identical board (clone) based off the one given as a parameter
     * @param originalBoard Board to "clone"
     */

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

        for (IChess.ChessType piece : originalBoard.removedBlackPieces){
            this.removedBlackPieces.add(piece);
        }
        for (IChess.ChessType piece : originalBoard.removedWhitePieces){
            this.removedWhitePieces.add(piece);
        }

        //this.moveList = originalBoard.moveList;
    }

    /**
     *
     * @param pos ChessPosition of the piece you want
     * @return the Piece at the given pos
     */

    public Piece getPiece(IChess.ChessPosition pos) {
        try {
            return board[pos.x][pos.y];

        } catch (Exception e) {
            //System.out.println(e);
            return null;
        }
    }

    /**
     * Removes a piece from the board
     * @param pos Position of the piece you want to remove
     * @return Removed piece
     */

    public Piece removePiece(IChess.ChessPosition pos) {
        Piece removedPiece = this.board[pos.x][pos.y];
        this.board[pos.x][pos.y] = null;
        return removedPiece;
    }

    /**
     * Adds a piece to the board
     * @param pos Position of the piece you want to add
     * @param chessPiece Piece to add
     */

    public void addPiece(IChess.ChessPosition pos, Piece chessPiece) {
        this.board[pos.x][pos.y] = chessPiece;
    }

    /**
     * Moves the piece and makes special movements (roque, transformation of the pawn into a queen)
     * @param pos0 Starting position
     * @param pos1 End position
     * @return Removed piece
     */
    public Piece movePiece(IChess.ChessPosition pos0, IChess.ChessPosition pos1) {
        Piece pieceToMove = this.removePiece(pos0);
        Piece removedPiece = this.removePiece(pos1);

        //transformation of the pawn into a queen

        if (pieceToMove.getPieceType() == IChess.ChessType.TYP_PAWN && pieceToMove.getPieceColor() == IChess.ChessColor.CLR_WHITE && pos1.y == IChess.BOARD_POS_Y_BLACK_PIECES) {
            this.addPiece(pos1, new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_QUEEN));
        } else if (pieceToMove.getPieceType() == IChess.ChessType.TYP_PAWN && pieceToMove.getPieceColor() == IChess.ChessColor.CLR_BLACK && pos1.y == IChess.BOARD_POS_Y_WHITE_PIECES) {
            this.addPiece(pos1, new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_QUEEN));

            //the roque white side

        } else if (pieceToMove.getPieceType() == IChess.ChessType.TYP_KING && pieceToMove.getNumberOfTurns() == 0 && pieceToMove.getPieceColor() == IChess.ChessColor.CLR_WHITE && pos1.y == IChess.BOARD_POS_Y_WHITE_PIECES) {
            this.addPiece(pos1, pieceToMove);


            if (pos1.x == 2) {
                Piece rookToMove = this.removePiece(new IChess.ChessPosition(0, IChess.BOARD_POS_Y_WHITE_PIECES));
                this.addPiece(new IChess.ChessPosition(3, IChess.BOARD_POS_Y_WHITE_PIECES), rookToMove);
            } else if (pos1.x == 6) {
                Piece rookToMove = this.removePiece(new IChess.ChessPosition(7, IChess.BOARD_POS_Y_WHITE_PIECES));
                this.addPiece(new IChess.ChessPosition(5, IChess.BOARD_POS_Y_WHITE_PIECES), rookToMove);
            }

            //the roque black side

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

    /**
     * Clones the board
     * @return the clone board
     */
    public ChessBoard clone() {
        return new ChessBoard(this);
    }

    /**
     * Get the types of the pieces that are gone from the board
     * @param color Color of the pieces removed
     * @return List of the types of pieces removed
     */
    public List<IChess.ChessType> getRemovedPieces(IChess.ChessColor color) {
        switch (color) {
            case CLR_WHITE:
                return removedWhitePieces;

            case CLR_BLACK:
                return removedBlackPieces;
        }

        return null;
    }

    /**
     * add pieces to the list of removed pieces 
     * @param type type of the pieces removed
     * @param color color of the pieces removed
     */
    public void addRemovedPiece(IChess.ChessType type, IChess.ChessColor color) {
        switch (color) {
            case CLR_WHITE:
                removedWhitePieces.add(type);
                break;
            case CLR_BLACK:
                removedBlackPieces.add(type);
            break;
        }
    }


}
