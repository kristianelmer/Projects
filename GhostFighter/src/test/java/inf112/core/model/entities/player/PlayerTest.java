package inf112.core.model.entities.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Rectangle;

import inf112.core.model.Direction;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  private Player player;

  @BeforeEach
  void setUp() {
    player = new Player(100, 100);
  }

  @Test
  void testInitialHealth() {
    assertEquals(50, player.getHealth());
  }

  @Test
  void testInitialHearts() {
    assertEquals(3, player.getHearts());
  }

  @Test
  void testTakeDamage() {
    player.takeDamage(5);
    assertEquals(45, player.getHealth());

    player.takeDamage(100);
    assertEquals(0, player.getHealth());
  }

  @Test
  void testSetMaxHealth() {
    player.setMaxHealth(80);
    player.takeDamage(15); // shouldn´t affect max health
    assertEquals(80, player.getMaxHealth());
  }

  @Test
  void testSetAndGetHealth() {
    player.setHealth(30);
    assertEquals(30, player.getHealth());
  }

  @Test
  void testGetBounds() {
    Rectangle bounds = player.getBounds();
    assertEquals(100, bounds.x);
    assertEquals(100, bounds.y);
    assertEquals(20, bounds.width);
    assertEquals(20, bounds.height);
  }

  @Test
  void testGetSizeWidthHeight() {
    assertEquals(20, player.getSize());
    assertEquals(20, player.getWidth());
    assertEquals(20, player.getHeight());
  }

  @Test
  void testGetNewBoundsLeft() {
    float deltaTime = 0.5f;
    Rectangle newBounds = player.getNewBounds(Direction.LEFT, deltaTime);
    assertEquals(100 - 200 * deltaTime, newBounds.x);
    assertEquals(100, newBounds.y);
  }

  @Test
  void testGetNewBoundsUp() {
    float deltaTime = 0.25f;
    Rectangle newBounds = player.getNewBounds(Direction.UP, deltaTime);
    assertEquals(100 + 200 * deltaTime, newBounds.y);
  }

  @Test
  void testRestoreHearts() {
    player.decreaseHearts();
    player.restoreHearts();
    assertEquals(3, player.getHearts());
  }

  @Test
  void testDecreaseHearts() {
    player.decreaseHearts();
    assertEquals(2, player.getHearts());

    player.decreaseHearts();
    player.decreaseHearts();
    assertEquals(0, player.getHearts());
  }

  @Test
  void testMoveLeft() {
    float deltaTime = 0.5f;
    player = new Player(200, 200);
    player.move(Direction.LEFT, deltaTime);

    assertEquals(200 - 200 * deltaTime, player.getX());
  }

  @Test
  void testMoveRight() {
    float deltaTime = 0.5f;
    player = new Player(200, 200);
    player.move(Direction.RIGHT, deltaTime);

    assertEquals(200 + 200 * deltaTime, player.getX());
  }

  @Test
  void testMoveUp() {
    float deltaTime = 0.5f;
    player = new Player(200, 200);
    player.move(Direction.UP, deltaTime);
    player.move(Direction.LEFT, deltaTime);

    assertEquals(200 + (200 * deltaTime), player.getY());
  }

  @Test
  void testMoveDown() {
    float deltaTime = 0.5f;
    player = new Player(200, 200);
    player.move(Direction.DOWN, deltaTime);

    assertEquals(200 - 200 * deltaTime, player.getY());
  }

  @Test
  void testMoveUpAndLeft() {
    float deltaTime = 0.5f;
    player = new Player(200, 200);

    player.move(Direction.UP, deltaTime);
    player.move(Direction.LEFT, deltaTime);

    assertEquals(200 + 200 * deltaTime, player.getY());

    assertEquals(200 - 200 * deltaTime, player.getX());
  }

  @Test
  void testIsAlive() {
    assertTrue(player.isAlive());

    player.takeDamage(player.getHealth());
    assertFalse(player.isAlive());
  }

  @Test
  void testToStringFormat() {
    String expected = "[x=100.0, y=100.0, width=20.0, height=20.0]";
    assertEquals(expected, player.toString());
  }
}
