package no.uib.inf101.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.model.entities.Monster;

public class BossFactoryTest {
    private static final int MAP_WIDTH = 1200;
    private static final int MAP_HEIGHT = 800;

    @BeforeEach
    void setUp() {
        // Reset boss counter before each test
        BossFactory.bossCounter = 0;
    }

    @Test
    void testBossSpawn() {
        Monster boss = BossFactory.spawnBoss(MAP_WIDTH, MAP_HEIGHT);
        
        assertNotNull(boss);
        assertEquals(1, BossFactory.bossCounter);
        
        // Check boss properties
        assertEquals(150, boss.getEntitySize());
        assertEquals(10, boss.getAttackPower());
        assertEquals(1000, boss.getMaxhealth());
        assertEquals(1000, boss.getHealth());
        
        // Check boss position (should be centered)
        int expectedX = MAP_WIDTH/2 - boss.getEntitySize()/2;
        int expectedY = MAP_HEIGHT/2 - boss.getEntitySize()/2;
        assertEquals(expectedX, boss.getX());
        assertEquals(expectedY, boss.getY());
    }

    @Test
    void testMultipleBossSpawns() {
        // Spawn multiple bosses and check if their properties scale
        Monster firstBoss = BossFactory.spawnBoss(MAP_WIDTH, MAP_HEIGHT);
        Monster secondBoss = BossFactory.spawnBoss(MAP_WIDTH, MAP_HEIGHT);
        
        assertEquals(2, BossFactory.bossCounter);
        
        // Check if second boss is stronger
        assertTrue(secondBoss.getAttackPower() > firstBoss.getAttackPower());
        assertTrue(secondBoss.getMaxhealth() > firstBoss.getMaxhealth());
        
        // Verify scaling values
        assertEquals(20, secondBoss.getAttackPower()); // 10 * bossCounter
        assertEquals(2000, secondBoss.getMaxhealth()); // 1000 * bossCounter
    }

    @Test
    void testBossBounds() {
        Monster boss = BossFactory.spawnBoss(MAP_WIDTH, MAP_HEIGHT);
        
        // Check if bounds are properly set
        assertEquals(boss.getEntitySize(), boss.getBounds().width);
        assertEquals(boss.getEntitySize(), boss.getBounds().height);
        assertEquals(boss.getX(), boss.getBounds().x);
        assertEquals(boss.getY(), boss.getBounds().y);
    }
} 