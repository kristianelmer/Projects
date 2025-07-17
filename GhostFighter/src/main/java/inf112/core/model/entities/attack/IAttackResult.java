package inf112.core.model.entities.attack;

public interface IAttackResult {

  /**
   * Gets the name of the attack.
   *
   * @return the name of the attack
   */
  String getAttackName();

  /**
   * Gets the damage dealt by the attack.
   *
   * @return the damage value
   */
  int getDamage();

  /**
   * Checks if the attack hit the target.
   *
   * @return true if the attack hit, false otherwise
   */
  boolean wasHit();

  /**
   * Checks if the attack was a critical hit.
   *
   * @return true if the attack was a critical hit, false otherwise
   */
  boolean wasCritical();
}
