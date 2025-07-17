package inf112.core.view.rendering.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import inf112.core.view.ViewableGameModel;
import inf112.core.view.rendering.MenuRenderer;

public class MenuButtonClickListener extends ClickListener {
  private final ViewableGameModel model;
  private final MenuButtonType button;
  private final MenuRenderer menuRenderer;

  public MenuButtonClickListener(MenuButtonType button,ViewableGameModel model, MenuRenderer renderer) {
    this.model = model;
    this.button = button;
    this.menuRenderer = renderer;
  }

  @Override
  public void clicked(InputEvent event, float x, float y) {
    switch (button) {
        case PLAY:
            model.handleMenuSelection();
            break;
        case HOW_TO:
            menuRenderer.howToPlayVisible = true;
            break;
        case OPTIONS:
            menuRenderer.optionsVisible = true;
        break;
        case CREDITS:
            menuRenderer.creditsVisible = true;
            break;
        case QUIT:
            Gdx.app.exit();
            break;
        case BACK:
            if (menuRenderer.howToPlayVisible) menuRenderer.howToPlayVisible = false;
            if (menuRenderer.optionsVisible) menuRenderer.optionsVisible = false;
            if (menuRenderer.creditsVisible) menuRenderer.creditsVisible = false;
            break;
        default:
            System.out.println("Something went wrong in the ButtonClickListener");
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

