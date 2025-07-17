package inf112.core.model.entities.powerup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerUpFactoryTest {

  private PowerUpFactory powerUpFactory;

  @BeforeEach
  void setUp() {
    powerUpFactory = new PowerUpFactory();

  }

  @Test
  void testPowerUpIsWithinExpectedBounds() {
    PowerUp powerUp = powerUpFactory.getNextPowerUp(0, 0, 1);
    assertNotNull(powerUp);
    assertEquals(0, powerUp.getX());
    assertEquals(0, powerUp.getY());
    assertEquals(1, powerUp.getWidth());
    assertEquals(1, powerUp.getHeight());

  }

  @Test
  void testPowerUpIsNotNull() {
    PowerUp powerUp = powerUpFactory.getNextPowerUp(0, 0, 1);
    assertNotNull(powerUp);
    assertNotNull(powerUpFactory.getNextPowerUp(0, 0, 1));

  }

  @Test
  void testGetNextGhost_ThrowsException() {
    try {
      powerUpFactory.getNextGhost();
    } catch (UnsupportedOperationException e) {
      assertEquals("PowerUpFactory cannot create ghosts", e.getMessage());
    }
  }
}
