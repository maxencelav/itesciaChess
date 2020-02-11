package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.ChessBoard;
import fr.rphstudio.chess.game.Piece;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Knight implements IMove {

    public Knight(){
    }

    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p) {

        List<IChess.ChessPosition> possibleMoves = new ArrayList<>();

        int[] posX ={p.x+1,p.x+1,p.x+2,p.x+2,p.x-1,p.x-1,p.x-2,p.x-2};
        int[] posY ={p.y-2,p.y+2,p.y-1,p.y+1,p.y-2,p.y+2,p.y-1,p.y+1};

        for(int i=0;i<8;i++)
            if((posX[i]>=0 && posX[i] < 8 && posY[i] >= 0 && posY[i] < 8))
                possibleMoves.add(new IChess.ChessPosition(posX[i],posY[i]));

        return possibleMoves;
    }
}
