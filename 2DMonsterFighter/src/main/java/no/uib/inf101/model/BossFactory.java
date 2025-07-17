package no.uib.inf101.model;

import no.uib.inf101.model.entities.Monster;

/**
 * A factory class for creating boss monsters.
 * Each boss created is stronger than the previous one, with increased health and attack power.
 */
public class BossFactory {
    /** Counter to track the number of bosses created, used to scale boss difficulty */
    static int bossCounter = 0;

    /**
     * Creates a new boss monster at the center of the map.
     * The boss's stats scale with the number of bosses created.
     * 
     * @param mapwidth The width of the map
     * @param mapheight The height of the map
     * @return A new boss monster with scaled stats
     */
    public static Monster spawnBoss(int mapwidth, int mapheight){
        bossCounter++;
        Monster boss = new Monster(mapwidth/2, mapheight/2);
        boss.setAttackPower(10*bossCounter);
        boss.setEntitySize(150);
        boss.setMaxHealth(1000*bossCounter);
        boss.setHealth(boss.getMaxhealth());
        //center the boss
        boss.move(-boss.getEntitySize()/2, -boss.getEntitySize()/2);
        return boss;
    }
}
