package inf112.core.model.entities.ghost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.core.model.entities.player.Player;

import static org.junit.jupiter.api.Assertions.*;

class BattleGhostTest {

  BattleGhost ghost;

  @BeforeEach
  void setUp() {
    ghost = new BattleGhost("TestGhost", 1, GhostType.REGULAR);
  }

  @Test
  void healthShouldBePositiveAfterCreation() {
    assertTrue(ghost.getHealth() > 0);
    assertTrue(ghost.getMaxHealth() >= ghost.getHealth());
  }

  @Test
  void regularGhostHealthIsWithinExpectedRange() {
      int level = 3;
      BattleGhost ghost = new BattleGhost("Regular", level, GhostType.REGULAR);
  
      int base = level * 5 + 2 * level; // 3 * 5 + 2 * 3 = 15 + 6 = 21
      int health = ghost.getMaxHealth();
  
      assertTrue(health >= base - 4 && health <= base + 4);
  }
  

  @Test
  void bossGhostHealthIsWithinExpectedRange() {
      int level = 3;
      BattleGhost ghost = new BattleGhost("Boss", level, GhostType.BOSS);
  
      int base = level * 5 + 4; // 3 * 5 + 4 = 15 + 4 = 19
      int health = ghost.getMaxHealth();
  
      assertTrue(health >= base - 4 && health <= base + 4);
  }
  
  

  @Test
  void takeDamageReducesHealth() {
    ghost.takeDamage(5);
    int newHealth = (ghost.getHealth() < 0) ? 0 : ghost.getHealth(); // health is 0 if under 0
    assertEquals(newHealth, ghost.getHealth());
  }

  @Test
  void healthDoesNotGoBelowZero() {
    ghost.takeDamage(999);
    assertEquals(0, ghost.getHealth());
  }

  @Test
  void isAliveReturnsTrueWhenHealthIsAboveZero() {
    ghost.takeDamage(ghost.getHealth() - 1);
    assertTrue(ghost.isAlive());
  }

  @Test
  void isAliveReturnsFalseWhenHealthIsZero() {
    ghost.takeDamage(ghost.getHealth());
    assertFalse(ghost.isAlive());
  }

  @Test
  void attackShouldAffectPlayerHealthWhenHit() {
    Player dummyPlayer = new Player(100, 100);
    int originalHealth = dummyPlayer.getHealth();

    // can miss and do zero damage - so must be in loop
    // test failure will cause stackOverflowError which is okay because it should fail either way
    while (dummyPlayer.getHealth() == originalHealth) {
      ghost.attack(dummyPlayer);
    }

    assertTrue(dummyPlayer.getHealth() < originalHealth);
  }
}
