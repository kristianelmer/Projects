package inf112.core.model.entities.attack;

import inf112.core.utils.Utility;

public class Attack implements IAttack {
  private final String name;
  private final int minDamage;
  private final int maxDamage;
  private final int critChance; // Percentage (e.g., 10 means 10% chance to crit)
  private final int accuracy; // Percentage (e.g., 70 means 70% chance to hit)

  public Attack(String name, int minDamage, int maxDamage, int critChance, int accuracy) {
    this.name = name;
    this.minDamage = minDamage;
    this.maxDamage = maxDamage;
    this.critChance = critChance;
    this.accuracy = accuracy;
  }

  @Override
  public AttackResult execute() {
    boolean hit = Utility.getRandomNumber(1, 100) <= accuracy;
    boolean critical = false;
    int damage = 0;
    long startTime = System.currentTimeMillis();
    while (true) {
      long elapsedTime = System.currentTimeMillis() - startTime;
      if (elapsedTime > 125) { // time in 'attacking mode' after button click
        break;
      }

      if (hit) {
        damage = Utility.getRandomNumber(minDamage, maxDamage);
        critical = Utility.getRandomNumber(1, 100) <= critChance;
        if (critical) {
          damage *= 2;
        }
      }
    }

    return new AttackResult(name, damage, hit, critical);
  }

  @Override
  public String getName() {
    return name;
  }
}
