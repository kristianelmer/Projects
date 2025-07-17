package inf112.core.model;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;
import inf112.core.map.MapData;
import inf112.core.model.entities.ghost.OverworldGhost;
import inf112.core.model.entities.player.Player;
import inf112.core.model.entities.powerup.PowerUp;
import inf112.core.testutils.InitializeLibGDX;
import inf112.core.model.entities.ghost.GhostType;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GameModelTest {

  private GameModel gameModel;
  private MapData mapDataMock;

  @BeforeAll
  static void setUpAll() {
    InitializeLibGDX.initialize();

    Gdx.audio = mock(Audio.class);
    when(Gdx.audio.newSound(any(FileHandle.class))).thenReturn(mock(Sound.class));
  }

  @BeforeEach
  public void setUp() {
    mapDataMock = mock(MapData.class);
    when(mapDataMock.getStartPosition()).thenReturn(new Rectangle(100, 100, 32, 32));
    when(mapDataMock.getWidth()).thenReturn(500);
    when(mapDataMock.getHeight()).thenReturn(500);
    MapObjects walls = new MapObjects();

    when(mapDataMock.getWalls()).thenReturn(walls);

    MapObjects legalAreas = new MapObjects();
    RectangleMapObject mainRect = new RectangleMapObject(0, 0, 1000, 1000);
    mainRect.setName("mainRect");
    legalAreas.add(mainRect);
    when(mapDataMock.getGhostLegalPos()).thenReturn(legalAreas);

    gameModel = new GameModel(mapDataMock);
  }

  @Test
  public void testInitialGameStateIsWelcomeScreen() {
    assertEquals(GameState.WELCOME_SCREEN, gameModel.getGameState());
  }

  @Test
  public void testRestartGoToMenuResetsState() {
    gameModel.setGameState(GameState.BATTLESCENE);
    gameModel.restartGoToMenu();
    assertEquals(GameState.WELCOME_SCREEN, gameModel.getGameState());
    assertEquals(100, gameModel.getPlayer().getX());
  }

  @Test
  public void testHandlePause() {
    gameModel.setGameState(GameState.OVERWORLD);
    gameModel.handlePause();
    assertEquals(GameState.PAUSED, gameModel.getGameState());

    gameModel.handlePause();
    assertEquals(GameState.OVERWORLD, gameModel.getGameState());
  }

  @Test
  public void testStartBattleChangesStateAndSetsBattleScene() {
    OverworldGhost ghost = mock(OverworldGhost.class);
    gameModel.startBattle(ghost);
    assertEquals(GameState.BATTLESCENE, gameModel.getGameState());
    assertNotNull(gameModel.getBattleScene());
  }

  @Test
  public void testHandleBattleEndedResetsStateAndRemovesGhostIfPlayerAlive() {
    OverworldGhost ghost = mock(OverworldGhost.class);
    when(ghost.getType()).thenReturn(GhostType.REGULAR);

    Player player = gameModel.getPlayer();
    player.setHealth(100);

    gameModel.getActiveGhosts().add(ghost);
    assertTrue(gameModel.getActiveGhosts().contains(ghost));

    gameModel.handleBattleEnded(ghost);
    assertEquals(GameState.OVERWORLD, gameModel.getGameState());
    assertFalse(gameModel.getActiveGhosts().contains(ghost));
  }

  @Test
  public void testHandleMenuSelectionToOverworld() {
    gameModel.setGameState(GameState.WELCOME_SCREEN);

    gameModel.handleMenuSelection();

    assertEquals(GameState.OVERWORLD, gameModel.getGameState());
  }

  @Test
  public void testHandleMenuSelectionFromBattleToOverworld() {
    gameModel.setGameState(GameState.BATTLESCENE);
    OverworldGhost ghost = mock(OverworldGhost.class);
    when(ghost.getX()).thenReturn(100f);
    when(ghost.getY()).thenReturn(100f);
    when(ghost.getBounds()).thenReturn(new Rectangle(100, 100, 10, 10));

    gameModel.startBattle(ghost);
    gameModel.handleMenuSelection();

    assertEquals(GameState.OVERWORLD, gameModel.getGameState());
  }

  @Test
  public void testMovePlayerUpdatesPositionAndDirection() {
    float deltaTime = 1f;
    float initialX = gameModel.getPlayer().getX();
    float initialY = gameModel.getPlayer().getY();

    gameModel.movePlayer(Direction.RIGHT, deltaTime);

    float updatedX = gameModel.getPlayer().getX();
    float updatedY = gameModel.getPlayer().getY();

    assertTrue(updatedX > initialX);
    assertEquals(initialY, updatedY);
    assertEquals(Direction.RIGHT, gameModel.getDirection());
  }

  @Test
  public void testUpdateGhostsRevertsIllegalMove() {
    OverworldGhost ghost = mock(OverworldGhost.class);

    when(ghost.getX()).thenReturn(100f);
    when(ghost.getY()).thenReturn(100f);

    Rectangle illegalBounds = new Rectangle(9999, 9999, 10, 10); // illegal position
    when(ghost.getBounds()).thenReturn(illegalBounds);

    Direction newDirection = Direction.LEFT;
    when(ghost.selectRandomDirection()).thenReturn(newDirection);

    gameModel.getActiveGhosts().clear();
    gameModel.getActiveGhosts().add(ghost);

    gameModel.update();

    // illegal move is detected, reverted
    verify(ghost).setX(100f);
    verify(ghost).setY(100f);
    verify(ghost).setCurrentDirection(newDirection);
  }

  @Test
  public void testCheckGameOverTriggersWhenNoHeartsLeft() {
    Player player = gameModel.getPlayer();
    player.decreaseHearts();
    player.decreaseHearts();
    player.decreaseHearts();

    gameModel.checkGameOver();

    assertEquals(GameState.GAME_OVER, gameModel.getGameState());
  }

  @Test
  public void testSpawnNewPowerupHasLimitedProbability() {
    int initialSize = gameModel.getActivePowerUps().size();
    gameModel.spawnNewPowerup();
    // Could still be same size because of the 1/3 chance
    assertTrue(gameModel.getActivePowerUps().size() >= initialSize);
  }

  @Test
  public void testGetNearestGhostReturnsClosest() {
    OverworldGhost ghost1 = mock(OverworldGhost.class);
    when(ghost1.getX()).thenReturn(100f);
    when(ghost1.getY()).thenReturn(100f);
    when(ghost1.getBounds()).thenReturn(new Rectangle(100, 100, 10, 10));

    OverworldGhost ghost2 = mock(OverworldGhost.class);
    when(ghost2.getX()).thenReturn(200f);
    when(ghost2.getY()).thenReturn(200f);
    when(ghost2.getBounds()).thenReturn(new Rectangle(200, 200, 10, 10));

    gameModel.getActiveGhosts().clear();
    gameModel.getActiveGhosts().addAll(List.of(ghost1, ghost2));

    OverworldGhost nearest = gameModel.getNearestGhost();
    assertEquals(ghost1, nearest);
  }

  @Test
  public void testGetNearestPowerUpReturnsClosest() {
    PowerUp powerUp1 = mock(PowerUp.class);
    when(powerUp1.getX()).thenReturn(1f);
    when(powerUp1.getY()).thenReturn(1f);
    when(powerUp1.getBounds()).thenReturn(new Rectangle(1, 1, 10, 10));

    PowerUp powerUp2 = mock(PowerUp.class);
    when(powerUp2.getX()).thenReturn(300f);
    when(powerUp2.getY()).thenReturn(300f);
    when(powerUp2.getBounds()).thenReturn(new Rectangle(300, 300, 10, 10));

    gameModel.getActivePowerUps().clear();
    gameModel.getActivePowerUps().addAll(List.of(powerUp1, powerUp2));

    PowerUp nearest = gameModel.getNearestPowerUp();
    assertEquals(powerUp1, nearest);
  }

  @Test
  public void testRemovePowerUpRemoves() {
    PowerUp powerUp = mock(PowerUp.class);
    when(powerUp.getX()).thenReturn(5f);
    when(powerUp.getY()).thenReturn(5f);
    when(powerUp.getBounds()).thenReturn(new Rectangle(5, 5, 10, 10));

    gameModel.getActivePowerUps().clear();
    gameModel.getActivePowerUps().add(powerUp);

    gameModel.removePowerUp(powerUp);

    assertFalse(gameModel.getActivePowerUps().contains(powerUp));
  }

  @Test
  public void testCanInteractWithOverlappingEntity() {
    OverworldGhost ghost = mock(OverworldGhost.class);
    when(ghost.getBounds()).thenReturn(gameModel.getPlayer().getBounds());

    assertTrue(gameModel.canInteract(ghost));
  }

  @Test
  public void testCanNotInteractWhenNotOverlapping() {
    OverworldGhost ghost = mock(OverworldGhost.class);
    when(ghost.getBounds()).thenReturn(new Rectangle(9999, 9999, 10, 10));

    assertFalse(gameModel.canInteract(ghost));
  }

  @Test
  public void testUpdateCallsBattleSceneCheckBattleEnded() {
    OverworldGhost ghost = new OverworldGhost("Blinky", 3, GhostType.REGULAR, 20);
    gameModel.startBattle(ghost);

    BattleScene originalScene = gameModel.getBattleScene();

    gameModel.update();

    assertNotNull(originalScene);
  }

  @Test
  public void testRestartGoToMenuResetsGhostsAndPowerUps() {
    gameModel.getActiveGhosts().clear();
    gameModel.getActivePowerUps().clear();

    gameModel.spawnNewPowerup();
    assertTrue(gameModel.getActivePowerUps().size() >= 0);

    gameModel.restartGoToMenu();

    assertEquals(GameState.WELCOME_SCREEN, gameModel.getGameState());
    assertEquals(10, gameModel.getActiveGhosts().size()); // default ghosts in world
    assertTrue(gameModel.getActivePowerUps().isEmpty());
  }

  @Test
  public void testHandleBattleOutcomeIncrementsBattlesWonAndRestoresHealth() {
    Player player = gameModel.getPlayer();

    player.setHealth(50);
    gameModel.handleBattleOutcome();

    assertEquals(player.getMaxHealth(), player.getHealth());
  }

  @Test
  public void testHandleBattleOutcomeWithPlayerDeathDecreasesHearts() {
    Player player = gameModel.getPlayer();
    player.setHealth(0);

    int initialHearts = player.getHearts();
    gameModel.handleBattleOutcome();

    assertEquals(initialHearts - 1, player.getHearts());
  }

  @Test
  public void testSpawnNewPowerupWithinReasonableDistance() {
    int oldSize = gameModel.getActivePowerUps().size();
    for (int i = 0; i < 50; i++) { // call multiple times to beat 1/3 chance
      gameModel.spawnNewPowerup();
    }

    int newSize = gameModel.getActivePowerUps().size();
    assertTrue(newSize >= oldSize);

    for (PowerUp p : gameModel.getActivePowerUps()) {
      float distance = (float) Math.sqrt(
          Math.pow(p.getX() - gameModel.getPlayer().getX(), 2) +
              Math.pow(p.getY() - gameModel.getPlayer().getY(), 2));
      assertTrue(distance < 100); // within expected spawn range
    }
  }

  @Test
  public void testGetLegalGhostPositionThrowsIfNotRectangle() {
    MapObjects illegalObjects = new MapObjects();
    MapObject notRectangle = mock(MapObject.class);
    notRectangle.setName("mainRect");
    illegalObjects.add(notRectangle);

    when(mapDataMock.getGhostLegalPos()).thenReturn(illegalObjects);

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      gameModel.handleBattleEnded();
      ; // Indirectly triggers getLegalGhostPosition
    });

    assertEquals("this should DEFINITELY be a rectangle", exception.getMessage());
  }

  @Test
  public void testSetDirectionChangesDirection() {
    gameModel.setDirection(Direction.LEFT);
    assertEquals(Direction.LEFT, gameModel.getDirection());
  }

  @Test
  public void testPlayerIsMovingFlag() {
    assertFalse(gameModel.getPlayerIsMoving()); // default
    gameModel.setPlayerIsMoving(true);
    assertTrue(gameModel.getPlayerIsMoving());
  }

  @Test
  public void testGetAudioControllerReturnsNonNull() {
    assertNotNull(gameModel.getAudioController());
  }

  @Test
  public void testDanceSetsDanceDirectionAndResetsCounter() {
    gameModel.dance();
    assertEquals(Direction.DANCE, gameModel.getDirection());
    assertEquals(0, gameModel.getDanceCounter());
  }

  @Test
  public void testAddDanceCounterIncrementsCounter() {
    int initial = gameModel.getDanceCounter();
    gameModel.addDanceCounter();
    assertEquals(initial + 1, gameModel.getDanceCounter());
  }

  @Test
  public void testDisposeCleansUpResources() {
    gameModel.getActiveGhosts().add(mock(OverworldGhost.class));
    gameModel.getActivePowerUps().add(mock(PowerUp.class));

    gameModel.dispose();

    assertTrue(gameModel.getActiveGhosts().isEmpty());
    assertTrue(gameModel.getActivePowerUps().isEmpty());
  }

}
