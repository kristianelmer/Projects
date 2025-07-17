package inf112.core.model.entities.ghost;

import com.badlogic.gdx.math.Rectangle;

import inf112.core.model.Direction;
import inf112.core.model.entities.OverworldEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import inf112.core.utils.Utility;

public class OverworldGhost extends Ghost implements OverworldEntity {
  private final int size;
  private final Rectangle bounds;
  private static final float SPEED = 200;
  private Direction currentDirection;
  private float moveTimer;
  private float waitTimer;

  public OverworldGhost(String name, int level, GhostType type, int size) {
    super(name, level, type);
    
    // bounds is set in model because it uses MapData, and OverworldGhost should not
    // be dependent of MapData
    this.bounds = new Rectangle(0, 0, size, size); // will be overwritten in GameModel
    this.size = size;
    this.currentDirection = selectRandomDirection();
    this.moveTimer = 0f;
    this.waitTimer = 0f;
  }

  /**
   * Updates the ghost's position and movement.
   *
   * @param deltaTime The time since the last update
   */
  public void update(float deltaTime) {
    if (waitTimer > 0) {
      waitTimer -= deltaTime;
      return; // Don't move while waiting
    }

    if (moveTimer < 1f) {
      move(currentDirection, deltaTime);
      moveTimer += deltaTime;
    } else {
      waitTimer = getRandomWaitTime();
      moveTimer = 0f;
      currentDirection = selectRandomDirection();
    }
  }

  /**
   * Sets the x position of the ghost.
   *
   * @param x The new x position
   */
  public void setX(float x) {
    this.bounds.x = x;
  }

  /**
   * Sets the y position of the ghost.
   *
   * @param y The new y position
   */
  public void setY(float y) {
    this.bounds.y = y;
  }

  /**
   * Sets the start position of the ghost.
   *
   * @param x The new x position
   * @param y The new y position
   */
  public void setStartPosition(float x, float y) {
    this.bounds.x = x;
    this.bounds.y = y;
  }

  // change wait time interval to adjust ghost movement timing
  private float getRandomWaitTime() {
    int randomMilliseconds = Utility.getRandomNumber(500, 8000);
    return randomMilliseconds / 1000f;
  }

  public void setCurrentDirection(Direction direction) {
    this.currentDirection = direction;
  }

  /**
   * Selects a random direction for the ghost to move in.
   *
   * @return A random direction
   */
  public Direction selectRandomDirection() {
    Direction newDirection;
    do {
      newDirection = Direction.values()[Utility.getRandomNumber(0, 7)];
    } while (newDirection == currentDirection);
    return newDirection;
  }

  /**
   * Moves the ghost in a given direction.
   *
   * @param direction The direction to move in
   * @param deltaTime The time since the last update
   */
  public void move(Direction direction, float deltaTime) {
    List<Float> movementVector = calculateMovementVector(direction);
    float dx = movementVector.get(0);
    float dy = movementVector.get(1);

    List<Float> normalizedVector = normalizeMovementVector(dx, dy);
    dx = normalizedVector.get(0);
    dy = normalizedVector.get(1);

    float newX = bounds.x + dx * SPEED * deltaTime;
    float newY = bounds.y + dy * SPEED * deltaTime;

    updatePosition(newX, newY);
  }

  /**
   * Calculates the movement vector (dx, dy) based on the current direction.
   */
  private List<Float> calculateMovementVector(Direction direction) {
    List<Float> movementVector = new ArrayList<>();
    float dx = 0;
    float dy = 0;

    switch (direction) {
      case LEFT:
        dx = -1;
        break;
      case RIGHT:
        dx = 1;
        break;
      case UP:
        dy = 1;
        break;
      case DOWN:
        dy = -1;
        break;
      case UP_LEFT:
        dx = -1;
        dy = 1;
        break;
      case UP_RIGHT:
        dx = 1;
        dy = 1;
        break;
      case DOWN_LEFT:
        dx = -1;
        dy = -1;
        break;
      case DOWN_RIGHT:
        dx = 1;
        dy = -1;
        break;
      default:
        break;
    }

    movementVector.add(dx);
    movementVector.add(dy);
    return movementVector;
  }

  /**
   * Normalizes the movement vector for diagonal movement.
   */
  private List<Float> normalizeMovementVector(float dx, float dy) {
    List<Float> normalizedVector = new ArrayList<>();
    if (dx != 0 && dy != 0) {
      float length = (float) Math.sqrt(dx * dx + dy * dy);
      dx /= length;
      dy /= length;
    }

    normalizedVector.add(dx);
    normalizedVector.add(dy);
    return normalizedVector;
  }

  private void updatePosition(float newX, float newY) {
    bounds.x = newX;
    bounds.y = newY;
  }

  @Override
  public Rectangle getBounds() {
    return bounds;
  }

  @Override
  public float getX() {
    return bounds.x;
  }

  @Override
  public float getY() {
    return bounds.y;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public int getWidth() {
    return size;
  }

  @Override
  public int getHeight() {
    return size;
  }

  /**
   * Returns a string representation of the ghost.
   *
   * @return A string representation of the ghost
   */
  public String toString() {
    return "[name=" + this.getName() + "size=" + size + ", x=" + bounds.x + ", y=" + bounds.y + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    OverworldGhost ghost = (OverworldGhost) obj;
    return this.getLevel() == ghost.getLevel() && Objects.equals(this.getName(), ghost.getName())
        && this.getType() == ghost.getType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getName(), this.getLevel(), this.getType());
  }

  public float getWaitTimer() {
    return waitTimer;
  }
}
