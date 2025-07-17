package no.uib.inf101.model.powerups;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.model.entities.Player;
import java.awt.Rectangle;

public class PowerUpTest {
    private PowerUp powerUp;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100, 100);
        powerUp = new PowerUp(200, 200, player) {
            @Override
            public void activate() {
                // Mock implementation
            }
        };
    }

    @Test
    void testInitialState() {
        assertEquals(200, powerUp.getX());
        assertEquals(200, powerUp.getY());
        assertEquals(30, powerUp.getSize());
        assertNotNull(powerUp.getBounds());
    }

    @Test
    void testBounds() {
        Rectangle bounds = powerUp.getBounds();
        assertEquals(200, bounds.x);
        assertEquals(200, bounds.y);
        assertEquals(30, bounds.width);
        assertEquals(30, bounds.height);
    }

    @Test
    void testActivate() {
        assertThrows(UnsupportedOperationException.class, () -> {
            new PowerUp(0, 0, player).activate();
        });
    }

    @Test
    void testImage() {
        assertNull(powerUp.getImage());
    }
} 