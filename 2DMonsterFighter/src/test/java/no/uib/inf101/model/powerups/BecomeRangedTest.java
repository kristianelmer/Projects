package no.uib.inf101.model.powerups;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.model.entities.Player;
import no.uib.inf101.model.GameModel;

public class BecomeRangedTest {
    private BecomeRanged becomeRanged;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100, 100);
        becomeRanged = new BecomeRanged(200, 200, player, true);
    }

    @Test
    void testInitialState() {
        assertEquals(200, becomeRanged.getX());
        assertEquals(200, becomeRanged.getY());
        assertEquals(30, becomeRanged.getSize());
        assertNotNull(becomeRanged.getBounds());
    }

    @Test
    void testActivate() {
        int initialWidth = player.getAttackRangeWidth();
        
        becomeRanged.activate();
        
        assertEquals(initialWidth, player.getAttackRangeWidth());
        assertEquals(Math.max(GameModel.mapheight, GameModel.mapwidth), player.getAttackRangeLength());
        assertTrue(player.getIsRanged());
    }

    @Test
    void testMultipleActivations() {
        becomeRanged.activate();
        becomeRanged.activate();
        
        // Second activation should not change anything
        assertEquals(Math.max(GameModel.mapheight, GameModel.mapwidth), player.getAttackRangeLength());
        assertTrue(player.getIsRanged());
    }
} 