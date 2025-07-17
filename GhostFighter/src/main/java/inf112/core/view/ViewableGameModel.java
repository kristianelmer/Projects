package inf112.core.view;

import java.util.List;

import inf112.core.audio.AudioController;
import inf112.core.model.BattleScene;
import inf112.core.model.Direction;
import inf112.core.model.GameState;
import inf112.core.model.entities.OverworldEntity;
import inf112.core.model.entities.attack.AttackResult;
import inf112.core.model.entities.ghost.OverworldGhost;
import inf112.core.model.entities.player.Player;
import inf112.core.model.entities.powerup.PowerUp;

/**
 * Interface representing read-only access to the game model for the view layer.
 * Provides methods for retrieving game state, player status, ongoing battles,
 * nearby entities, and audio controller information.
 */
public interface ViewableGameModel {

  /**
   * Gets the player entity.
   *
   * @return The Player instance
   */
  Player getPlayer();

  /**
   * Gets the current active battle scene.
   *
   * @return The BattleScene instance, or null if no battle is occurring
   */
  BattleScene getBattleScene();

  /**
   * Checks if the player can interact with a given overworld entity.
   *
   * @param entity The entity to check
   * @return true if the player can interact, false otherwise
   */
  boolean canInteract(OverworldEntity entity);

  /**
   * Gets the current state of the game.
   *
   * @return The current GameState
   */
  GameState getGameState();

  /**
   * Handles selection logic for in-game menus.
   */
  void handleMenuSelection();

  /**
   * Restarts the game and returns to the main menu.
   */
  void restartGoToMenu();

  /**
   * Toggles the paused state of the game.
   */
  void handlePause();

  /**
   * Gets the result of the last attack made by the player in a battle.
   *
   * @return The player's last AttackResult
   */
  AttackResult getLastPlayerAttackResult();

  /**
   * Gets the result of the last attack made by a ghost in a battle.
   *
   * @return The ghost's last AttackResult
   */
  AttackResult getLastGhostAttackResult();

  /**
   * Sets the result of the player's last attack in a battle.
   *
   * @param result The result to set
   */
  void setLastPlayerAttackResult(AttackResult result);

  /**
   * Sets the result of the ghost's last attack in a battle.
   *
   * @param result The result to set
   */
  void setLastGhostAttackResult(AttackResult result);

  /**
   * Gets a list of all active ghosts currently in the overworld.
   *
   * @return A list of OverworldGhosts
   */
  List<OverworldGhost> getActiveGhosts();

  /**
   * Gets a list of all active power-ups currently in the overworld.
   *
   * @return A list of PowerUps
   */
  List<PowerUp> getActivePowerUps();

  /**
   * Gets the ghost nearest to the player.
   *
   * @return The nearest OverworldGhost, or null if none are nearby
   */
  OverworldGhost getNearestGhost();

  /**
   * Gets the power-up nearest to the player.
   *
   * @return The nearest PowerUp, or null if none are nearby
   */
  PowerUp getNearestPowerUp();

  /**
   * Gets the direction the player is currently facing or moving.
   *
   * @return The current Direction
   */
  Direction getDirection();

  /**
   * Sets the direction the player is facing or moving.
   *
   * @param direction The direction to set
   */
  void setDirection(Direction direction);

  /**
   * Checks whether the player is currently moving.
   *
   * @return true if the player is moving, false otherwise
   */
  boolean getPlayerIsMoving();

  /**
   * Gets the current value of the player's dance counter.
   *
   * @return The number of times the player has danced
   */
  int getDanceCounter();

  /**
   * Increments the player's dance counter.
   */
  void addDanceCounter();

  /**
   * Gets the game's audio controller for managing sound effects and music.
   *
   * @return The AudioController instance
   */
  AudioController getAudioController();
}
