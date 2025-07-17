package inf112.core.model.entities;

import com.badlogic.gdx.math.Rectangle;

public interface OverworldEntity {

  /**
   * Gets the current bounds of the entity.
   *
   * @return a {@link Rectangle} representing the entity's position and size
   */
  Rectangle getBounds();

  /**
   * Gets the x-coordinate of the entity's position.
   *
   * @return the x-coordinate
   */
  float getX();

  /**
   * Gets the y-coordinate of the entity's position.
   *
   * @return the y-coordinate
   */
  float getY();

  /**
   * Gets the width of the entity.
   *
   * @return the width
   */
  int getWidth();

  /**
   * Gets the height of the entity.
   *
   * @return the height
   */
  int getHeight();

  /**
   * Gets the overall size of the entity (e.g., width or bounding square size).
   *
   * @return the size of the entity
   */
  int getSize();
}
