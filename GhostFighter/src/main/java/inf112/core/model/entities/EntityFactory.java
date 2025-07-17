package inf112.core.model.entities;

import inf112.core.model.entities.ghost.OverworldGhost;
import inf112.core.model.entities.powerup.PowerUp;

public abstract class EntityFactory {

  /**
   * Generates and returns the next ghost entity.
   * Implementations should control ghost difficulty, type, and spawn logic.
   *
   * @return A new OverworldGhost instance (never null)
   */
  public abstract OverworldGhost getNextGhost();

  /**
   * Generates and returns the next power-up entity.
   * Implementations should control power-up type and spawn location.
   *
   * @return A new PowerUp instance
   */
  public abstract PowerUp getNextPowerUp(float x, float y, int size);
}
