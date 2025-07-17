package inf112.core.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import inf112.core.model.entities.ghost.GhostType;
import inf112.core.model.entities.ghost.OverworldGhost;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utility {

  /**
   * Takes an upper and a lower bound, and returns a random number between
   * (including) said bounds.
   *
   * @param lowerBound the lower bound (inclusive)
   * @param upperBound the upper bound (inclusive)
   * @return int value within bounds
   */
  public static int getRandomNumber(int lowerBound, int upperBound) {
    Random rand = new Random();
    return rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
  }

  /**
   * Reads ghost data from a CSV file and returns a list of OverworldGhost
   * objects.
   *
   * @param filePath the path to the CSV file (relative to the assets' folder)
   * @return a list of OverworldGhost objects
   */
  public static List<OverworldGhost> loadGhostsFromCSV(String filePath) {
    List<OverworldGhost> ghosts = new ArrayList<>();
    FileHandle file = Gdx.files.internal(filePath);

    String[] lines = file.readString().split("\\r?\\n");

    // Skip header row, process rest
    for (int i = 1; i < lines.length; i++) {
      String[] data = lines[i].split(",");
      if (data.length < 4) {
        throw new IllegalArgumentException("Invalid CSV format: Each row must have at least 4 columns.");
      }

      String name = data[0].trim();
      int level = Integer.parseInt(data[1].trim());
      GhostType type = GhostType.valueOf(data[2].trim().toUpperCase());
      int size = Integer.parseInt(data[3].trim());

      ghosts.add(new OverworldGhost(name, level, type, size));
    }

    return ghosts;
  }
}