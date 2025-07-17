package inf112.core.model.entities.attack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AttackTest {

  private Attack testAttack;

  @BeforeEach
  public void setUpBeforeEach() {
    String attackName = "TestHit";
    int minDamage = 100;
    int maxDamage = 100;
    int criticalHitProbability = 0;
    int accuracy = 100;

    testAttack = new Attack(attackName, minDamage, maxDamage, criticalHitProbability, accuracy);
  }

  @Test
  public void testAttackConstructor() {
    assertNotNull(testAttack);
  }

  @Test
  public void testAttackExecutionNotNull() {
    assertNotNull(testAttack.execute());
  }

  @Test
  public void testAttackWithResult() {
    String expectedResultName = "TestHit";
    int expectedDamage = 100;
    boolean expectedWasHit = true;
    boolean expectedWasCritical = false;

    assertEquals(expectedResultName, testAttack.execute().getAttackName());
    assertEquals(expectedDamage, testAttack.execute().getDamage());
    assertEquals(expectedWasHit, testAttack.execute().wasHit());
    assertEquals(expectedWasCritical, testAttack.execute().wasCritical());
  }

  @Test
  public void testGetName() {
    String expected = "TestHit";
    assertEquals(expected, testAttack.getName());
  }
}
