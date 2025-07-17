package no.uib.inf101.view;

import no.uib.inf101.model.entities.Player;

/**
 * Represents a health bar for the player character.
 * Calculates and stores the dimensions for both the green (current health) and red (missing health) portions.
 */
public class PlayerHealthBar {
    /** Length of the green portion representing current health */
    public double greenLength;
    /** Length of the red portion representing missing health */
    public int redLength;
    /** Starting position of the red portion */
    public int redStart;
    /** Starting position of the green portion */
    public int greenStart = 0;
    /** Total length of the health bar */
    public double maxHealthBarLength = 300;

    /**
     * Creates a new health bar for the specified player.
     * Calculates the lengths of the green and red portions based on the player's current and maximum health.
     * 
     * @param player The player whose health is being displayed
     */
    public PlayerHealthBar(Player player){
        greenLength = (maxHealthBarLength/player.getMaxhealth())*player.getHealth();
        redLength = (int) (maxHealthBarLength - greenLength);
        redStart = (int) greenLength;
    }
}
