package inf112.core.model;

import com.badlogic.gdx.math.Rectangle;

import inf112.core.audio.AudioController;
import inf112.core.controller.ControllableGameModel;
import inf112.core.map.MapData;
import inf112.core.model.entities.OverworldEntity;
import inf112.core.model.entities.attack.AttackResult;
import inf112.core.model.entities.ghost.OverworldGhost;
import inf112.core.model.entities.ghost.RandomGhostFactory;
import inf112.core.model.entities.player.Player;
import inf112.core.model.entities.powerup.PowerUp;
import inf112.core.model.entities.powerup.PowerUpFactory;
import inf112.core.utils.Utility;
import inf112.core.view.ViewableGameModel;
import inf112.core.model.entities.ghost.GhostType;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class GameModel implements ViewableGameModel, ControllableGameModel {

  private static final int POWERUP_SIZE = 20;

  private final RandomGhostFactory ghostFactory;
  private final List<OverworldGhost> activeGhosts;
  private final List<PowerUp> activePowerUps;
  private final MapData mapData;

  private Player player;
  private PowerUpFactory powerUpFactory;
  private GameState gameState;
  private GameState gameStateBeforePause;
  private int battlesWon;
  private boolean playerIsMoving;
  public Direction currdirection;
  private BattleScene battleScene;
  private AttackResult lastPlayerAttackResult;
  private AttackResult lastGhostAttackResult;
  public int danceCounter;
  private AudioController audioController;

  public GameModel(MapData mapData) {
    this.mapData = mapData;
    this.player = new Player(getStartPos().x, getStartPos().y);
    this.ghostFactory = new RandomGhostFactory();
    this.powerUpFactory = new PowerUpFactory();
    this.activeGhosts = new ArrayList<>();
    this.activePowerUps = new ArrayList<>();
    this.gameState = GameState.WELCOME_SCREEN;
    this.gameStateBeforePause = gameState;
    this.currdirection = Direction.DOWN;
    this.playerIsMoving = false;
    this.audioController = new AudioController();

    spawnInitialGhosts(10); // edit for debug
  }

  @Override
  public void update() {
    updateGhosts();

    if (gameState == GameState.BATTLESCENE && battleScene != null) {
      battleScene.checkBattleEnded();
    }
  }

  private void updateGhosts() {
    for (OverworldGhost ghost : activeGhosts) {
      float prevX = ghost.getX();
      float prevY = ghost.getY();
      ghost.update(Gdx.graphics.getDeltaTime());

      if (!isLegalGhostPosition(ghost.getBounds())) {
        ghost.setX(prevX);
        ghost.setY(prevY);
        ghost.setCurrentDirection(ghost.selectRandomDirection());
      }
    }
  }

  private boolean isLegalGhostPosition(Rectangle bounds) {
    MapObjects legalRects = mapData.getGhostLegalPos();
    for (MapObject legal : legalRects) {
      if (legal instanceof RectangleMapObject) {
        Rectangle legalRect = ((RectangleMapObject) legal).getRectangle();
        if (bounds.overlaps(legalRect)) {
          return true;
        }
      }
    }
    return false;
  }

  private void spawnInitialGhosts(int numberOfGhosts) {
    for (int i = 0; i < numberOfGhosts; i++) {
      OverworldGhost ghost = ghostFactory.getNextGhost();
      Rectangle legalPos = getLegalGhostPosition();
      ghost.setX(legalPos.x);
      ghost.setY(legalPos.y);
      activeGhosts.add(ghost);
    }
  }

  @Override
  public Player getPlayer() {
    return player;
  }

  @Override
  public void movePlayer(Direction direction, float deltaTime) {
    Rectangle newBounds = player.getNewBounds(direction, deltaTime);
    currdirection = direction;
    if (isLegalPos(newBounds)) {
      player.move(direction, deltaTime);
    }

    canInteract(getNearestGhost());
  }

  private Rectangle getLegalGhostPosition() {
    MapObject spawn = mapData.getGhostLegalPos().get("mainRect");
    Rectangle spawnRect;
    int offset = 100;
    if (spawn instanceof RectangleMapObject) {
      spawnRect = ((RectangleMapObject) spawn).getRectangle();
    } else {
      throw new IllegalArgumentException("this should DEFINITELY be a rectangle");
    }

    float x = Utility.getRandomNumber((int) spawnRect.x, (int) (spawnRect.x + spawnRect.getWidth() - offset));
    float y = Utility.getRandomNumber((int) spawnRect.y, (int) (spawnRect.y + spawnRect.getHeight() - offset));

    return new Rectangle(x, y, offset, offset); // Adjust ghost size if needed
  }

  private boolean isLegalPos(Rectangle entity) {
    MapObjects walls = mapData.getWalls();

    for (MapObject wall : walls) {
      if (wall instanceof RectangleMapObject) {
        Rectangle wallRect = ((RectangleMapObject) wall).getRectangle();
        if (entity.overlaps(wallRect)) {
          return false; // Collision detected
        }
      }
    }

    return true; // No collisions found
  }

  @Override
  public List<OverworldGhost> getActiveGhosts() {
    return activeGhosts;
  }

  @Override
  public List<PowerUp> getActivePowerUps() {
    return activePowerUps;
  }

  /**
   * Gets the nearest power-up to the player.
   *
   * @return The nearest PowerUp, or null if none exists
   */
  public PowerUp getNearestPowerUp() {
    PowerUp nearestPowerUp = null;
    float nearestDistance = Float.MAX_VALUE;

    for (PowerUp powerUp : activePowerUps) {
      float distance = calculateDistance(player.getX(), player.getY(), powerUp.getX(), powerUp.getY());
      if (distance < nearestDistance) {
        nearestDistance = distance;
        nearestPowerUp = powerUp;
      }
    }

    return nearestPowerUp;
  }

  @Override
  public boolean canInteract(OverworldEntity entity) {
    return player.getBounds().overlaps(entity.getBounds());
  }

  /**
   * Removes a power-up from the game world.
   *
   * @param powerUp The power-up to remove
   */
  public void removePowerUp(PowerUp powerUp) {
    audioController.playPowerupSound(); // 'eating' sound
    activePowerUps.remove(powerUp);
  }

  /**
   * Gets the nearest ghost to the player.
   *
   * @return The nearest OverworldGhost, or null if none exists
   */
  public OverworldGhost getNearestGhost() {
    OverworldGhost nearestGhost = null;
    float nearestDistance = Float.MAX_VALUE;

    for (OverworldGhost ghost : activeGhosts) {
      float distance = calculateDistance(player.getX(), player.getY(), ghost.getX(), ghost.getY());
      if (distance < nearestDistance) {
        nearestDistance = distance;
        nearestGhost = ghost;
      }
    }

    return nearestGhost;
  }

  private float calculateDistance(float x1, float y1, float x2, float y2) {
    return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }

  @Override
  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

  @Override
  public GameState getGameState() {
    return gameState;
  }

  @Override
  public void handlePause() {
    if (gameState != GameState.PAUSED) {
      this.gameStateBeforePause = gameState;
      setGameState(GameState.PAUSED);
    } else {
      setGameState(this.gameStateBeforePause);
    }
  }

  @Override
  public void handleMenuSelection() {
    if (gameState == GameState.WELCOME_SCREEN) {
      gameState = GameState.OVERWORLD;
    } else if (gameState == GameState.BATTLESCENE) {
      handleBattleEnded();
    }
  }

  @Override
  public void restartGoToMenu() {
    this.player = new Player(getStartPos().x, getStartPos().y);
    this.powerUpFactory = new PowerUpFactory();
    this.activeGhosts.clear();
    this.activePowerUps.clear();
    this.gameState = GameState.WELCOME_SCREEN;
    this.gameStateBeforePause = gameState;
    this.currdirection = Direction.DOWN;
    this.playerIsMoving = false;

    this.audioController.dispose();
    this.audioController = new AudioController();

    spawnInitialGhosts(10); // edit to change ghosts on map from start
  }

  @Override
  public void startBattle(OverworldGhost ghost) {
    if (gameState != GameState.BATTLESCENE) {
      gameState = GameState.BATTLESCENE;
      battleScene = new BattleScene(this, ghost);
      audioController.switchToBattleMusic();
    }
  }

  @Override
  public void handleBattleEnded() {
    handleBattleEnded(null);
  }

  @Override
  public void handleBattleEnded(OverworldGhost defeatedGhost) {
    resetBattleState();
    handleBattleOutcome();
    resetPlayerHealth();
    handleGhostRemoval(defeatedGhost);
    clearAttackResults();
    maybeSpawnNewGhost(defeatedGhost);
  }

  private void resetBattleState() {
    gameState = GameState.OVERWORLD;
    battleScene = null;
    audioController.switchToMainMusic();
  }

  private void resetPlayerHealth() {
    player.setMaxHealth(player.getMaxHealth() + (battlesWon * 5));
    player.setHealth(player.getMaxHealth());
  }

  private void handleGhostRemoval(OverworldGhost ghost) {
    if (ghost == null) {
      return;
    }
    if (player.getHealth() > 0) {
      activeGhosts.remove(ghost);
    }
  }

  private void clearAttackResults() {
    setLastPlayerAttackResult(new AttackResult("", 0, false, false));
    setLastGhostAttackResult(new AttackResult("", 0, false, false));
  }

  private void maybeSpawnNewGhost(OverworldGhost ghost) {
    if (ghost == null || ghost.getType() != GhostType.BOSS) {
      spawnNewGhost();
    }
  }

  /**
   * Handles the outcome of a battle.
   * Adjusts the player's health and checks if the game is over.
   */
  public void handleBattleOutcome() {
    if (player.getHealth() <= 0) {
      player.decreaseHearts();
      player.setHealth(player.getMaxHealth()); // Reset health
      checkGameOver();
    } else {
      // Increment battlesWon only if the player wins
      battlesWon++;
    }

    spawnNewPowerup();
  }

  private void spawnNewGhost() {
    OverworldGhost newGhost = ghostFactory.getNextGhost();
    Rectangle legalPos = getLegalGhostPosition();
    newGhost.setX(legalPos.x);
    newGhost.setY(legalPos.y);
    activeGhosts.add(newGhost);
  }

  /**
   * Spawns a new power-up at a random position near the player.
   * Only spawns a power-up 1/3 of the time.
   */
  public void spawnNewPowerup() {
    if (Utility.getRandomNumber(0, 2) != 0) {
      return; // 1/3 chance of wawning
    }

    float baseX = player.getX();
    float baseY = player.getY();

    float x = baseX + Utility.getRandomNumber(-50, 50);
    float y = baseY + Utility.getRandomNumber(-50, 50);

    Rectangle legalPos = getLegalPowerUpPosition(x, y, POWERUP_SIZE);
    PowerUp newPowerUp = powerUpFactory.getNextPowerUp(legalPos.x, legalPos.y, POWERUP_SIZE);
    activePowerUps.add(newPowerUp);

  }

  private Rectangle getLegalPowerUpPosition(float x, float y, int size) {
    Rectangle powerUpPos = new Rectangle(x, y, size, size);

    if (isLegalPos(powerUpPos)) {
      return powerUpPos;
    }

    return getLegalPowerUpPosition(x + 10, y + 10, size); // recursive with offset
  }

  private Rectangle getStartPos() {
    return new Rectangle(mapData.getStartPosition());
  }

  /**
   * Checks if the game is over.
   * If the player has no hearts left, the game state is set to GAME_OVER.
   */
  public void checkGameOver() {
    if (player.getHearts() <= 0) {
      setGameState(GameState.GAME_OVER);
    }
  }

  @Override
  public BattleScene getBattleScene() {
    return battleScene;
  }

  /**
   * Sets the last player attack result.
   *
   * @param result The last player attack result
   */
  public void setLastPlayerAttackResult(AttackResult result) {
    this.lastPlayerAttackResult = result;
  }

  /**
   * Sets the last ghost attack result.
   *
   * @param result The last ghost attack result
   */
  public void setLastGhostAttackResult(AttackResult result) {
    this.lastGhostAttackResult = result;
  }

  /**
   * Gets the last player attack result.
   *
   * @return The last player attack result
   */
  public AttackResult getLastPlayerAttackResult() {
    return lastPlayerAttackResult;
  }

  /**
   * Gets the last ghost attack result.
   *
   * @return The last ghost attack result
   */
  public AttackResult getLastGhostAttackResult() {
    return lastGhostAttackResult;
  }

  @Override
  public Direction getDirection() {
    return currdirection;
  }

  /**
   * Sets the current direction of the player.
   *
   * @param direction The direction to set
   */
  public void setDirection(Direction direction) {
    currdirection = direction;
  }

  @Override
  public boolean getPlayerIsMoving() {
    return playerIsMoving;
  }

  @Override
  public void setPlayerIsMoving(boolean isMoving) {
    playerIsMoving = isMoving;
  }

  @Override
  public AudioController getAudioController() {
    return audioController;
  }

  @Override
  public void dance() {
    setDirection(Direction.DANCE);
    danceCounter = 0;
  }

  @Override
  public int getDanceCounter() {
    return danceCounter;
  }

  @Override
  public void addDanceCounter() {
    danceCounter++;
  }

  /**
   * Disposes of all audio resources when the game is closed.
   */
  public void dispose() {
    audioController.dispose();
    activeGhosts.clear();
    activePowerUps.clear();
  }
}
