package inf112.core.view.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.core.model.GameState;
import inf112.core.view.ViewableGameModel;

public class Renderer implements IRenderer {
  private final SpriteBatch batch;
  private final Viewport viewport;
  private final OrthogonalTiledMapRenderer mapRenderer;
  private final OverworldRenderer overworldRenderer;
  private final BattleRenderer battleRenderer;
  private final MenuRenderer menuRenderer;
  private final GameOverRenderer gameOverRenderer;
  private final PauseRenderer pauseRenderer;
  private final Stage stage;
  private final Stage fixedScreenStage;
  private final ViewableGameModel model;

  private GameState gameState;

  public Renderer(ViewableGameModel model, OrthogonalTiledMapRenderer mapRenderer) {
    this.model = model;
    this.mapRenderer = mapRenderer;
    this.gameState = model.getGameState();
    this.batch = new SpriteBatch();
    this.viewport = new ScreenViewport();
    this.stage = new Stage(viewport);
    this.fixedScreenStage = new Stage(new ScreenViewport());

    this.overworldRenderer = new OverworldRenderer(model, batch, viewport, fixedScreenStage, mapRenderer);
    this.battleRenderer = new BattleRenderer(model, batch, stage);
    this.menuRenderer = new MenuRenderer(model, batch, stage);
    this.gameOverRenderer = new GameOverRenderer(model, batch, stage);
    this.pauseRenderer = new PauseRenderer(model, batch, stage);

    stage.clear();
  }

  /**
   * Gets the fixed screen stage.
   *
   * @return The fixed screen stage
   */
  public Stage getFixedStage() {
    return this.fixedScreenStage;
  }

  @Override
  public void render() {
    this.gameState = model.getGameState();
    updateViewport(gameState);

    ScreenUtils.clear(Color.BLACK);
    batch.setProjectionMatrix(viewport.getCamera().combined);

    batch.begin();
    renderSceneByState(gameState);
    batch.end();

    renderStage();
  }

  private void renderSceneByState(GameState gameState) {
    switch (gameState) {
      case OVERWORLD:
        handleOverworldRender();
        break;
      case WELCOME_SCREEN:
        handleWelcomeScreenRender();
        break;
      case PAUSED:
        handlePausedRender();
        break;
      case GAME_OVER:
        handleGameOverRender();
        break;
      case BATTLESCENE:
        handleBattleSceneRender();
        break;
    }
  }

  private void handleOverworldRender() {
    menuRenderer.removeButtons();
    pauseRenderer.removeButtons();
    battleRenderer.removeButtons();
    battleRenderer.clearMessages();

    overworldRenderer.render();
    mapRenderer.setView((OrthographicCamera) viewport.getCamera());
  }

  private void handleWelcomeScreenRender() {
    pauseRenderer.removeButtons();
    gameOverRenderer.removeButtons();
    menuRenderer.render();
  }

  private void handlePausedRender() {
    battleRenderer.removeButtons();
    pauseRenderer.render();
  }

  private void handleGameOverRender() {
    pauseRenderer.removeButtons();
    menuRenderer.removeButtons();
    battleRenderer.removeButtons();
    gameOverRenderer.render();
  }

  private void handleBattleSceneRender() {
    pauseRenderer.removeButtons();
    battleRenderer.render();
  }

  private void renderStage() {
    stage.act();
    stage.draw();
  }

  private void updateViewport(GameState state) {
    if (state == GameState.OVERWORLD) {
      viewport.getCamera().position.set(model.getPlayer().getX(), model.getPlayer().getY(), 0);
    } else {
      viewport.getCamera().position.set(viewport.getWorldWidth() / 2f, viewport.getWorldHeight() / 2f, 0);
    }
    viewport.getCamera().update();
  }

  /**
   * Gets the main stage.
   *
   * @return The main stage
   */
  public Stage getStage() {
    return stage;
  }

  @Override
  public void dispose() {
    batch.dispose();
    overworldRenderer.dispose();
    battleRenderer.dispose();
    menuRenderer.dispose();
    gameOverRenderer.dispose();
  }

}
