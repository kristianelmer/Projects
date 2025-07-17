package inf112.core.model.entities.powerup;

import inf112.core.model.entities.EntityFactory;
import inf112.core.model.entities.ghost.OverworldGhost;

public class PowerUpFactory extends EntityFactory {

  @Override
  public PowerUp getNextPowerUp(float x, float y, int size) {
    return new PowerUp(x, y, size);
  }

  @Override
  public OverworldGhost getNextGhost() {
    throw new UnsupportedOperationException("PowerUpFactory cannot create ghosts");
  }
}
