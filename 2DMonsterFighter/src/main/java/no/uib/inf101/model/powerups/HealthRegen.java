package no.uib.inf101.model.powerups;

import java.io.IOException;

import javax.imageio.ImageIO;

import no.uib.inf101.model.entities.Player;

/**
 * A power-up that restores the player's health to maximum.
 * When activated, it sets the player's current health to their maximum health.
 */
public class HealthRegen extends PowerUp {

    /**
     * Creates a new HealthRegen power-up at the specified coordinates.
     * 
     * @param x The x-coordinate of the power-up's position
     * @param y The y-coordinate of the power-up's position
     * @param player The player that can collect this power-up
     * @param isTest Whether this is for testing (prevents image loading)
     */
    public HealthRegen(int x, int y, Player player, boolean isTest) {
        super(x, y, player);
        if (!isTest) {
            try {
                powerUpImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/healheart.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Activates the power-up, restoring the player's health to maximum.
     * Sets the player's current health to their maximum health value.
     */
    @Override
    public void activate(){
        player.setHealth(player.getMaxhealth());
    }
}
