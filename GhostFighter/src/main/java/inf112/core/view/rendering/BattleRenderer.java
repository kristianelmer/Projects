package inf112.core.view.rendering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.GdxRuntimeException;

import inf112.core.view.ViewableGameModel;
import inf112.core.view.rendering.ui.AttackButtonClickListener;
import inf112.core.view.rendering.ui.BottomBar;
import inf112.core.view.rendering.ui.HealthBar;
import inf112.core.view.rendering.ui.TopBar;
import inf112.core.model.entities.attack.Attack;
import inf112.core.model.entities.attack.AttackRegistry;
import inf112.core.model.entities.attack.AttackResult;
import inf112.core.model.entities.ghost.OverworldGhost;
import inf112.core.utils.Utility;

public class BattleRenderer implements IRenderer {
  private final ViewableGameModel model;
  private final SpriteBatch batch;
  private final Texture battleBackground;
  private final Texture dialogBox;
  private final Texture playerImage;
  private final Map<String, Texture> ghostTextures;
  private final Texture defaultGhostTexture;
  private final Texture fightingButtonTexture;
  private final HealthBar playerHealthBar;
  private final HealthBar ghostHealthBar;
  private final Stage stage;
  private final ArrayList<ImageButton> attackButtons;
  private final BitmapFont font;

  private final TopBar topBar;
  private final BottomBar bottomBar;

  private String attackMessage;
  private String ghostAttackMessage;
  private float messageTickCounter = 0;
  private static final float MESSAGE_MAX_TICK = 360; // 4 seconds

  private AttackResult lastPlayerAttackResult;
  private AttackResult lastGhostAttackResult;

  private final Color lightGreen = new Color(0.75f, 0.98f, 0.83f, 1.0f); // RGB values for #BFF9D5
  private final Color darkBlue = new Color(0.0f, 0.13f, 0.38f, 1.0f); // #002060

  public BattleRenderer(ViewableGameModel model, SpriteBatch batch, Stage stage) {
    this.model = model;
    this.batch = batch;
    this.stage = stage;

    this.topBar = new TopBar(stage, model);
    this.bottomBar = new BottomBar();

    this.battleBackground = new Texture("images/screens/battle-screen.jpg");
    this.dialogBox = new Texture("images/ui/dialogBox.png");
    this.playerImage = new Texture("images/playerMovement/boy_still.png");
    this.ghostTextures = new HashMap<>();
    this.defaultGhostTexture = new Texture("images/ghosts/ghost.png");

    loadGhostTextures();

    this.fightingButtonTexture = new Texture("images/battle/attacks/fightingButton.png");

    this.playerHealthBar = new HealthBar(250, 120);
    this.ghostHealthBar = new HealthBar(720, 600);

    this.font = new BitmapFont();
    font.setColor(lightGreen);
    font.getData().setScale(2.0f);

    this.attackButtons = new ArrayList<>();

    this.lastPlayerAttackResult = null;
    this.lastGhostAttackResult = null;

    setupUI();

  }

  @Override
  public void render() {
    renderBackground();
    batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, 1024, 1024));

    renderPlayer();
    renderBattleGhost();
    renderAttackMessage();

    renderButtons();
    renderUI();
  }

  private void setupUI() {
    batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, 1024, 1024));

    float startX = 20;
    float startY = 50;
    float buttonSpacing = 112;

    for (Attack attack : AttackRegistry.ATTACKS) {
      String attackImagePath = "images/battle/attacks/" + attack.getName().toLowerCase() + ".png";

      if (!Gdx.files.internal(attackImagePath).exists()) {
        continue; // Skip if the image doesn't exist
      }

      Texture attackTexture = new Texture(Gdx.files.internal(attackImagePath));

      TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(attackTexture));
      TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(fightingButtonTexture));

      ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
      style.up = upDrawable; // Normal state
      style.down = downDrawable; // Pressed state

      ImageButton attackButton = new ImageButton(style);
      attackButton.setSize(144, 85);
      attackButton.setPosition(startX, startY);
      startY += buttonSpacing;

      attackButton.addListener(new AttackButtonClickListener(attack, model));
      attackButtons.add(attackButton);
      stage.addActor(attackButton);
    }
  }

  /**
   * Removes all attack buttons from the stage.
   */
  public void removeButtons() {
    for (ImageButton button : attackButtons) {
      if (button.hasParent())
        button.remove();
    }
  }

  private void renderBackground() {
    batch.draw(battleBackground, 0, 0, 960, 640);
    batch.draw(dialogBox, Math.round(960 * 0.55), Math.round(640 * 0.1), Math.round(dialogBox.getWidth() * 1.4),
        dialogBox.getHeight());
  }

  private void renderUI() {
    topBar.render(batch);
    bottomBar.render(batch);
  }

  private void renderButtons() {
    for (ImageButton attackButton : attackButtons) {
      if (!attackButton.hasParent()) {
        stage.addActor(attackButton);
      }
    }
  }

  private void renderPlayer() {
    batch.draw(playerImage, 300, 200, 100, 135);
    String playerHealthText = "Player Health: " + model.getPlayer().getHealth() + "/"
        + model.getPlayer().getMaxHealth();
    font.setColor(lightGreen);
    font.draw(batch, playerHealthText, 250, 175);
    drawPlayerHealth();
  }

  private void renderBattleGhost() {
    Texture ghostTexture = getGhostTexture(model.getBattleScene().getBattleGhost().getName());
    batch.draw(ghostTexture, 700, 600, 250, 280);

    String ghostHealthText = model.getBattleScene().getBattleGhost().getName() + " Health: " +
        model.getBattleScene().getBattleGhost().getHealth() + "/" +
        model.getBattleScene().getBattleGhost().getMaxHealth();

    font.setColor(lightGreen);
    font.draw(batch, ghostHealthText, 720, 650);
    drawGhostHealth();
  }

  private void drawPlayerHealth() {
    playerHealthBar.draw(batch, model.getPlayer().getHealth(), model.getPlayer().getMaxHealth());
  }

  private void drawGhostHealth() {
    ghostHealthBar.draw(batch, model.getBattleScene().getBattleGhost().getHealth(),
        model.getBattleScene().getBattleGhost().getMaxHealth());
  }

  private String generateAttackMessage(String attacker, AttackResult attackResult) {
    if (attackResult == null) {
      return "";
    }

    if (attackResult.wasHit()) {
      return attacker + " used " + attackResult.getAttackName() + (attackResult.wasCritical() ? " (CRITICAL!) " : " ")
          + "and dealt\n" + attackResult.getDamage() + " damage!";
    } else {
      return "MISS! " + attacker + " dealt 0 damage.";
    }
  }

  private void updateAttackMessageIfNeeded() {
    AttackResult newPlayerAttack = model.getLastPlayerAttackResult();
    AttackResult newGhostAttack = model.getLastGhostAttackResult();

    if (newPlayerAttack != lastPlayerAttackResult || newGhostAttack != lastGhostAttackResult) {
      lastPlayerAttackResult = newPlayerAttack;
      lastGhostAttackResult = newGhostAttack;
      attackMessage = generateAttackMessage("Player", newPlayerAttack);
      ghostAttackMessage = generateAttackMessage(model.getBattleScene().getBattleGhost().getName(), newGhostAttack);
      messageTickCounter = 0; // Reset counter so new messages are visible
    }
  }

  private void renderAttackMessage() {
    updateAttackMessageIfNeeded();

    font.setColor(darkBlue);
    if (messageTickCounter <= MESSAGE_MAX_TICK) {
      messageTickCounter++;
      if (attackMessage != null && 0 < messageTickCounter && messageTickCounter < 170) {
        font.draw(batch, attackMessage, Math.round(960 * 0.63), Math.round(640 * 0.4));
      }
      if (ghostAttackMessage != null && 190 < messageTickCounter && messageTickCounter < MESSAGE_MAX_TICK) {
        font.draw(batch, ghostAttackMessage, Math.round(960 * 0.63), Math.round(640 * 0.35));

      }
    }
  }

  /**
   * Clears the attack messages.
   */
  public void clearMessages() {
    attackMessage = null;
    ghostAttackMessage = null;
    messageTickCounter = 0;
  }

  private void loadGhostTextures() {
    List<OverworldGhost> allGhosts = Utility.loadGhostsFromCSV("data/ghosts.csv");

    for (OverworldGhost ghost : allGhosts) {
      String texturePath = "images/ghosts/" + ghost.getName().toLowerCase() + ".png";
      try {
        Texture texture = new Texture(texturePath);
        ghostTextures.put(ghost.getName(), texture);
      } catch (GdxRuntimeException e) {
        Gdx.app.error("Texture Loading", "Could not load texture for ghost: " + ghost.getName());
      }
    }
  }

  private Texture getGhostTexture(String ghostName) {
    return ghostTextures.getOrDefault(ghostName, defaultGhostTexture);
  }

  @Override
  public void dispose() {
    battleBackground.dispose();
    dialogBox.dispose();
    playerImage.dispose();
    fightingButtonTexture.dispose();
    font.dispose();

    for (Texture texture : ghostTextures.values()) {
      texture.dispose();
    }
    defaultGhostTexture.dispose();

    playerHealthBar.dispose();
    ghostHealthBar.dispose();
    topBar.dispose();
  }
}
