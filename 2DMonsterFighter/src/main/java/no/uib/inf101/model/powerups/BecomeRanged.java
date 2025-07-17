package no.uib.inf101.model.powerups;

import java.io.IOException;

import javax.imageio.ImageIO;

import no.uib.inf101.model.GameModel;
import no.uib.inf101.model.entities.Player;

/**
 * A power-up that gives the player ranged attack capabilities.
 * When activated, it changes the player's attack range and state.
 */
public class BecomeRanged extends PowerUp {

    /**
     * Creates a new BecomeRanged power-up at the specified coordinates.
     * 
     * @param x The x-coordinate of the power-up's position
     * @param y The y-coordinate of the power-up's position
     * @param player The player that can collect this power-up
     * @param isTest Whether this is for testing (prevents image loading)
     */
    public BecomeRanged(int x, int y, Player player, boolean isTest) {
        super(x, y, player);
        if (!isTest) {
            try {
                powerUpImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/magicstaff.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Activates the power-up, giving the player ranged attack capabilities.
     * Sets the player's attack range to cover the entire map and enables ranged attacks.
     */
    @Override
    public void activate(){
        player.setAttackRange(player.getAttackRangeWidth(), Math.max(GameModel.mapheight, GameModel.mapwidth));
        player.setRanged(true);
    }

}
