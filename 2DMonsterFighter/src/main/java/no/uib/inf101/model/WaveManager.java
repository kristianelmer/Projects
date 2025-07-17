package no.uib.inf101.model;

import java.util.ArrayList;

import no.uib.inf101.model.entities.Monster;

/**
 * Manages the spawning and scaling of monster waves in the game.
 * Handles both regular monster waves and boss waves, with increasing difficulty.
 */
public class WaveManager {
    /** Factory for creating random monsters */
    private RandomMonsterFactory monsterFactory;
    /** Current wave number */
    private int waveNumber;
    /** Number of monsters to spawn in the next wave */
    private int monstersPerWave;
    /** Reference to the game model */
    private GameModel model;
    /** Flag indicating if a boss is currently active */
    private boolean isBossActive = false;

    /**
     * Creates a new wave manager for the specified game model.
     * 
     * @param model The game model
     */
    public WaveManager(GameModel model) {
        monsterFactory = new RandomMonsterFactory();
        waveNumber = 0;
        monstersPerWave = 10;
        this.model = model;
    }

    /**
     * Spawns a new wave of monsters or a boss.
     * Every 5th wave is a boss wave.
     * 
     * @param mapWidth The width of the map
     * @param mapHeight The height of the map
     * @return A list of monsters for the new wave
     */
    public ArrayList<Monster> spawnMonsters(int mapWidth, int mapHeight) {
        waveNumber++;
        ArrayList<Monster> monsters = new ArrayList<>();
        if (waveNumber%5 == 0){
            isBossActive = true;
            Monster boss = BossFactory.spawnBoss(mapWidth, mapHeight);
            while (boss.getBounds().intersects(model.getPlayer().getAOE(100))){
                boss.move(0, -1);
            }
            monsters.add(boss);
            return monsters;
        }
        isBossActive = false;
        for (int i = 0; i < monstersPerWave; i++) {
            Monster monster = monsterFactory.createRandomMonster(mapWidth, mapHeight, model.getPlayer());
            monsters.add(monster);
        }
        monstersPerWave++; 
        setMonsterStrength(waveNumber-1, monsters);
        return monsters;
    }

    /**
     * Gets the current wave number.
     * 
     * @return The current wave number
     */
    public int getWaveNumber() {
        return waveNumber;
    }

    /**
     * Sets the strength of monsters based on the wave number.
     * Scales attack power, speed, and health with increasing waves.
     * 
     * @param strength The wave number to scale from
     * @param monsters The list of monsters to scale
     */
    private void setMonsterStrength(int strength, ArrayList<Monster> monsters){
        for (int i = 0; i < monsters.size(); i++){
            Monster monster = monsters.get(i);
            monster.setAttackPower((int) Math.floor(1+0.25*strength));
            monster.setSpeed((int) Math.floor(2+0.125*strength));
            monster.setMaxHealth(100+25*strength);
            monster.setHealth(monster.getMaxhealth());
        }
    }

    /**
     * Checks if a boss is currently active.
     * 
     * @return true if a boss is active, false otherwise
     */
    public boolean getIsBossActive(){
        return isBossActive;
    }
}
