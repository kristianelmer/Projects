package no.uib.inf101.model.powerups;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.model.entities.Player;

public class AttackWidthBoostTest {
    private AttackWidthBoost attackWidthBoost;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100, 100);
        attackWidthBoost = new AttackWidthBoost(200, 200, player, true);
    }

    @Test
    void testInitialState() {
        assertEquals(200, attackWidthBoost.getX());
        assertEquals(200, attackWidthBoost.getY());
        assertEquals(30, attackWidthBoost.getSize());
        assertNotNull(attackWidthBoost.getBounds());
    }

    @Test
    void testActivate() {
        int initialWidth = player.getAttackRangeWidth();
        int initialLength = player.getAttackRangeLength();
        
        attackWidthBoost.activate();
        
        assertEquals(initialWidth + 50, player.getAttackRangeWidth());
        assertEquals(initialLength, player.getAttackRangeLength());
    }

    @Test
    void testMultipleActivations() {
        int initialWidth = player.getAttackRangeWidth();
        
        attackWidthBoost.activate();
        attackWidthBoost.activate();
        
        assertEquals(initialWidth + 100, player.getAttackRangeWidth());
    }
} 