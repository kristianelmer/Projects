package inf112.core.model.entities.ghost;

import inf112.core.model.entities.powerup.PowerUp;
import inf112.core.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class RandomGhostFactory extends GhostFactory {

  @Override
  public OverworldGhost getNextGhost() {
    List<OverworldGhost> ghostList = Utility.loadGhostsFromCSV("data/ghosts.csv");
    List<OverworldGhost> regularGhosts = new ArrayList<>();

    for (OverworldGhost ghost : ghostList) {
      if (ghost.getType() == GhostType.REGULAR) {
        regularGhosts.add(ghost);
      }
    }

    if (regularGhosts.isEmpty()) {
      return null;
    }

    int randomIndex = Utility.getRandomNumber(0, regularGhosts.size() - 1);
    return regularGhosts.get(randomIndex);
  }

  @Override
  public PowerUp getNextPowerUp(float x, float y, int size) {
    throw new UnsupportedOperationException("GhostFactory cannot create PowerUps");
  }
}