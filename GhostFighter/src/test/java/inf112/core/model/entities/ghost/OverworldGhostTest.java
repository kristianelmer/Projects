package inf112.core.model.entities.ghost;

import inf112.core.model.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OverworldGhostTest {

  private OverworldGhost ghost;

  @BeforeEach
  public void setup() {
    ghost = new OverworldGhost("Spooky", 3, GhostType.REGULAR, 32);
    ghost.setStartPosition(400f, 400f);
  }

  @Test
  public void testInitialValuesAreSetCorrectly() {
    assertEquals(400f, ghost.getX());
    assertEquals(400f, ghost.getY());
    assertEquals(32, ghost.getSize());
    assertEquals(32, ghost.getWidth());
    assertEquals(32, ghost.getHeight());
    assertNotNull(ghost.selectRandomDirection());
  }

  @Test
  public void testSetXAndY() {
    ghost.setX(250);
    ghost.setY(350);
    assertEquals(250f, ghost.getX());
    assertEquals(350f, ghost.getY());
  }

  @Test
  public void testSetStartPosition() {
    ghost.setStartPosition(100f, 200f);
    assertEquals(100f, ghost.getX());
    assertEquals(200f, ghost.getY());
  }

  @Test
  public void testMoveRightIncreasesX() {
    ghost.setCurrentDirection(Direction.RIGHT);
    float oldX = ghost.getX();
    ghost.move(Direction.RIGHT, 0.5f);
    assertTrue(ghost.getX() > oldX);
    assertEquals(ghost.getY(), 400f);
  }

  @Test
  public void testMoveLeftDecreasesX() {
    ghost.setCurrentDirection(Direction.LEFT);
    float oldX = ghost.getX();
    ghost.move(Direction.LEFT, 0.5f);
    assertTrue(ghost.getX() < oldX);
    assertEquals(ghost.getY(), 400f);
  }

  @Test
  public void testMoveUpRightChangesBothAxes() {
    ghost.setCurrentDirection(Direction.UP_RIGHT);
    float oldX = ghost.getX();
    float oldY = ghost.getY();
    ghost.move(Direction.UP_RIGHT, 0.2f);
    assertTrue(ghost.getX() > oldX);
    assertTrue(ghost.getY() > oldY);
  }

  @Test
  public void testMoveDownLeftChangesBothAxesNegatively() {
    ghost.setCurrentDirection(Direction.DOWN_LEFT);
    float oldX = ghost.getX();
    float oldY = ghost.getY();
    ghost.move(Direction.DOWN_LEFT, 0.2f);
    assertTrue(ghost.getX() < oldX);
    assertTrue(ghost.getY() < oldY);
  }

  @Test
  public void testUpdateIncreasesPositionWhenNotWaiting() {
    float xBefore = ghost.getX();
    float yBefore = ghost.getY();
    ghost.setCurrentDirection(Direction.RIGHT);
    ghost.update(0.5f);
    assertTrue(ghost.getX() > xBefore || ghost.getY() != yBefore);
  }

  @Test
  public void testUpdateSwitchesToWaitModeAfterOneSecond() {
    for (int i = 0; i < 11; i++) {
      ghost.update(0.1f); // 1.1s total
    }
    assertTrue(ghost.getWaitTimer() > 0f);
  }

  @Test
  public void testUpdateDoesNotMoveWhenWaiting() {
    ghost.update(1.1f);
    float x = ghost.getX();
    float y = ghost.getY();
    ghost.update(0.05f);
    assertEquals(x, ghost.getX());
    assertEquals(y, ghost.getY());
  }

  @Test
  public void testEqualsAndHashCode() {
    OverworldGhost g2 = new OverworldGhost("Spooky", 3, GhostType.REGULAR, 32);
    OverworldGhost g3 = new OverworldGhost("Creepy", 3, GhostType.BOSS, 32);
    assertEquals(ghost, g2);
    assertEquals(ghost.hashCode(), g2.hashCode());
    assertNotEquals(ghost, g3);
  }

  @Test
  public void testToStringContainsRelevantInformation() {
    String str = ghost.toString();
    assertTrue(str.contains("name=Spooky"));
    assertTrue(str.contains("x=" + ghost.getX()));
    assertTrue(str.contains("y=" + ghost.getY()));
  }

  @Test
  public void testBoundsReflectsPosition() {
    ghost.setX(123);
    ghost.setY(456);
    assertEquals(123f, ghost.getBounds().x);
    assertEquals(456f, ghost.getBounds().y);
  }
}
