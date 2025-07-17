package inf112.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import inf112.core.map.MapData;
import inf112.core.view.rendering.IRenderer;
import inf112.core.view.rendering.Renderer;

public class GameView implements IRenderer {
  private final Stage stage;
  private final Renderer renderer;
  private final OrthogonalTiledMapRenderer mapRenderer;

  public GameView(ViewableGameModel model, MapData mapData) {
    this.mapRenderer = mapData.createRenderer();
    this.renderer = new Renderer(model, mapRenderer);
    this.stage = renderer.getStage();

    InputMultiplexer multiplexer = new InputMultiplexer(stage, renderer.getFixedStage());
    Gdx.input.setInputProcessor(multiplexer);
  }

  @Override
  public void render() {
    renderer.render();
    stage.act();
    stage.draw();
  }

  @Override
  public void dispose() {
    renderer.dispose();
    stage.dispose();
    mapRenderer.dispose();
  }
}
