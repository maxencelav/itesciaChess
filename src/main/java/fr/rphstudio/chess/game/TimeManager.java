

package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import org.lwjgl.Sys;

public class TimeManager {
    long timeWhite = 0;
    long timeBlack = 0;
    long startTime = System.currentTimeMillis();

    public long getCurrentTime() {
        return System.currentTimeMillis() - startTime;
    }

    public void startNewTimer() {
        startTime = System.currentTimeMillis();
    }

    public long getPlayerTime(IChess.ChessColor color, boolean isPlaying) {
        long time = 0;

        switch (color) {
            case CLR_WHITE:
                time = timeWhite;
                break;
            case CLR_BLACK:
                time = timeBlack;
                break;
        }

        if (isPlaying) {
            time += getCurrentTime();
        }

        return time;
    }

    public void changeTurn(IChess.ChessColor color) {
        switch (color) {
            case CLR_WHITE:
                timeWhite += getCurrentTime();
                break;
            case CLR_BLACK:
                timeBlack += getCurrentTime();
                break;
        }

        startNewTimer();
    }

    public void resetTimer() {
        timeWhite = 0;
        timeBlack = 0;
        startTime = System.currentTimeMillis();
    }
}



