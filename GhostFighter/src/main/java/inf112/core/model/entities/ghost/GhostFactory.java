package inf112.core.model.entities.ghost;

import inf112.core.model.entities.EntityFactory;

public abstract class GhostFactory extends EntityFactory {

  @Override
  public abstract OverworldGhost getNextGhost();
}
