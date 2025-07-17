package no.uib.inf101.model.powerups;

import java.io.IOException;

import javax.imageio.ImageIO;

import no.uib.inf101.model.entities.Player;

/**
 * A power-up that increases the player's attack speed.
 * When activated, it increases the player's attack speed by 50%.
 */
public class AttackSpeedBoost extends PowerUp{

    /**
     * Creates a new AttackSpeedBoost power-up at the specified coordinates.
     * 
     * @param x The x-coordinate of the power-up's position
     * @param y The y-coordinate of the power-up's position
     * @param player The player that can collect this power-up
     * @param isTest Whether this is for testing (prevents image loading)
     */
    public AttackSpeedBoost(int x, int y, Player player, boolean isTest) {
        super(x, y, player);
        if (!isTest) {
            try {
                powerUpImage = ImageIO.read(getClass().getResourceAsStream("/no/uib/inf101/res/attackspeed.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Activates the power-up, increasing the player's attack speed.
     * Increases the player's attack speed by 50% (multiplies it by 1.5).
     */
    @Override
    public void activate(){
        player.setAttackSpeed(player.getAttackSpeed()*1.5);
    }
    


}
