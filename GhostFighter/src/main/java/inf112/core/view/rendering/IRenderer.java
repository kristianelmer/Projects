package inf112.core.view.rendering;

public interface IRenderer {

  /**
   * Renders the necessary graphical elements on the screen.
   * Implementing classes should define how the rendering process is carried out.
   */
  void render();

  /**
   * Disposes of any resources used during the rendering process, freeing up
   * memory
   * and other system resources. This method should be called when the rendering
   * context is no longer needed.
   */
  void dispose();
}
