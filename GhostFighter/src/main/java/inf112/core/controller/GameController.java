package inf112.core.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import inf112.core.audio.AudioController;
import inf112.core.model.Direction;
import inf112.core.model.GameState;
import inf112.core.model.entities.ghost.OverworldGhost;
import inf112.core.model.entities.powerup.PowerUp;

public class GameController {
  private final ControllableGameModel model;
  private GameState gameState;
  private final AudioController audioController;

  public GameController(ControllableGameModel model) {
    this.model = model;
    this.gameState = model.getGameState();
    this.audioController = model.getAudioController();
  }

  /**
   * Handles input from the player.
   *
   * @param deltaTime The time since the last frame.
   */
  public void handleInput(float deltaTime) {
    gameState = model.getGameState();

    audioController.handleMusicInput();

    if (gameState == GameState.OVERWORLD || gameState == GameState.BATTLESCENE || gameState == GameState.PAUSED) {
      if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        model.handlePause();
    }

    if (gameState == GameState.OVERWORLD) {
      handleOverworldInput(deltaTime);
    }

    if (gameState == GameState.BATTLESCENE) {
      handleBattleSceneInput();
    }
  }

  private void handleOverworldInput(float deltaTime) {
    handlePlayerMovement(deltaTime);
    handlePlayerAnimation();
    handleGhostInteraction();
  }

  private void handlePlayerMovement(float deltaTime) {
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      model.movePlayer(Direction.LEFT, deltaTime);
      model.setPlayerIsMoving(true);
    }

    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      model.movePlayer(Direction.RIGHT, deltaTime);
      model.setPlayerIsMoving(true);
    }

    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      model.movePlayer(Direction.UP, deltaTime);
      model.setPlayerIsMoving(true);
    }

    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      model.movePlayer(Direction.DOWN, deltaTime);
      model.setPlayerIsMoving(true);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !model.getPlayerIsMoving()) {
      model.dance();
    }
  }

  private void handlePlayerAnimation() {
    if (!Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
        !Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.UP)
        && !Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      model.setPlayerIsMoving(false);
    }

    if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)
        && !Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.DOWN)) ||
        (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.DOWN)
            && !Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT))) {
      model.setPlayerIsMoving(false);
    }
  }

  private void handleGhostInteraction() {
    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
      OverworldGhost nearestGhost = model.getNearestGhost();
      if (nearestGhost != null && model.canInteract(nearestGhost)) {
        audioController.switchToBattleMusic(); // Start battle music
        model.startBattle(nearestGhost);
        return;
      }

      PowerUp nearestPowerUp = model.getNearestPowerUp();
      if (nearestPowerUp != null && model.getPlayer().getBounds().overlaps(nearestPowerUp.getBounds())) {
        model.getPlayer().restoreHearts();
        model.removePowerUp(nearestPowerUp);
        model.dance();
      }
    }
  }

  private void handleBattleSceneInput() {
    if (Gdx.input.isKeyPressed(Input.Keys.Q)) { // Q to instantly end battle
      audioController.switchToMainMusic();
      model.handleBattleEnded();
    }
  }

  /**
   * Disposes of all audio resources when the game is closed.
   */
  public void dispose() {
    audioController.dispose();
  }
}
