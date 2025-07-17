package inf112.core.view.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import inf112.core.view.ViewableGameModel;
import inf112.core.view.rendering.ui.GeneralClickListener;

public class GameOverRenderer implements IRenderer {
  private final ViewableGameModel model;
  private final SpriteBatch batch;
  private final Stage stage;

  private final Texture gameOverScreen;
  private final Texture menuButtonTexture;
  private final Texture quitbuttonTexture;
  private ImageButton menuButton;
  private ImageButton quitButton;

  public GameOverRenderer(ViewableGameModel model, SpriteBatch batch, Stage stage) {
    this.model = model;
    this.batch = batch;
    this.stage = stage;
    this.gameOverScreen = new Texture("images/screens/game-over-screen.jpg");
    this.menuButtonTexture = new Texture("images/menuButtons/menu.png");
    this.quitbuttonTexture = new Texture("images/menuButtons/exit.png");

    setupButtons();
  }

  /**
   * Removes the menu and quit buttons from the stage.
   */
  public void removeButtons() {
    if (menuButton.hasParent()) {
      menuButton.remove();
      quitButton.remove();
    }
  }

  private void setupButtons() {
    int buttonY = Gdx.graphics.getHeight() / 3;
    int buttonWidth = 175;
    int buttonHeight = 70;

    ClickListener clickListener = new GeneralClickListener(model, "menu");
    GameButton gameButton = new GameButton(menuButtonTexture, clickListener, Gdx.graphics.getWidth() / 2 - buttonWidth - 3, buttonY,
        buttonWidth, buttonHeight);
    menuButton = gameButton.getImageButton();

    clickListener = new GeneralClickListener(model, "quit");
    gameButton = new GameButton(quitbuttonTexture, clickListener, Gdx.graphics.getWidth() / 2 + 3, buttonY, buttonWidth, buttonHeight);
    quitButton = gameButton.getImageButton();
  }

  @Override
  public void render() {
    batch.draw(gameOverScreen, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    stage.addActor(menuButton);
    stage.addActor(quitButton);
  }

  @Override
  public void dispose() {
    gameOverScreen.dispose();
    menuButtonTexture.dispose();
    quitbuttonTexture.dispose();
  }
}
