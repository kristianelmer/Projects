package inf112.core.model.entities.powerup;

import com.badlogic.gdx.math.Rectangle;

import inf112.core.model.entities.OverworldEntity;

public class PowerUp implements OverworldEntity {
  private final Rectangle bounds;
  private final int size;

  public PowerUp(float x, float y, int size) {
    this.bounds = new Rectangle(x, y, size, size);
    this.size = size;
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
  public int getWidth() {
    return size;
  }

  @Override
  public int getHeight() {
    return size;
  }

  @Override
  public int getSize() {
    return size;
  }
}
