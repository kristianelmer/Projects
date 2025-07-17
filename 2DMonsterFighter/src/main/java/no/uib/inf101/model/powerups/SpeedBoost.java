package no.uib.inf101.model.powerups;

import java.io.IOException;

import javax.imageio.ImageIO;

import no.uib.inf101.model.entities.Player;

/**
 * A power-up that increases the player's movement speed.
 * When activated, it increases the player's speed by 4 units.
 */
public class SpeedBoost extends PowerUp {
    /**
     * Creates a new speed boost power-up at the specified position.
     * 
     * @param x The x-coordinate of the power-up's position
     * @param y The y-coordinate of the power-up's position
     * @param player The player that can collect this power-up
     * @param isTest Whether this is for testing (prevents image loading)
     */
    public SpeedBoost(int x, int y, Player player, boolean isTest) {
        super(x, y, player);
        if (!isTest) {
            try {
                powerUpImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/shoe.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Activates the power-up, increasing the player's movement speed.
     * Increases the player's speed by 4 units.
     */
    @Override
    public void activate(){
        player.setSpeed(player.getSpeed()+4);
    }
}
