package inf112.core.view.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import inf112.core.view.ViewableGameModel;
import inf112.core.view.rendering.ui.GeneralClickListener;

public class PauseRenderer implements IRenderer {
  private final ViewableGameModel model;
  private final SpriteBatch batch;
  private final Stage stage;

  private final Texture pauseScreen;
  private final Texture resumeTexture;
  private final Texture goToMenuTexture;

  private ImageButton resumeButton;
  private ImageButton goToMenuButton;

  public PauseRenderer(ViewableGameModel model, SpriteBatch batch, Stage stage) {
    this.model = model;
    this.batch = batch;
    this.stage = stage;

    this.pauseScreen = new Texture("images/screens/pause-screen.jpg");
    this.resumeTexture = new Texture("images/menuButtons/resume.png");
    this.goToMenuTexture = new Texture("images/menuButtons/go-to-menu.png");

    setupButtons();
  }

  private void setupButtons() {
    int middleX = Gdx.graphics.getWidth() / 2;
    int buttonY = Gdx.graphics.getHeight() / 2 - 110;

    int buttonWidth = 200;
    int buttonHeight = 70;

    int shiftLeft = 30;

    ClickListener clickListener = new GeneralClickListener(model, "resume");
    GameButton gameButton = new GameButton(resumeTexture, clickListener, middleX - buttonWidth - 3, buttonY,
        buttonWidth - shiftLeft,
        buttonHeight);
    this.resumeButton = gameButton.getImageButton();

    clickListener = new GeneralClickListener(model, "menu");
    gameButton = new GameButton(goToMenuTexture, clickListener, middleX - shiftLeft + 3, buttonY,
        buttonWidth + shiftLeft, buttonHeight);
    this.goToMenuButton = gameButton.getImageButton();
  }

  /**
   * Removes the resume and go-to-menu buttons from the stage.
   */
  public void removeButtons() {
    if (resumeButton.hasParent()) {
      resumeButton.remove();
      goToMenuButton.remove();
    }
  }

  @Override
  public void render() {
    batch.draw(pauseScreen, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    stage.addActor(resumeButton);
    stage.addActor(goToMenuButton);
  }

  @Override
  public void dispose() {
    pauseScreen.dispose();
    resumeTexture.dispose();
    goToMenuTexture.dispose();
  }
}
