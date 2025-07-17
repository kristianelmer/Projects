package inf112.core.view.rendering.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.view.ViewableGameModel;
import inf112.core.model.entities.attack.Attack;
import inf112.core.model.entities.attack.AttackResult;
import inf112.core.model.entities.ghost.*;
import inf112.core.model.entities.player.Player;

public class AttackButtonClickListener extends ClickListener {

  private final Attack attack;
  private final ViewableGameModel model;

  public AttackButtonClickListener(Attack attack, ViewableGameModel model) {
    this.attack = attack;
    this.model = model;
  }

  @Override
  public void clicked(InputEvent event, float x, float y) {
    Player player = model.getPlayer();
    BattleGhost ghost = model.getBattleScene().getBattleGhost();

    // Player attacks with the chosen attack
    AttackResult playerResult = attack.execute();
    if (playerResult.wasHit()) {
      ghost.takeDamage(playerResult.getDamage());
    }
    model.setLastPlayerAttackResult(playerResult);

    // Ghost retaliates with a random attack
    AttackResult ghostResult = ghost.attack(player);
    model.setLastGhostAttackResult(ghostResult);
  }

  @Override
  public void enter(InputEvent event, float x, float y, int pointer,
      com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
  }

  @Override
  public void exit(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor toActor) {
    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
  }
}
