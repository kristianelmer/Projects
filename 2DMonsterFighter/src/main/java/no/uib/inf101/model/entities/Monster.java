package no.uib.inf101.model.entities;



/**
 * A monster entity that can move and attack the player.
 */
public class Monster extends Entity {

    /**
     * Creates a new monster at the specified coordinates.
     * 
     * @param x The x-coordinate of the monster's position
     * @param y The y-coordinate of the monster's position
     */
    public Monster(int x, int y) {
        super(x, y);
        setSpeed(2);
        setAttackPower(1);
    }
}
