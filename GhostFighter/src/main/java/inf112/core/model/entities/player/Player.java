package inf112.core.model.entities.player;

import com.badlogic.gdx.math.Rectangle;

import inf112.core.model.Direction;
import inf112.core.model.entities.OverworldEntity;
import inf112.core.model.entities.attack.Attack;
import inf112.core.model.entities.attack.AttackRegistry;
import inf112.core.model.entities.attack.AttackResult;
import inf112.core.model.entities.ghost.BattleGhost;
import inf112.core.utils.Utility;

import java.util.List;

public class Player implements IPlayer, OverworldEntity {
  private static final int SPEED = 200;
  private static final int PLAYER_SIZE = 20;
  private static final int MAX_HEARTS = 3;

  private final Rectangle bounds;
  private final List<Attack> attacks;

  private int maxHealth; // increases between battles
  private int health;
  private int hearts;

  public Player(float x, float y) {
    this.bounds = new Rectangle(x, y, PLAYER_SIZE, PLAYER_SIZE);
    this.maxHealth = 50; // start health
    this.health = maxHealth;
    this.hearts = MAX_HEARTS;
    this.attacks = AttackRegistry.ATTACKS;
  }

  private Attack getRandomAttack() {
    return attacks.get(Utility.getRandomNumber(0, attacks.size() - 1));
  }

  @Override
  public AttackResult attack(BattleGhost ghost) {
    Attack chosenAttack = getRandomAttack();
    AttackResult result = chosenAttack.execute();

    if (result.wasHit()) {
      ghost.takeDamage(result.getDamage());
    }

    return result;
  }

  @Override
  public void move(Direction direction, float deltaTime) {
    switch (direction) {
      case LEFT:
        bounds.x -= SPEED * deltaTime;
        break;
      case RIGHT:
        bounds.x += SPEED * deltaTime;
        break;
      case UP:
        bounds.y += SPEED * deltaTime;
        break;
      case DOWN:
        bounds.y -= SPEED * deltaTime;
        break;
      default:
        break;
    }
  }

  @Override
  public Rectangle getNewBounds(Direction direction, float deltaTime) {
    return switch (direction) {
      case LEFT -> new Rectangle((bounds.x - SPEED * deltaTime), bounds.y, bounds.width, bounds.height);
      case RIGHT -> new Rectangle((bounds.x + SPEED * deltaTime), bounds.y, bounds.width, bounds.height);
      case UP -> new Rectangle(bounds.x, (bounds.y + SPEED * deltaTime), bounds.width, bounds.height);
      case DOWN -> new Rectangle(bounds.x, (bounds.y - SPEED * deltaTime), bounds.width, bounds.height);
      default -> new Rectangle(bounds);
    };
  }

  @Override
  public Rectangle getBounds() {
    return bounds;
  }

  @Override
  public float getX() {
    return bounds.x;
  }

  @Override
  public float getY() {
    return bounds.y;
  }

  @Override
  public int getSize() {
    return PLAYER_SIZE;
  }

  @Override
  public int getWidth() {
    return PLAYER_SIZE;
  }

  @Override
  public int getHeight() {
    return PLAYER_SIZE;
  }

  @Override
  public String toString() {
    return "[x=" + bounds.x + ", y=" + bounds.y + ", width=" + bounds.width + ", height=" + bounds.height + "]";
  }

  @Override
  public int getHealth() {
    return health;
  }

  @Override
  public void setHealth(int newHealth) {
    this.health = newHealth;
  }

  @Override
  public void setMaxHealth(int newHealth) {
    this.maxHealth = newHealth;
  }

  @Override
  public boolean isAlive() {
    return health > 0;
  }

  @Override
  public void takeDamage(int damage) {
    health -= damage;
    if (health < 0)
      health = 0;
  }

  @Override
  public int getMaxHealth() {
    return (health > maxHealth) ? health : maxHealth;
  }

  @Override
  public int getHearts() {
    return hearts;
  }

  @Override
  public void restoreHearts() {
    if (hearts < MAX_HEARTS) {
      hearts++;
    }
  }

  @Override
  public void decreaseHearts() {
    if (hearts > 0) {
      hearts--;
    }
  }
}
