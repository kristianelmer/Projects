package inf112.core.model.entities.ghost;

public abstract class Ghost {
  private final String name;
  private final int level;
  private final GhostType type;

  public Ghost(String name, int level, GhostType type) {
    this.name = name;
    this.level = level;
    this.type = type;
  }

  /**
   * Gets the ghost's display name.
   *
   * @return The ghost's name (never null or empty)
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the ghost's difficulty level.
   *
   * @return The positive integer level
   */
  public int getLevel() {
    return level;
  }

  /**
   * Gets the ghost's classification type.
   *
   * @return The GhostType enum value (never null)
   */
  public GhostType getType() {
    return type;
  }
}
