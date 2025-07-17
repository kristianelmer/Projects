package inf112.core.model.entities.attack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class AttackResultTest {
  @Test
  public void testAttackResultConstructor() {
    AttackResult test = new AttackResult("test", 0, true, false);
    assertNotNull(test);
  }

  @Test
  public void testAttackResultGetters() {
    String name = "test";
    int dmg = 100;
    boolean hit = true;
    boolean crit = false;
    AttackResult test = new AttackResult("test", 100, true, false);

    assertEquals(name, test.getAttackName());
    assertEquals(dmg, test.getDamage());
    assertEquals(hit, test.wasHit());
    assertEquals(crit, test.wasCritical());
  }
}
