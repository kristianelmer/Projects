package no.uib.inf101.model.powerups;

import java.io.IOException;

import javax.imageio.ImageIO;

import no.uib.inf101.model.entities.Player;

/**
 * A power-up that increases the width of the player's attack area.
 */
public class AttackWidthBoost extends PowerUp {

    /**
     * Creates a new attack width boost power-up at the specified position.
     * 
     * @param x The x-coordinate of the power-up's position
     * @param y The y-coordinate of the power-up's position
     * @param player The player that can collect this power-up
     * @param isTest Whether this is for testing (prevents image loading)
     */
    public AttackWidthBoost(int x, int y, Player player, boolean isTest) {
        super(x, y, player);
        if (!isTest) {
            try {
                powerUpImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/swipe.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Activates the power-up, increasing the player's attack range width.
     * Increases the player's attack range width by 50 units while maintaining the current length.
     */
    @Override
    public void activate(){
        player.setAttackRange(player.getAttackRangeWidth()+50, player.getAttackRangeLength());
    }

}
