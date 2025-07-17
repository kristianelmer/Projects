package inf112.core.model.entities.attack;

import java.util.Arrays;
import java.util.List;

public class AttackRegistry {
  public static final List<Attack> ATTACKS = Arrays.asList(
      new Attack("Swipe", 1, 5, 10, 80),
      new Attack("Claw", 1, 3, 20, 90),
      new Attack("Bash", 3, 10, 15, 55),
      new Attack("Bite", 2, 6, 35, 70),
      new Attack("Strike", 3, 11, 15, 60));
}
