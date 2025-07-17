package inf112.core.model.entities.powerup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerUpTest {

  private PowerUp powerUp;

  @BeforeEach
  void setUp() {
    powerUp = new PowerUp(0, 0, 1);
  }

  @Test
  void testGetBounds() {
    assertEquals(0, powerUp.getBounds().x);
    assertEquals(0, powerUp.getBounds().y);
    assertEquals(1, powerUp.getBounds().width);
    assertEquals(1, powerUp.getBounds().height);
  }

  @Test
  void testGetX() {
    assertEquals(0, powerUp.getX());

  }

  @Test
  void testGetY() {
    assertEquals(0, powerUp.getY());
  }

  @Test
  void testGetWidth() {
    assertEquals(1, powerUp.getWidth());
  }

  @Test
  void testGetHeight() {
    assertEquals(1, powerUp.getHeight());
  }

  @Test
  void testGetSize() {
    assertEquals(1, powerUp.getSize());
  }
}
