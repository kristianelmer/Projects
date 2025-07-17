package inf112.core.view.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import inf112.core.model.GameState;
import inf112.core.view.ViewableGameModel;
import inf112.core.view.rendering.ui.MenuButtonClickListener;
import inf112.core.view.rendering.ui.MenuButtonType;

public class MenuRenderer implements IRenderer {
  private final ViewableGameModel model;
  private final SpriteBatch batch;
  private final Stage stage;
  private final BitmapFont font;

  private final Texture menuBackground;
  private final Texture pauseScreen;
  private final Texture howToScreen;
  private final Texture optionsScreen;
  private final Texture creditsScreen;

  private final Texture playTexture;
  private final Texture howToTexture;
  private final Texture optionsTexture;
  private final Texture creditsTexture;
  private final Texture quitTexture;
  private final Texture backTexture;

  public Boolean howToPlayVisible = false;
  public Boolean optionsVisible = false;
  public Boolean creditsVisible = false;

  private ImageButton startGameButton;
  private ImageButton howToPlayGameButton;
  private ImageButton optionsButton;
  private ImageButton creditsButton;
  private ImageButton quitButton;
  private ImageButton backButton;

  public MenuRenderer(ViewableGameModel model, SpriteBatch batch, Stage stage) {
    this.model = model;
    this.batch = batch;
    this.stage = stage;
    this.menuBackground = new Texture("images/screens/menu-screen.png");
    this.howToScreen = new Texture("images/screens/how-to-screen.jpg");
    this.optionsScreen = new Texture("images/screens/options-screen.jpg");
    this.creditsScreen = new Texture("images/screens/credits-screen.jpg");
    this.pauseScreen = new Texture("images/screens/pause-screen.jpg");

    this.playTexture = new Texture("images/menuButtons/play.png");
    this.howToTexture = new Texture("images/menuButtons/how-to-play.png");
    this.optionsTexture = new Texture("images/menuButtons/options.png");
    this.creditsTexture = new Texture("images/menuButtons/credits.png");
    this.quitTexture = new Texture("images/menuButtons/quit.png");
    this.backTexture = new Texture("images/menuButtons/back.png");

    this.font = new BitmapFont();
    font.getData().setScale(2.0f);

    setupButtons();
  }

  /**
   * Removes all buttons from the stage.
   */
  public void removeButtons() {
    if (startGameButton.hasParent())
      removeWelcomeButtons();
    if (backButton.hasParent())
      backButton.remove();
  }

  private void setupButtons() {
    int buttonY = 380;
    int buttonX = 60;
    int buttonHeight = 50;
    int padding = 5;
    int buttonWidth = 164;
    int buttonDistance = buttonWidth + padding;

    ClickListener clickListener = new MenuButtonClickListener(MenuButtonType.PLAY, model, this);

    GameButton gameButton = new GameButton(playTexture, clickListener, buttonX, buttonY, buttonWidth, buttonHeight);
    startGameButton = gameButton.getImageButton();

    clickListener = new MenuButtonClickListener(MenuButtonType.HOW_TO, model, this);
    gameButton = new GameButton(howToTexture, clickListener, buttonX + buttonDistance, buttonY, buttonWidth,
        buttonHeight);
    howToPlayGameButton = gameButton.getImageButton();

    clickListener = new MenuButtonClickListener(MenuButtonType.OPTIONS, model, this);
    gameButton = new GameButton(optionsTexture, clickListener, buttonX + 2 * buttonDistance, buttonY, buttonWidth,
        buttonHeight);
    optionsButton = gameButton.getImageButton();

    clickListener = new MenuButtonClickListener(MenuButtonType.CREDITS, model, this);
    gameButton = new GameButton(creditsTexture, clickListener, buttonX + 3 * buttonDistance, buttonY, buttonWidth,
        buttonHeight);
    creditsButton = gameButton.getImageButton();

    clickListener = new MenuButtonClickListener(MenuButtonType.QUIT, model, this);
    gameButton = new GameButton(quitTexture, clickListener, buttonX + 4 * buttonDistance, buttonY, buttonWidth,
        buttonHeight);
    quitButton = gameButton.getImageButton();

    clickListener = new MenuButtonClickListener(MenuButtonType.BACK, model, this);
    gameButton = new GameButton(backTexture, clickListener, 755, 515, buttonWidth, 65);
    backButton = gameButton.getImageButton();

  }

  private void addWelcomeButtonsToStage() {
    stage.addActor(startGameButton);
    stage.addActor(howToPlayGameButton);
    stage.addActor(optionsButton);
    stage.addActor(creditsButton);
    stage.addActor(quitButton);
  }

  private void removeWelcomeButtons() {
    startGameButton.remove();
    howToPlayGameButton.remove();
    optionsButton.remove();
    creditsButton.remove();
    quitButton.remove();
  }

  @Override
  public void render() {
    batch.draw(menuBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    if (model.getGameState() == GameState.WELCOME_SCREEN) {
      if (!(howToPlayVisible || optionsVisible || creditsVisible)) {
        if (!startGameButton.hasParent()) {
          backButton.remove();
          addWelcomeButtonsToStage();
        }
      } else if (howToPlayVisible) {
        removeWelcomeButtons();
        batch.draw(howToScreen, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backButton);
      } else if (optionsVisible) {
        removeWelcomeButtons();
        batch.draw(optionsScreen, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backButton);
      } else {
        removeWelcomeButtons();
        batch.draw(creditsScreen, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backButton);
      }
    } else {
      startGameButton.remove();
      howToPlayGameButton.remove();
      optionsButton.remove();
      creditsButton.remove();
      quitButton.remove();
    }
  }

  @Override
  public void dispose() {
    menuBackground.dispose();
    pauseScreen.dispose();
    font.dispose();
    playTexture.dispose();
    howToTexture.dispose();
    optionsTexture.dispose();
    creditsTexture.dispose();
    quitTexture.dispose();
    backTexture.dispose();
  }
}
