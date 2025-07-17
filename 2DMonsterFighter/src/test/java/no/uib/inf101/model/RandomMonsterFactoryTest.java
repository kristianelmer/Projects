package no.uib.inf101.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.model.entities.Monster;
import no.uib.inf101.model.entities.Player;

public class RandomMonsterFactoryTest {
    private static final int MAP_WIDTH = 1200;
    private static final int MAP_HEIGHT = 800;
    private RandomMonsterFactory factory;
    private Player player;

    @BeforeEach
    void setUp() {
        factory = new RandomMonsterFactory();
        player = new Player(MAP_WIDTH/2, MAP_HEIGHT/2);
    }

    @Test
    void testMonsterSpawn() {
        Monster monster = factory.createRandomMonster(MAP_WIDTH, MAP_HEIGHT, player);
        
        assertNotNull(monster);
        assertTrue(monster.getX() >= 0 && monster.getX() <= MAP_WIDTH);
        assertTrue(monster.getY() >= 0 && monster.getY() <= MAP_HEIGHT);
    }

    @Test
    void testMonsterSpawnAwayFromPlayer() {
        // Create multiple monsters and verify they don't spawn too close to player
        for (int i = 0; i < 10; i++) {
            Monster monster = factory.createRandomMonster(MAP_WIDTH, MAP_HEIGHT, player);
            assertFalse(player.getAOE(100).intersects(monster.getBounds()));
        }
    }

    @Test
    void testMultipleMonsterSpawns() {
        // Test multiple monster spawns to ensure variety in positions
        Monster monster1 = factory.createRandomMonster(MAP_WIDTH, MAP_HEIGHT, player);
        Monster monster2 = factory.createRandomMonster(MAP_WIDTH, MAP_HEIGHT, player);
        
        // It's possible but very unlikely for two monsters to spawn at exactly the same position
        assertFalse(monster1.getX() == monster2.getX() && monster1.getY() == monster2.getY());
    }

    @Test
    void testMonsterProperties() {
        Monster monster = factory.createRandomMonster(MAP_WIDTH, MAP_HEIGHT, player);
        
        // Check default monster properties
        assertEquals(50, monster.getEntitySize());
        assertEquals(100, monster.getMaxhealth());
        assertEquals(100, monster.getHealth());
        assertEquals(2, monster.getSpeed());
        assertEquals(1, monster.getAttackPower());
    }
} 