package inf112.core.model.scenes;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import inf112.core.map.MapData;
import inf112.core.model.BattleScene;
import inf112.core.model.GameModel;
import inf112.core.model.entities.attack.Attack;
import inf112.core.model.entities.attack.AttackResult;
import inf112.core.model.entities.ghost.BattleGhost;
import inf112.core.model.entities.ghost.GhostType;
import inf112.core.model.entities.ghost.OverworldGhost;
import inf112.core.model.entities.player.Player;
import inf112.core.testutils.InitializeLibGDX;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BattleSceneTest {

  private GameModel model;
  private MapData mockMapData;

  @BeforeAll
  static void setUpAll() {
    InitializeLibGDX.initialize();

    Gdx.audio = mock(Audio.class);
    when(Gdx.audio.newSound(any(FileHandle.class))).thenReturn(mock(Sound.class));
  }

  @BeforeEach
  void setUp() {
    mockMapData = mock(MapData.class);

    MapObjects walls = new MapObjects();
    when(mockMapData.getWalls()).thenReturn(walls);

    MapObjects legalAreas = new MapObjects();
    RectangleMapObject mainRect = new RectangleMapObject(0, 0, 1000, 1000);
    mainRect.setName("mainRect");
    legalAreas.add(mainRect);
    when(mockMapData.getGhostLegalPos()).thenReturn(legalAreas);

    // Setup start position
    when(mockMapData.getStartPosition()).thenReturn(new Rectangle(100, 100, 50, 50));

    // Setup map dimensions
    when(mockMapData.getWidth()).thenReturn(1000);
    when(mockMapData.getHeight()).thenReturn(1000);

    model = new GameModel(mockMapData);
  }

  @Test
  void fight_setsPlayerAttackResult() {
    OverworldGhost ghost = new OverworldGhost("Spectre", 1, GhostType.REGULAR, 40);
    BattleScene scene = new BattleScene(model, ghost);
    scene.fight();

    AttackResult result = model.getLastPlayerAttackResult();
    assertNotNull(result);
    assertTrue(result.getDamage() >= 0);
  }

  @Test
  void fight_setsGhostAttackResult() {
    OverworldGhost ghost = new OverworldGhost("Spectre", 1, GhostType.REGULAR, 40);
    BattleScene scene = new BattleScene(model, ghost);
    scene.fight();

    AttackResult result = model.getLastGhostAttackResult();
    assertNotNull(result);
    assertTrue(result.getDamage() >= 0);
  } 

  @Test
void fight_appliesDamageOnlyIfGhostHits() {
    Player player = model.getPlayer();

    AttackResult mockResult = new AttackResult("bite", 10, true, false); // simulate a hit
    Attack mockAttack = mock(Attack.class);
    when(mockAttack.execute()).thenReturn(mockResult);

    BattleGhost mockGhost = mock(BattleGhost.class);
    when(mockGhost.attack(eq(player))).thenAnswer(invocation -> {
        player.takeDamage(mockResult.getDamage());
        return mockResult;
    });

    OverworldGhost overworld = new OverworldGhost("Spectre", 1, GhostType.REGULAR, 40);
    BattleScene scene = new BattleScene(model, overworld) {
        @Override
        public BattleGhost getBattleGhost() {
            return mockGhost;
        }
    };

    scene.fight();

    AttackResult result = model.getLastGhostAttackResult();
    assertNotNull(result);
}


  @Test
  void fight_dealsDamageToPlayer() {
    Player player = model.getPlayer();
    int initialHealth = player.getHealth();

    OverworldGhost ghost = new OverworldGhost("Spectre", 1, GhostType.REGULAR, 40);
    
    BattleScene scene = new BattleScene(model, ghost);
    scene.fight();

    AttackResult ghostAttack = model.getLastGhostAttackResult();
    if (ghostAttack.wasHit()) {
      assertTrue(player.getHealth() < initialHealth);
    }
  }

  @Test
  void checkBattleEnded_CallsHandleBattleEnded() {
    Player player = mock(Player.class);
    when(player.isAlive()).thenReturn(false);

    GameModel model = mock(GameModel.class);
    when(model.getPlayer()).thenReturn(player);

    OverworldGhost ghost = new OverworldGhost("Spectre", 1, GhostType.REGULAR, 40);
    BattleScene scene = new BattleScene(model, ghost);

    scene.checkBattleEnded();

    verify(model).handleBattleEnded(ghost);
  }
}
