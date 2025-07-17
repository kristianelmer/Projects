package no.uib.inf101.model;

import no.uib.inf101.model.entities.Monster;
import no.uib.inf101.model.entities.Player;

/**
 * A factory class that creates monsters at random positions on the map.
 * Ensures monsters don't spawn too close to the player.
 */
public class RandomMonsterFactory {
    /**
     * Creates a new random monster factory.
     */
    public RandomMonsterFactory() {
    }

    /**
     * Creates a monster at a random position on the map.
     * Ensures the monster doesn't spawn within the player's attack range.
     * 
     * @param mapWidth The width of the map
     * @param mapHeight The height of the map
     * @param player The player to avoid spawning near
     * @return A new monster at a random position
     */
    public Monster createRandomMonster(int mapWidth, int mapHeight, Player player) {
        int monsterX = getRandom(mapWidth);
        int monsterY = getRandom(mapHeight);
        Monster monster = new Monster(monsterX, monsterY);
        if (player.getAOE(100).intersects(monster.getBounds())) {
            return createRandomMonster(mapWidth, mapHeight, player);
        }
        else{
            return monster;
        }
    }

    /**
     * Gets a random number between 0 and the specified number.
     * 
     * @param num The upper bound for the random number
     * @return A random number between 0 and num
     */
    private int getRandom(int num) {
        return (int) (Math.random() * num);
    }
}
