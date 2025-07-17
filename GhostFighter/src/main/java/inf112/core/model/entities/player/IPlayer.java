package inf112.core.model.entities.player;

import com.badlogic.gdx.math.Rectangle;
import inf112.core.model.Direction;
import inf112.core.model.entities.attack.AttackResult;
import inf112.core.model.entities.ghost.BattleGhost;

public interface IPlayer {

  /**
   * Moves the player in the specified direction based on the elapsed time.
   *
   * @param direction The direction in which to move the player.
   * @param deltaTime The time elapsed since the last frame, used for smooth
   *                  movement.
   */
  void move(Direction direction, float deltaTime);

  /**
   * Calculates the new bounds of the player if it were to move in the specified
   * direction.
   *
   * @param direction The direction in which the player is moving.
   * @param deltaTime The time elapsed since the last frame, used for smooth
   *                  movement.
   * @return A Rectangle representing the new bounds of the player.
   */
  Rectangle getNewBounds(Direction direction, float deltaTime);

  /**
   * Sets the player's health to the specified value.
   *
   * @param newHealth The new health value for the player.
   */
  void setHealth(int newHealth);

  /**
   * Sets the player's current max health to the specified value.
   * Max health is to be increased between battles.
   *
   * @param newHealth The new max health value for the player.
   */
  void setMaxHealth(int newHealth);

  /**
   * Checks if the player is alive (health greater than 0).
   *
   * @return True if the player is alive, false otherwise.
   */
  boolean isAlive();

  /**
   * Reduces the player's health by the specified damage amount.
   *
   * @param damage The amount of damage to inflict on the player.
   */
  void takeDamage(int damage);

  /**
   * Returns the player's size.
   *
   * @return The player's size.
   */
  int getSize();

  /**
   * Returns the player's current health.
   *
   * @return The player's current health.
   */
  int getHealth();

  /**
   * Returns the player's maximum health.
   *
   * @return The player's maximum health.
   */
  int getMaxHealth();

  /**
   * Returns the number of hearts (lives) the player has remaining.
   *
   * @return The current number of hearts/lives
   */
  int getHearts();

  /**
   * Restores the player's hearts (lives) to their maximum value.
   * Typically used when the player gains an extra life or at level start.
   */
  void restoreHearts();

  /**
   * Decreases the player's hearts (lives) by one.
   * Typically called when the player dies or loses a life.
   */
  void decreaseHearts();

  /**
   * Executes an attack on a BattleGhost and returns the result of the attack.
   *
   * @param ghost The BattleGhost to attack.
   * @return An AttackResult object representing the outcome of the attack.
   */
  AttackResult attack(BattleGhost ghost);
}
