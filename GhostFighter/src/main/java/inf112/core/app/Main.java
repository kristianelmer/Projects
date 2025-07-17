package inf112.core.app;

import org.lwjgl.system.Configuration;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.utils.Os;
import com.badlogic.gdx.utils.SharedLibraryLoader;

public class Main {
  public static void main(String[] args) {
    if (SharedLibraryLoader.os == Os.MacOsX) {
      Configuration.GLFW_LIBRARY_NAME.set("glfw_async");
    }

    Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
    cfg.setTitle("GhostFighter");
    cfg.setWindowedMode(960, 640);

    new Lwjgl3Application(new GameApp(), cfg);
  }
}
