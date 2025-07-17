package inf112.core.map;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

public class MapData {
  private final TiledMap tiledMap;
  private final int width;
  private final int height;
  private final MapObjects walls;
  private final MapObjects ghostLegalPos;
  private final MapObjects positions;
  private final Rectangle startPosition;

  public MapData(TiledMap tiledMap) {
    this.tiledMap = tiledMap;
    this.width = tiledMap.getProperties().get("width", Integer.class) *
        tiledMap.getProperties().get("tilewidth", Integer.class);
    this.height = tiledMap.getProperties().get("height", Integer.class) *
        tiledMap.getProperties().get("tileheight", Integer.class);
    this.walls = tiledMap.getLayers().get("Collision").getObjects();
    this.ghostLegalPos = tiledMap.getLayers().get("LegalGhostPos").getObjects();
    this.positions = tiledMap.getLayers().get("Positions").getObjects();
    this.startPosition = extractStartPosition();
  }

  private Rectangle extractStartPosition() {
    for (MapObject obj : positions) {
      if ("StartPos".equals(obj.getName()) && obj instanceof RectangleMapObject) {
        return ((RectangleMapObject) obj).getRectangle();
      }
    }

    return new Rectangle(400, 400, 100, 100); // fallback
  }

  /**
   * Creates a new OrthogonalTiledMapRenderer for rendering the tiled map.
   *
   * @return A new OrthogonalTiledMapRenderer instance
   */
  public OrthogonalTiledMapRenderer createRenderer() {
    return new OrthogonalTiledMapRenderer(tiledMap);
  }

  /**
   * Gets the width of the map.
   *
   * @return The width of the map
   */
  public int getWidth() {
    return width;
  }

  /**
   * Gets the height of the map.
   *
   * @return The height of the map
   */
  public int getHeight() {
    return height;
  }

  /**
   * Gets the MapObjects representing the walls of the map.
   *
   * @return The MapObjects representing the walls
   */
  public MapObjects getWalls() {
    return walls;
  }

  /**
   * Gets the starting position of the player.
   *
   * @return The starting position of the player
   */
  public Rectangle getStartPosition() {
    return startPosition;
  }

  /**
   * Gets the MapObjects representing the positions of the map.
   *
   * @return The MapObjects representing the positions
   */
  public MapObjects getPositions() {
    return positions;
  }

  /**
   * Gets the MapObjects representing the legal positions for ghosts to move.
   *
   * @return The MapObjects representing the positions
   */
  public MapObjects getGhostLegalPos() {
    return ghostLegalPos;
  }
}
