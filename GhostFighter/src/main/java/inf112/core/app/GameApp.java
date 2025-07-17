package inf112.core.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import inf112.core.controller.GameController;
import inf112.core.map.MapData;
import inf112.core.model.GameModel;
import inf112.core.view.GameView;

public class GameApp implements ApplicationListener {
  private GameView view;
  private GameModel model;
  private GameController controller;
  private MapData mapData;

  @Override
  public void create() {
    TiledMap tiledMap = new TmxMapLoader().load("map/map.tmx");
    this.mapData = new MapData(tiledMap);

    this.model = new GameModel(mapData);
    this.view = new GameView(model, mapData);
    this.controller = new GameController(model);
  }

  @Override
  public void resize(int width, int height) {
    model.update();
  }

  @Override
  public void render() {
    float deltaTime = Gdx.graphics.getDeltaTime();
    controller.handleInput(deltaTime);
    model.update();
    view.render();
  }

  @Override
  public void pause() {
    // Handled in model with GameState
  }

  @Override
  public void resume() {
    // Handled in model with GameState
  }

  @Override
  public void dispose() {
    view.dispose();
    model.dispose();
    controller.dispose();
  }
}
