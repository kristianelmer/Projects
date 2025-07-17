package inf112.core.model;

import inf112.core.model.entities.attack.AttackResult;
import inf112.core.model.entities.ghost.BattleGhost;
import inf112.core.model.entities.ghost.OverworldGhost;
import inf112.core.model.entities.player.Player;

public class BattleScene {
  private final OverworldGhost overworldGhost;
  private final GameModel model;
  private final Player player;
  private final BattleGhost battleGhost;

  public BattleScene(GameModel model, OverworldGhost overworldGhost) {
    this.model = model;
    this.player = model.getPlayer();
    this.overworldGhost = overworldGhost;
    this.battleGhost = new BattleGhost(
        overworldGhost.getName(),
        overworldGhost.getLevel(),
        overworldGhost.getType());
  }

  /**
   * Executes one round of combat:
   * 1. Player attacks ghost
   * 2. Ghost attacks player
   * 3. Updates attack results in game model
   */
  public void fight() {
    AttackResult playerAttack = player.attack(battleGhost);
    model.setLastPlayerAttackResult(playerAttack);

    AttackResult ghostAttack = battleGhost.attack(player);
    model.setLastGhostAttackResult(ghostAttack);

    checkBattleEnded();
  }

  /**
   * Checks if battle should end (when either participant is defeated)
   * and triggers battle cleanup if needed.
   */
  public void checkBattleEnded() {
    if (!player.isAlive() || !battleGhost.isAlive()) {
      model.handleBattleEnded(overworldGhost);
    }
  }

  /**
   * Gets the battle-specific ghost representation.
   *
   * @return The BattleGhost instance participating in this battle
   */
  public BattleGhost getBattleGhost() {
    return battleGhost;
  }
}
