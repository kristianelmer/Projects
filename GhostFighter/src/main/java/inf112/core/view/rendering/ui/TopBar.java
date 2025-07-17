package inf112.core.view.rendering.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import inf112.core.model.entities.player.Player;
import inf112.core.view.ViewableGameModel;
import inf112.core.view.rendering.GameButton;

public class TopBar extends AbstractUIBar {

  private final Texture heartTexture;
  private final Texture pauseButtonTexture;
  private final Texture muteButtonTexture;
  private final Stage fixedStage;
  private final ViewableGameModel model;

  public TopBar(Stage fixedStage, ViewableGameModel model) {
    super("images/ui/UIBarBackground.png");
    this.fixedStage = fixedStage;
    this.model = model;

    this.heartTexture = new Texture("images/ui/topbarHeart.png");
    this.pauseButtonTexture = new Texture("images/menuButtons/pause.png");
    this.muteButtonTexture = new Texture("images/menuButtons/mute.png");

    setupButtons();
  }

  private void setupButtons() {
    int buttonWidth = 150;
    int buttonHeight = (int) (TOPBAR_HEIGHT - 4);
    int buttonY = (int) (Gdx.graphics.getHeight() - TOPBAR_HEIGHT + 3);

    ClickListener pauseListener = new GeneralClickListener(model, "pause");
    int pauseButtonX = Gdx.graphics.getWidth() - buttonWidth * 2 - 6;
    GameButton pauseButton = new GameButton(pauseButtonTexture, pauseListener, pauseButtonX, buttonY, buttonWidth,
        buttonHeight);
    fixedStage.addActor(pauseButton.getImageButton());

    ClickListener muteListener = new GeneralClickListener(model, "mute");
    int muteButtonX = Gdx.graphics.getWidth() - buttonWidth - 3;
    GameButton muteButton = new GameButton(muteButtonTexture, muteListener, muteButtonX, buttonY, buttonWidth,
        buttonHeight);
    fixedStage.addActor(muteButton.getImageButton());
  }

  private void renderHearts(SpriteBatch batch, Player player) {
    int hearts = player.getHearts();
    float x = 25;
    float y = Gdx.graphics.getHeight() - TOPBAR_HEIGHT;
    float w = 35;
    float h = 30;

    for (int i = 0; i < hearts; i++) {
      batch.draw(heartTexture, x + i * w, y, w, h);
    }
  }
  
  @Override
  public void renderContents(SpriteBatch batch) {
    renderHearts(batch, model.getPlayer());
    fixedStage.act();
    fixedStage.draw();
  }

  @Override
  public void renderContents(SpriteBatch batch, OrthographicCamera camera, Player player) {
    renderHearts(batch, player);
    fixedStage.act();
    fixedStage.draw();
  }


  @Override
  public float getYPosition() {
    return Gdx.graphics.getHeight() - TOPBAR_HEIGHT;
  }

  @Override
  public void dispose() {
    super.dispose();
    heartTexture.dispose();
    pauseButtonTexture.dispose();
    muteButtonTexture.dispose();
  }
}
