package inf112.core.model.entities.ghost;

import inf112.core.utils.Utility;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RandomGhostFactoryTest {

  private RandomGhostFactory factory;
  private List<OverworldGhost> ghostList;

  @BeforeEach
  void setUp() {
    factory = new RandomGhostFactory();
    ghostList = new ArrayList<>(List.of(
        new OverworldGhost("Ghostly", 3, GhostType.REGULAR, 30),
        new OverworldGhost("Shadow", 3, GhostType.REGULAR, 40),
        new OverworldGhost("Phantom", 3, GhostType.REGULAR, 50),
        new OverworldGhost("Specter", 3, GhostType.REGULAR, 60),
        new OverworldGhost("Wraith", 3, GhostType.REGULAR, 70),
        new OverworldGhost("Banshee", 3, GhostType.REGULAR, 80),
        new OverworldGhost("YellowBoss", 10, GhostType.BOSS, 150),
        new OverworldGhost("PurpleBoss", 10, GhostType.BOSS, 150)));
  }

  @Test
  void testGetNext_ReturnsValidRegularGhost() {
    try (MockedStatic<Utility> mockedUtility = mockStatic(Utility.class)) {
      mockedUtility.when(() -> Utility.loadGhostsFromCSV(anyString()))
          .thenReturn(ghostList);
      mockedUtility.when(() -> Utility.getRandomNumber(0, 5))
          .thenReturn(2);

      OverworldGhost ghost = factory.getNextGhost();
      assertNotNull(ghost);
      assertEquals(GhostType.REGULAR, ghost.getType());
    }
  }

  @Test
  void testGetNext_ReturnsMultipleDifferentGhosts() {
    try (MockedStatic<Utility> mockedUtility = mockStatic(Utility.class)) {
      mockedUtility.when(() -> Utility.loadGhostsFromCSV(anyString()))
          .thenReturn(ghostList);

      Set<String> ghostNames = new HashSet<>();

      for (int i = 0; i < 6; i++) {
        mockedUtility.when(() -> Utility.getRandomNumber(0, 5)).thenReturn(i % 6);
        OverworldGhost ghost = factory.getNextGhost();
        assertNotNull(ghost);
        assertEquals(GhostType.REGULAR, ghost.getType());
        ghostNames.add(ghost.getName());
      }

      assertTrue(ghostNames.size() > 1);
    }
  }

  @Test
  void testGetNext_IgnoresBossesOnlyReturnsRegulars() {
    try (MockedStatic<Utility> mockedUtility = mockStatic(Utility.class)) {
      mockedUtility.when(() -> Utility.loadGhostsFromCSV(anyString()))
          .thenReturn(ghostList);

      for (int i = 0; i < 6; i++) {
        mockedUtility.when(() -> Utility.getRandomNumber(0, 5)).thenReturn(i);
        OverworldGhost ghost = factory.getNextGhost();
        assertEquals(GhostType.REGULAR, ghost.getType());
      }
    }
  }

  @Test
  void testGetNext_ReturnsNullIfNoRegulars() {
    try (MockedStatic<Utility> mockedUtility = mockStatic(Utility.class)) {
      List<OverworldGhost> bossOnly = List.of(
          new OverworldGhost("YellowBoss", 10, GhostType.BOSS, 150),
          new OverworldGhost("PurpleBoss", 10, GhostType.BOSS, 150));

      mockedUtility.when(() -> Utility.loadGhostsFromCSV(anyString()))
          .thenReturn(bossOnly);

      OverworldGhost ghost = factory.getNextGhost();
      assertNull(ghost);
    }
  }

  @Test
  void testGetNextPowerUp_ThrowsUnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class,
        () -> factory.getNextPowerUp(0f, 0f, 10));
  }
}
