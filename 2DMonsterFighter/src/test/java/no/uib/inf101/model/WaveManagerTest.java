package no.uib.inf101.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import no.uib.inf101.model.entities.Monster;
import no.uib.inf101.controller.Controller;

public class WaveManagerTest {
    private WaveManager waveManager;
    private GameModel gameModel;
    private Controller mockController;

    @BeforeEach
    void setUp() {
        mockController = new Controller();
        gameModel = new GameModel(mockController, true);
        waveManager = new WaveManager(gameModel);
    }

    @Test
    void testInitialState() {
        assertEquals(0, waveManager.getWaveNumber());
        assertFalse(waveManager.getIsBossActive());
    }

    @Test
    void testNormalWaveSpawn() {
        ArrayList<Monster> monsters = waveManager.spawnMonsters(GameModel.mapwidth, GameModel.mapheight);
        
        assertEquals(1, waveManager.getWaveNumber());
        assertEquals(10, monsters.size());
        assertFalse(waveManager.getIsBossActive());
        
        // Check monster properties
        for (Monster monster : monsters) {
            assertTrue(monster.getX() >= 0 && monster.getX() <= GameModel.mapwidth);
            assertTrue(monster.getY() >= 0 && monster.getY() <= GameModel.mapheight);
            assertTrue(monster.getHealth() > 0);
            assertTrue(monster.getSpeed() >= 2);
            assertTrue(monster.getAttackPower() >= 1);
        }
    }

    @Test
    void testBossWaveSpawn() {
        // Spawn 4 normal waves first
        for (int i = 0; i < 4; i++) {
            waveManager.spawnMonsters(GameModel.mapwidth, GameModel.mapheight);
        }
        
        // Spawn boss wave
        ArrayList<Monster> monsters = waveManager.spawnMonsters(GameModel.mapwidth, GameModel.mapheight);
        
        assertEquals(5, waveManager.getWaveNumber());
        assertEquals(1, monsters.size()); // Only boss monster
        assertTrue(waveManager.getIsBossActive());
    }

    @Test
    void testMonsterStrengthScaling() {
        // Spawn multiple waves and check if monster strength increases
        ArrayList<Monster> firstWave = waveManager.spawnMonsters(GameModel.mapwidth, GameModel.mapheight);
        ArrayList<Monster> secondWave = waveManager.spawnMonsters(GameModel.mapwidth, GameModel.mapheight);
        
        Monster firstWaveMonster = firstWave.get(0);
        Monster secondWaveMonster = secondWave.get(0);
        
        assertTrue(secondWaveMonster.getMaxhealth() > firstWaveMonster.getMaxhealth());
        assertTrue(secondWaveMonster.getAttackPower() >= firstWaveMonster.getAttackPower());
        assertTrue(secondWaveMonster.getSpeed() >= firstWaveMonster.getSpeed());
    }

    @Test
    void testWaveNumberIncrement() {
        assertEquals(0, waveManager.getWaveNumber());
        
        waveManager.spawnMonsters(GameModel.mapwidth, GameModel.mapheight);
        assertEquals(1, waveManager.getWaveNumber());
        
        waveManager.spawnMonsters(GameModel.mapwidth, GameModel.mapheight);
        assertEquals(2, waveManager.getWaveNumber());
    }

    @Test
    void testMonstersPerWaveIncrement() {
        ArrayList<Monster> firstWave = waveManager.spawnMonsters(GameModel.mapwidth, GameModel.mapheight);
        ArrayList<Monster> secondWave = waveManager.spawnMonsters(GameModel.mapwidth, GameModel.mapheight);
        
        assertEquals(10, firstWave.size());
        assertEquals(11, secondWave.size());
    }
} 