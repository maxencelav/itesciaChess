package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class TimeManager {
    long timeWhite = 0;
    long timeBlack = 0;
    long startTime = System.currentTimeMillis();

    /**
     * Returns time of current turn
     *
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
     *
     * @param color     Player color
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

        // if it's the player's turn
        if (isPlaying) {
            time += getCurrentTime(); // add the current move/round time to the returned time
        }

        return time;
    }

    /**
     * Decreases time for a given player
     *
     * @param color        Player color
     * @param timeToRemove Time in milliseconds to remove
     */
    public void decreaseTime(IChess.ChessColor color, long timeToRemove) {

        switch (color) { // Removes time from the corresponding player counter
            case CLR_WHITE:
                timeWhite -= timeToRemove;
                break;
            case CLR_BLACK:
                timeBlack -= timeToRemove;
                break;
        }

    }

    /**
     * Adds turn time to the corresponding player and starts a new timer for the new turn
     *
     * @param color Color of the player who's turn it is
     * @return time of the turn
     */
    public long changeTurn(IChess.ChessColor color) {
        long turnTime = getCurrentTime();
        switch (color) { // adds the turn time to the corresponding player counter
            case CLR_WHITE:
                timeWhite += turnTime;
                break;
            case CLR_BLACK:
                timeBlack += turnTime;
                break;
        }

        startNewTimer(); // start a new turn/move
        return turnTime;
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



