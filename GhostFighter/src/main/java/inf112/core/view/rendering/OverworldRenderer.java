package inf112.core.view.rendering;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.core.model.Direction;
import inf112.core.model.entities.ghost.OverworldGhost;
import inf112.core.model.entities.player.Player;
import inf112.core.model.entities.powerup.PowerUp;
import inf112.core.utils.Utility;
import inf112.core.view.ViewableGameModel;
import inf112.core.view.rendering.ui.BottomBar;
import inf112.core.view.rendering.ui.TopBar;

class OverworldRenderer implements IRenderer {
  private final ViewableGameModel model;
  private final SpriteBatch batch;
  private final OrthogonalTiledMapRenderer mapRenderer;
  private final Map<String, Texture> ghostTextures;
  private final Texture defaultGhostTexture;
  private final Texture powerUpImage;
  private final BitmapFont font;
  private final Viewport viewport;

  private final TopBar topBar;
  private final BottomBar bottomBar;

  private final Texture up1;
  private final Texture up2;
  private final Texture down1;
  private final Texture down2;
  private final Texture left1;
  private final Texture left2;
  private final Texture right1;
  private final Texture right2;
  private final Texture still;
  private final Texture dance1;
  private final Texture dance2;

  private Texture playerImage;

  private int stepCounter;
  private int spriteNum;

  public OverworldRenderer(ViewableGameModel model, SpriteBatch batch, Viewport viewport, Stage fixedStage,
      OrthogonalTiledMapRenderer mapRenderer) {
    this.model = model;
    this.batch = batch;
    this.viewport = viewport;
    this.mapRenderer = mapRenderer;

    this.topBar = new TopBar(fixedStage, model);
    this.bottomBar = new BottomBar();

    this.playerImage = new Texture("images/playerMovement/boy_still.png");
    this.ghostTextures = new HashMap<>();
    this.defaultGhostTexture = new Texture("images/ghosts/ghost.png");

    loadGhostTextures();

    this.stepCounter = 0;
    this.spriteNum = 1;

    this.up1 = new Texture("images/playerMovement/boy_up_1.png");
    this.up2 = new Texture("images/playerMovement/boy_up_2.png");
    this.down1 = new Texture("images/playerMovement/boy_down_1.png");
    this.down2 = new Texture("images/playerMovement/boy_down_2.png");
    this.left1 = new Texture("images/playerMovement/boy_left_1.png");
    this.left2 = new Texture("images/playerMovement/boy_left_2.png");
    this.right1 = new Texture("images/playerMovement/boy_right_1.png");
    this.right2 = new Texture("images/playerMovement/boy_right_2.png");
    this.still = new Texture("images/playerMovement/boy_still.png");
    this.dance1 = new Texture("images/playerMovement/boy_dance1.png");
    this.dance2 = new Texture("images/playerMovement/boy_dance2.png");

    this.powerUpImage = new Texture("images/ui/overworldHeart.png");

    this.font = new BitmapFont();
    this.font.getData().setScale(2.5f);
    this.font.setColor(Color.BLUE);

  }

  @Override
  public void render() {
    Player player = model.getPlayer();

    OrthographicCamera camera = (OrthographicCamera) viewport.getCamera();

    mapRenderer.render();

    updatePlayer();
    renderPowerUps();
    renderGhost();
    interactionPrompts();
    renderPlayer(player);
    renderUI(camera, player);
  }

  private void renderUI(OrthographicCamera camera, Player player) {
    topBar.render(batch, camera, player);
    bottomBar.render(batch, camera, player);
  }

  private void renderPowerUps() {
    List<PowerUp> activePowerUps = model.getActivePowerUps();
    for (PowerUp powerUp : activePowerUps) {
      batch.draw(powerUpImage, powerUp.getX(), powerUp.getY(), powerUp.getWidth(), powerUp.getHeight());
    }
  }

  private void renderPlayer(Player player) {
    batch.draw(playerImage, player.getX(), player.getY(), player.getWidth(), player.getHeight());

  }

  private void loadGhostTextures() {
    List<OverworldGhost> allGhosts = Utility.loadGhostsFromCSV("data/ghosts.csv");

    for (OverworldGhost ghost : allGhosts) {
      String texturePath = "images/ghosts/" + ghost.getName().toLowerCase() + ".png";
      Texture texture = new Texture(texturePath);
      ghostTextures.put(ghost.getName(), texture);
    }
  }

  private void renderGhost() {
    List<OverworldGhost> activeGhosts = model.getActiveGhosts();
    for (OverworldGhost ghost : activeGhosts) {
      Texture texture = ghostTextures.getOrDefault(ghost.getName(), defaultGhostTexture);
      batch.draw(texture,
          ghost.getX(),
          ghost.getY(),
          ghost.getWidth(),
          ghost.getHeight());
    }
  }

  private void interactionPrompts() {
    OverworldGhost nearestGhost = model.getNearestGhost();
    if (nearestGhost != null && model.canInteract(nearestGhost)) {
      font.getData().setScale(1.8f);

      textWithOutline(nearestGhost.getX() - 150, nearestGhost.getY() - 15,
          "Press SPACE to interact with " + nearestGhost.getName());
    }

    PowerUp nearestPowerUp = model.getNearestPowerUp();
    if (nearestPowerUp != null && model.canInteract(nearestPowerUp)) {
      font.getData().setScale(2.0f);
      font.draw(batch, "Press SPACE to gain 1 heart",
          nearestPowerUp.getX() - 50, nearestPowerUp.getY() - 15);
    }
  }

  private void textWithOutline(float x, float y, String text) {
    Color outlineColor = new Color(0.2f, 0.2f, 0.6f, 1.0f); // darker blue/indigo
    Color middleColor = new Color(0.6f, 0.6f, 1.0f, 0.3f); // fillcolor but less opaque
    Color fillColor = new Color(0.6f, 0.6f, 1.0f, 1.0f); // lighter blue/indigo

    font.setColor(outlineColor);
    font.draw(batch, text, x - 3, y);
    font.draw(batch, text, x + 3, y);
    font.draw(batch, text, x, y - 3);
    font.draw(batch, text, x, y + 3);

    font.setColor(middleColor);
    font.draw(batch, text, x - 2, y);
    font.draw(batch, text, x + 2, y);
    font.draw(batch, text, x, y - 2);
    font.draw(batch, text, x, y + 2);

    font.setColor(fillColor);
    font.draw(batch, text, x - 1, y);
    font.draw(batch, text, x + 1, y);
    font.draw(batch, text, x, y - 1);
    font.draw(batch, text, x, y + 1);
    font.draw(batch, text, x, y);
  }

  /**
   * Updates the player's sprite animation based on movement and direction.
   * Handles walking animations by cycling between two sprites for each direction
   * (up, down, left, right).
   * Also handles a special dance animation.
   * The animation speed is controlled by a step counter that updates when the
   * player is moving.
   */
  public void updatePlayer() {
    if (model.getPlayerIsMoving()) {
      incrementStepCounter();
    }

    updateSpriteNum();

    if (model.getDirection() == Direction.DANCE) {
      handleDance();
    } else if (!model.getPlayerIsMoving()) {
      setStillImageIfNotDancing();
    } else {
      updateMovementImage();
    }
  }

  private void incrementStepCounter() {
    stepCounter++;
  }

  private void updateSpriteNum() {
    if (stepCounter > 12) {
      stepCounter = 0;
      spriteNum = (spriteNum == 1) ? 2 : 1; // if 1 then 2 else 1
    }
  }

  private void updateMovementImage() {
    switch (model.getDirection()) {
      case UP:
        playerImage = (spriteNum == 1) ? up1 : up2;
        break;
      case DOWN:
        playerImage = (spriteNum == 1) ? down1 : down2;
        break;
      case LEFT:
        playerImage = (spriteNum == 1) ? left1 : left2;
        break;
      case RIGHT:
        playerImage = (spriteNum == 1) ? right1 : right2;
        break;
      default:
        break;
    }
  }

  private void handleDance() {
    incrementStepCounter();
    model.addDanceCounter();
    playerImage = (spriteNum == 1) ? dance1 : dance2;
    if (model.getDanceCounter() > 120) {
      model.setDirection(Direction.DOWN);
    }
  }

  private void setStillImageIfNotDancing() {
    if (model.getDirection() != Direction.DANCE) {
      playerImage = still;
    }
  }

  @Override
  public void dispose() {
    playerImage.dispose();
    up1.dispose();
    up2.dispose();
    down1.dispose();
    down2.dispose();
    left1.dispose();
    left2.dispose();
    right1.dispose();
    right2.dispose();
    still.dispose();
    dance1.dispose();
    dance2.dispose();

    for (Texture texture : ghostTextures.values()) {
      texture.dispose();
    }

    defaultGhostTexture.dispose();
    powerUpImage.dispose();
    font.dispose();
    topBar.dispose();
  }
}
