package no.uib.inf101.model.powerups;

import java.io.IOException;

import javax.imageio.ImageIO;

import no.uib.inf101.model.entities.Player;

/**
 * A power-up that increases the player's attack range length.
 * When activated, it increases the player's attack range length by 50 units.
 */
public class AttackRangeBoost extends PowerUp {
    /**
     * Creates a new AttackRangeBoost power-up at the specified coordinates.
     * 
     * @param x The x-coordinate of the power-up's position
     * @param y The y-coordinate of the power-up's position
     * @param player The player that can collect this power-up
     * @param isTest Whether this is for testing (prevents image loading)
     */
    public AttackRangeBoost(int x, int y, Player player, boolean isTest) {
        super(x, y, player);
        if (!isTest) {
            try {
                powerUpImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/bow.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Activates the power-up, increasing the player's attack range length.
     * Increases the player's attack range length by 50 units while maintaining the current width.
     */
    @Override
    public void activate(){
        player.setAttackRange(player.getAttackRangeWidth(), player.getAttackRangeLength()+50);
    }
}
