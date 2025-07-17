package inf112.core.view.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameButton {
  ImageButton imageButton;

  public GameButton(Texture texture, ClickListener clickListener, int buttonX, int buttonY, int buttonWidth,
      int buttonHeight) {
    this.imageButton = new ImageButton(makeImageButtonStyle(texture));
    this.imageButton.setSize(buttonWidth, buttonHeight);
    this.imageButton.setPosition(buttonX, buttonY);
    this.imageButton.addListener(clickListener);
  }

  /**
   * Gets the ImageButton instance.
   *
   * @return The ImageButton instance
   */
  public ImageButton getImageButton() {
    return this.imageButton;
  }

  private ImageButton.ImageButtonStyle makeImageButtonStyle(Texture texture) {
    TextureRegion textureRegion = new TextureRegion(texture);
    TextureRegionDrawable drawable = new TextureRegionDrawable(textureRegion);

    ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
    style.up = drawable;
    return style;
  }
}
