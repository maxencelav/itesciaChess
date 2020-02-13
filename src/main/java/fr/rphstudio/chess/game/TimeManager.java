package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import org.lwjgl.Sys;

public class TimeManager {
    long timeWhite = 0;
    long timeBlack = 0;
    long startTime = System.currentTimeMillis();

    /**
     * Returns time of current turn
     * @return Time in milliseconds
     */
    public long getCurrentTime() {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Starts a timer for the turn
     */
    public void startNewTimer() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Returns current player playtime
     * @param color Player color
     * @param isPlaying Is it the player's turn (to update the timer value)
     * @return
     */
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

    public void decreaseTime(IChess.ChessColor color, int timeToRemove){

    }

    /**
     * Adds turn time to the corresponding player and starts a new timer for the new turn
     * @param color Color of the player who's turn it is
     */
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

    /**
     * Resets the timer's values to 0
     */
    public void resetTimer() {
        timeWhite = 0;
        timeBlack = 0;
        startTime = System.currentTimeMillis();
    }


}



