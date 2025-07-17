package inf112.core.testutils;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

public class InitializeLibGDX {

  public static void initialize() {
    HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();

    new HeadlessApplication(new ApplicationListener() {

      @Override
      public void create() {
        
      }

      @Override
      public void resize(int width, int height) {
        
      }

      @Override
      public void render() {
        
      }

      @Override
      public void pause() {
        
      }

      @Override
      public void resume() {
        
      }

      @Override
      public void dispose() {
        
      }
    }, config);
  }
}
