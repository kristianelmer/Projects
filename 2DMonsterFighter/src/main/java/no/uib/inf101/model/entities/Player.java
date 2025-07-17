package no.uib.inf101.model.entities;

import java.awt.Rectangle;

/**
 * Represents the player character in the game.
 * The player can move, attack, and collect power-ups.
 */
public class Player extends Entity {
    boolean isRanged;
    double attackSpeed;

    /**
     * Creates a new player at the specified coordinates.
     * 
     * @param x The x-coordinate of the player's position
     * @param y The y-coordinate of the player's position
     */
    public Player(int x, int y) {
        super(x, y);
        attackSpeed = 1;
        setAttackRange(100,100);
        attackPower = 50;
        setSpeed(10);
        isRanged = false;
    }

    /**
     * Gets the area of effect for the player's attack.
     * 
     * @param aoewidth The width of the area of effect
     * @return A rectangle representing the area of effect
     */
    public Rectangle getAOE(int aoewidth) {
        int x = getX();
        int y = getY();
        return new Rectangle(x - aoewidth, y - aoewidth, 
            aoewidth * 2 + getEntitySize(), aoewidth * 2 + getEntitySize());
    }

    /**
     * Sets whether the player has ranged attacks.
     * 
     * @param isRanged true if the player has ranged attacks, false otherwise
     */
    public void setRanged(boolean isRanged) {
        this.isRanged = isRanged;
    }

    /**
     * Checks if the player has ranged attacks.
     * 
     * @return true if the player has ranged attacks, false otherwise
     */
    public boolean getIsRanged() {
        return isRanged;
    }

    /**
     * Gets the player's attack speed.
     * 
     * @return The player's attack speed
     */
    public double getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Sets the player's attack speed.
     * 
     * @param attackSpeed The new attack speed
     */
    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
}
