package inf112.core.model.entities.attack;

public interface IAttack {
  
  /**
   * Executes the attack and returns the result.
   * The implementation should handle accuracy, damage calculation,
   * and critical hit chance.
   *
   * @return An {@link AttackResult} containing details of the attack outcome
   */
  AttackResult execute();

  /**
   * Returns the name of the attack.
   *
   * @return The name of the attack
   */
  String getName();
}
