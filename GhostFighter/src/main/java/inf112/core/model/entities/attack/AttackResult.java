package inf112.core.model.entities.attack;

public class AttackResult implements IAttackResult {
  private final String attackName;
  private final int damage;
  private final boolean hit;
  private final boolean critical;

  public AttackResult(String attackName, int damage, boolean hit, boolean critical) {
    this.attackName = attackName;
    this.damage = damage;
    this.hit = hit;
    this.critical = critical;
  }

  @Override
  public String getAttackName() {
    return attackName;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public boolean wasHit() {
    return hit;
  }

  @Override
  public boolean wasCritical() {
    return critical;
  }
}
