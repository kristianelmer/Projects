package inf112.core.view.rendering.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HealthBar {
  private static final float HEALTHBAR_LENGTH = 250;

  private final Texture green;
  private final Texture red;
  private final float x;
  private final float y;

  public HealthBar(float x, float y) {
    this.green = new Texture("images/battle/greenSquare.jpg");
    this.red = new Texture("images/battle/redSquare.jpg");
    this.x = x;
    this.y = y;
  }

  /**
   * Draws the health bar showing the current health status.
   *
   * @param batch         The SpriteBatch to use for rendering
   * @param currentHealth The current health value (must be <= maxHealth)
   * @param maxHealth     The maximum possible health value
   * @throws IllegalArgumentException if currentHealth > maxHealth or if either
   *                                  value is negative
   */
  public void draw(SpriteBatch batch, int currentHealth, int maxHealth) {
    if (currentHealth < 0 || maxHealth < 0) {
      throw new IllegalArgumentException("Health values cannot be negative");
    }
    if (currentHealth > maxHealth) {
      maxHealth = currentHealth;
    }

    float currentHpLength = (HEALTHBAR_LENGTH * currentHealth) / maxHealth;
    float redStart = x + currentHpLength;
    float redLength = HEALTHBAR_LENGTH - currentHpLength;

    batch.draw(green, x, y, currentHpLength, 10);
    batch.draw(red, redStart, y, redLength, 10);
  }

  /**
   * Releases all resources used by this health bar.
   * Must be called when the health bar is no longer needed.
   */
  public void dispose() {
    green.dispose();
    red.dispose();
  }
}