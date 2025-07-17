package inf112.core.utils;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import inf112.core.model.entities.ghost.GhostType;
import inf112.core.model.entities.ghost.OverworldGhost;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class UtilityTest {

  private Files filesMock;
  private FileHandle fileHandleMock;

  @BeforeEach
  void setUp() {
    filesMock = mock(Files.class);
    fileHandleMock = mock(FileHandle.class);
    Gdx.files = filesMock;
  }

  @Test
  void testLoadGhostsFromCSV_FullValidData() {
    String csvData = "name,level,type,size\n" +
        "Ghostly,3,REGULAR,30\n" +
        "Shadow,3,REGULAR,30\n" +
        "Phantom,3,REGULAR,45\n" +
        "Specter,3,REGULAR,30\n" +
        "Wraith,3,REGULAR,45\n" +
        "Banshee,3,REGULAR,40\n" +
        "YellowBoss,10,BOSS,90\n" +
        "PurpleBoss,10,BOSS,90";

    when(filesMock.internal("ghosts.csv")).thenReturn(fileHandleMock);
    when(fileHandleMock.readString()).thenReturn(csvData);

    List<OverworldGhost> ghosts = Utility.loadGhostsFromCSV("ghosts.csv");

    assertEquals(8, ghosts.size());

    OverworldGhost ghost = ghosts.get(0);
    assertEquals("Ghostly", ghost.getName());
    assertEquals(3, ghost.getLevel());
    assertEquals(GhostType.REGULAR, ghost.getType());
    assertEquals(30, ghost.getSize());

    OverworldGhost boss = ghosts.get(6);
    assertEquals("YellowBoss", boss.getName());
    assertEquals(10, boss.getLevel());
    assertEquals(GhostType.BOSS, boss.getType());
    assertEquals(90, boss.getSize());
  }

  @Test
  void testGetRandomNumber_ValidRange() {
    int lowerBound = 5;
    int upperBound = 10;
    int randomNumber = Utility.getRandomNumber(lowerBound, upperBound);

    assertTrue(randomNumber >= lowerBound && randomNumber <= upperBound);
  }

  @Test
  void testGetRandomNumber_SameBounds() {
    int lowerBound = 7;
    int upperBound = 7;
    int randomNumber = Utility.getRandomNumber(lowerBound, upperBound);

    assertEquals(lowerBound, randomNumber);
  }

  @Test
  void testGetRandomNumber_NegativeRange() {
    int lowerBound = -10;
    int upperBound = -5;
    int randomNumber = Utility.getRandomNumber(lowerBound, upperBound);

    assertTrue(randomNumber >= lowerBound && randomNumber <= upperBound);
  }

  @Test
  void testGetRandomNumber_MixedRange() {
    int lowerBound = -5;
    int upperBound = 5;
    int randomNumber = Utility.getRandomNumber(lowerBound, upperBound);

    assertTrue(randomNumber >= lowerBound && randomNumber <= upperBound);
  }

  @Test
  void testGetRandomNumber_InvalidRange() {
    int lowerBound = 10;
    int upperBound = 5;

    assertThrows(IllegalArgumentException.class, () -> {
      Utility.getRandomNumber(lowerBound, upperBound);
    });
  }

  @Test
  void testGetRandomNumber_LargeRange() {
    int lowerBound = 0;
    int upperBound = 1000000;
    int randomNumber = Utility.getRandomNumber(lowerBound, upperBound);

    assertTrue(randomNumber >= lowerBound && randomNumber <= upperBound);
  }
}
