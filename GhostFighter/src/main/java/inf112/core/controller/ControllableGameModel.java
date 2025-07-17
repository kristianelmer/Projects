package inf112.core.controller;

import inf112.core.audio.AudioController;
import inf112.core.model.Direction;
import inf112.core.model.GameState;
import inf112.core.model.entities.OverworldEntity;
import inf112.core.model.entities.ghost.OverworldGhost;
import inf112.core.model.entities.player.Player;
import inf112.core.model.entities.powerup.PowerUp;

public interface ControllableGameModel {

  /**
   * Updates the game model state. Should be called once per frame.
   */
  void update();

  /**
   * Moves the player in the specified direction.
   *
   * @param direction The direction to move the player
   * @param deltaTime The time since last frame (for frame-rate independent
   *                  movement)
   */
  void movePlayer(Direction direction, float deltaTime);

  /**
   * Checks if the player can interact with an entity.
   *
   * @param entity The entity to check interaction with
   * @return true if interaction is possible, false otherwise
   */
  boolean canInteract(OverworldEntity entity);

  /**
   * Gets the current game state.
   *
   * @return The current GameState
   */
  GameState getGameState();

  /**
   * Sets the game state.
   *
   * @param gameState The new game state
   */
  void setGameState(GameState gameState);

  /**
   * Handles cleanup when a battle ends.
   */
  void handleBattleEnded();

  /**
   * H
   * 
   * @param defeatedGhost
   */
  void handleBattleEnded(OverworldGhost defeatedGhost);

  /**
   * Handles menu selection logic based on current game state.
   */
  void handleMenuSelection();

  /**
   * Toggles pause state or returns to previous state if already paused.
   */
  void handlePause();

  /**
   * Initiates a battle with the specified ghost.
   *
   * @param ghost The ghost to battle against
   */
  void startBattle(OverworldGhost ghost);

  /**
   * Gets the nearest ghost to the player.
   *
   * @return The nearest OverworldGhost, or null if none exists
   */
  OverworldGhost getNearestGhost();

  /**
   * Checks if the player is currently moving.
   *
   * @return true if player is moving, false otherwise
   */
  boolean getPlayerIsMoving();

  /**
   * Sets the player's moving state.
   *
   * @param isMoving true to set player as moving, false otherwise
   */
  void setPlayerIsMoving(boolean isMoving);

  /**
   * Gets the nearest power-up to the player.
   *
   * @return The nearest PowerUp, or null if none exists
   */
  PowerUp getNearestPowerUp();

  /**
   * Gets the player entity.
   *
   * @return The Player instance
   */
  Player getPlayer();

  /**
   * Removes a collected power-up from the game world.
   *
   * @param powerUp The power-up to remove
   */
  void removePowerUp(PowerUp powerUp);

  /**
   * Gets the audio controller for managing game sounds.
   *
   * @return The AudioController instance
   */
  AudioController getAudioController();

  /**
   * Makes the player character perform a dance animation/action.
   */
  void dance();
}
