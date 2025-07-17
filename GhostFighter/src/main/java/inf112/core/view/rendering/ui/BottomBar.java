package inf112.core.view.rendering.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.core.model.entities.player.Player;

public class BottomBar extends AbstractUIBar {

  public BottomBar() {
    super("images/ui/UIBarBackground.png");
  }

  @Override
  public void renderContents(SpriteBatch batch) {
    // No extra rendering in battlescene
  }

  @Override
  public void renderContents(SpriteBatch batch, OrthographicCamera camera, Player player) {
    // No extra rendering in overworld
  }

  @Override
  public float getYPosition() {
    return 0;
  }
}
