package inf112.core.model.entities.ghost;

import java.util.List;

import inf112.core.model.entities.attack.Attack;
import inf112.core.model.entities.attack.AttackRegistry;
import inf112.core.model.entities.attack.AttackResult;
import inf112.core.model.entities.player.Player;
import inf112.core.utils.Utility;

public class BattleGhost extends Ghost {
  private int health;
  private final int maxHealth;
  private final List<Attack> attacks;

  public BattleGhost(String name, int level, GhostType type) {
    super(name, level, type);
    this.maxHealth = generateHealth(level, type);
    this.health = maxHealth;
    this.attacks = AttackRegistry.ATTACKS;
  }

  /**
   * Generates health based on type
   */
  private int generateHealth(int level, GhostType type) {
    int levelValue = level * 5;
    int typeValue = 0;

    if (type == GhostType.REGULAR) {
      typeValue = 2 * level;
    } else if (type == GhostType.BOSS) {
      typeValue = 4;
    }

    int baseHealth = levelValue + typeValue;
    int variation = Utility.getRandomNumber(-4, 4);
    return baseHealth + variation;
  }

  private Attack getRandomAttack() {
    return attacks.get(Utility.getRandomNumber(0, attacks.size() - 1));
  }

  /**
   * Attacks the player with a random attack.
   *
   * @param player The player to attack
   * @return The result of the attack
   */
  public AttackResult attack(Player player) {
    Attack chosenAttack = getRandomAttack();
    AttackResult result = chosenAttack.execute();

    if (result.wasHit()) {
      player.takeDamage(result.getDamage());
    }

    return result;
  }

  /**
   * Gets the current health of the ghost.
   *
   * @return The current health of the ghost
   */
  public int getHealth() {
    return health;
  }

  /**
   * Takes damage from an attack.
   *
   * @param damage The amount of damage to take
   */
  public void takeDamage(int damage) {
    health -= damage;
    if (health < 0) {
      health = 0;
    }
  }

  /**
   * Checks if the ghost is alive.
   *
   * @return true if the ghost is alive, false otherwise
   */
  public boolean isAlive() {
    return health > 0;
  }

  /**
   * Gets the maximum health of the ghost.
   *
   * @return The maximum health of the ghost
   */
  public int getMaxHealth() {
    return maxHealth;
  }
}
