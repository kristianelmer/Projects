package no.uib.inf101.model.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonsterTest {
    private Monster monster;

    @BeforeEach
    void setUp() {
        monster = new Monster(100, 100);
    }

    @Test
    void testInitialState() {
        assertEquals(100, monster.getHealth());
        assertEquals(100, monster.getMaxhealth());
        assertEquals(2, monster.getSpeed());
        assertEquals(1, monster.getAttackPower());
        assertEquals(50, monster.getEntitySize());
    }

    @Test
    void testMovement() {
        int initialX = monster.getX();
        int initialY = monster.getY();

        monster.move(10, 0);
        assertEquals(initialX + 10, monster.getX());
        assertEquals(initialY, monster.getY());

        monster.move(0, 10);
        assertEquals(initialX + 10, monster.getX());
        assertEquals(initialY + 10, monster.getY());
    }

    @Test
    void testDamage() {
        monster.takeDamage(20);
        assertEquals(80, monster.getHealth());

        monster.takeDamage(80);
        assertEquals(0, monster.getHealth());
    }

    @Test
    void testEntitySize() {
        monster.setEntitySize(60);
        assertEquals(60, monster.getEntitySize());
    }

    @Test
    void testAttackPower() {
        monster.setAttackPower(5);
        assertEquals(5, monster.getAttackPower());
    }

    @Test
    void testHealth() {
        monster.setHealth(50);
        assertEquals(50, monster.getHealth());

        monster.setMaxHealth(150);
        assertEquals(150, monster.getMaxhealth());
    }

    @Test
    void testBounds() {
        assertEquals(monster.getX(), monster.getBounds().x);
        assertEquals(monster.getY(), monster.getBounds().y);
        assertEquals(monster.getEntitySize(), monster.getBounds().width);
        assertEquals(monster.getEntitySize(), monster.getBounds().height);
    }
} 