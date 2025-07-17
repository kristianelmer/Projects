package no.uib.inf101.model;

/**
 * Represents the possible states of the game.
 * Used to control game flow and screen transitions.
 */
public enum Gamestate {
    /** The initial welcome screen shown when the game starts */
    WELCOMESCREEN,
    /** The game is actively being played */
    ACTIVE,
    /** The game has ended in defeat */
    GAMEOVER,
    /** The game has ended in victory */
    WINNER,
    /** The game is being quit */
    QUIT,
    /** The game is paused */
    PAUSE,
}
