package inf112.core.view.rendering.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import inf112.core.audio.AudioController;
import inf112.core.view.ViewableGameModel;

public class GeneralClickListener extends ClickListener {
  ViewableGameModel model;
  String input;
  AudioController audioController;

  public GeneralClickListener(ViewableGameModel model, String input) {
    this.model = model;
    this.input = input;
    this.audioController = model.getAudioController();
  }

  @Override
  public void clicked(InputEvent event, float x, float y) {
    switch (input) {
      case "menu":
        model.restartGoToMenu();
        break;

      case "quit":
        Gdx.app.exit();
        break;

      case "pause":
        model.handlePause();
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        break;

      case "resume":
        model.handlePause();
        break;

      case "mute":
        audioController.toggleMuteGame();
        break;

      default:
        System.out.println(input + " is not defined as a type of input for GeneralClickListener");
        break;
    }

  }

  @Override
  public void enter(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
  }

  @Override
  public void exit(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor toActor) {
    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
  }
}
