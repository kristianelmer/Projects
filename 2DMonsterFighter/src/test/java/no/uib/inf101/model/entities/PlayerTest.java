package no.uib.inf101.model.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Rectangle;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100, 100);
    }

    @Test
    void testInitialState() {
        assertEquals(100, player.getHealth());
        assertEquals(100, player.getMaxhealth());
        assertEquals(10, player.getSpeed());
        assertEquals(50, player.getAttackPower());
        assertEquals(100, player.getAttackRangeWidth());
        assertEquals(100, player.getAttackRangeLength());
        assertFalse(player.getIsRanged());
        assertEquals(1.0, player.getAttackSpeed());
    }

    @Test
    void testMovement() {
        int initialX = player.getX();
        int initialY = player.getY();

        player.move(10, 0);
        assertEquals(initialX + 10, player.getX());
        assertEquals(initialY, player.getY());

        player.move(0, 10);
        assertEquals(initialX + 10, player.getX());
        assertEquals(initialY + 10, player.getY());
    }

    @Test
    void testDamage() {
        player.takeDamage(20);
        assertEquals(80, player.getHealth());

        player.takeDamage(80);
        assertEquals(0, player.getHealth());
    }

    @Test
    void testRangedMode() {
        player.setRanged(true);
        assertTrue(player.getIsRanged());

        player.setRanged(false);
        assertFalse(player.getIsRanged());
    }

    @Test
    void testAttackSpeed() {
        player.setAttackSpeed(2.0);
        assertEquals(2.0, player.getAttackSpeed());

        player.setAttackSpeed(0.5);
        assertEquals(0.5, player.getAttackSpeed());
    }

    @Test
    void testAttackArea() {
        int aoewidth = 50;
        Rectangle attackArea = player.getAOE(aoewidth);
        
        assertEquals(player.getX() - aoewidth, attackArea.x);
        assertEquals(player.getY() - aoewidth, attackArea.y);
        assertEquals(aoewidth * 2 + player.getEntitySize(), attackArea.width);
        assertEquals(aoewidth * 2 + player.getEntitySize(), attackArea.height);
    }

    @Test
    void testEntitySize() {
        player.setEntitySize(60);
        assertEquals(60, player.getEntitySize());
        assertEquals(60, player.getBounds().width);
        assertEquals(60, player.getBounds().height);
    }

    @Test
    void testAttackPower() {
        player.setAttackPower(75);
        assertEquals(75, player.getAttackPower());
    }

    @Test
    void testHealth() {
        player.setHealth(50);
        assertEquals(50, player.getHealth());

        player.setMaxHealth(150);
        assertEquals(150, player.getMaxhealth());
    }
} 