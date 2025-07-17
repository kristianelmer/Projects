package no.uib.inf101.model.powerups;

import java.io.IOException;

import javax.imageio.ImageIO;

import no.uib.inf101.model.entities.Player;

/**
 * A power-up that increases the player's maximum health.
 * When activated, it increases the player's maximum health by 20 points.
 */
public class MaxHealthGain extends PowerUp {

    /**
     * Creates a new MaxHealthGain power-up at the specified coordinates.
     * 
     * @param x The x-coordinate of the power-up's position
     * @param y The y-coordinate of the power-up's position
     * @param player The player that can collect this power-up
     * @param isTest Whether this is for testing (prevents image loading)
     */
    public MaxHealthGain(int x, int y, Player player, boolean isTest) {
        super(x, y, player);
        if (!isTest) {
            try {
                powerUpImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/heart.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Activates the power-up, increasing the player's maximum health.
     * Increases the player's maximum health by 20 points.
     */
    @Override
    public void activate(){
        player.setMaxHealth(player.getMaxhealth()+20);
    }
}
