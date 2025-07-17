package inf112.core.view.rendering.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import inf112.core.model.entities.player.Player;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class AbstractUIBar {

  protected static final float TOPBAR_HEIGHT = 35;
  protected Texture background;

  public AbstractUIBar(String backgroundPath) {
    this.background = new Texture(backgroundPath);
  }

  /**
   * Renders the UI bar in screen space (not affected by camera movement).
   *
   * @param batch The SpriteBatch to use for rendering
   */
  public void render(SpriteBatch batch) {
    batch.end();
    batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    batch.begin();

    drawBackground(batch);
    renderContents(batch);

    batch.end();
    batch.begin();
  }

  /**
   * Renders the UI bar in screen space with additional player and camera context.
   *
   * @param batch  The SpriteBatch to use for rendering
   * @param camera The game camera (used to restore projection matrix after
   *               rendering)
   * @param player The player entity (may be used for contextual information)
   */
  public void render(SpriteBatch batch, OrthographicCamera camera, Player player) {
    batch.end();
    batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    batch.begin();

    drawBackground(batch);
    renderContents(batch, camera, player);

    batch.end();
    batch.begin();
    batch.setProjectionMatrix(camera.combined);
  }

  /**
   * Draws the background texture of the UI bar.
   *
   * @param batch The SpriteBatch to use for rendering
   */
  public void drawBackground(SpriteBatch batch) {
    batch.draw(background, 0, getYPosition(), Gdx.graphics.getWidth(), TOPBAR_HEIGHT);
  }

  /**
   * Disposes of all resources used by this UI bar.
   * Must be called when the bar is no longer needed.
   */
  public void dispose() {
    background.dispose();
  }

  /**
   * Abstract method for rendering bar-specific contents in screen space.
   * Must be implemented by concrete subclasses.
   *
   * @param batch The SpriteBatch to use for rendering
   */
  public abstract void renderContents(SpriteBatch batch);

  /**
   * Abstract method for rendering bar-specific contents with camera and player
   * context.
   * Must be implemented by concrete subclasses.
   *
   * @param batch  The SpriteBatch to use for rendering
   * @param camera The game camera
   * @param player The player entity
   */
  public abstract void renderContents(SpriteBatch batch, OrthographicCamera camera, Player player);

  /**
   * Gets the vertical position of the UI bar on screen.
   *
   * @return The y-coordinate where the bar should be rendered
   */
  public abstract float getYPosition();
}
