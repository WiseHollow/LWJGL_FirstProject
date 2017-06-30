package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.drawables.tiles.TileType;
import org.newdawn.slick.util.Log;

/**
 * Created by ieatl on 6/29/2017.
 */
public class TileGrid
{
    private static final short TILE_WIDTH = 64;
    private static final short TILE_HEIGHT = 64;

    public static final short TILES_WIDE = 20;
    public static final short TILES_HIGH = 15;

    private Tile[][] map;

    public TileGrid(TileType baseTileType)
    {
        map = new Tile[TILES_WIDE][TILES_HIGH];
        for (int x = 0; x < map.length; x++)
            for (int y = 0; y < map[x].length; y++)
                map[x][y] = new Tile(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, baseTileType);
    }

    public TileGrid(int[][] layout)
    {
        try
        {
            map = new Tile[TILES_WIDE][TILES_HIGH];
            for (int x = 0; x < map.length; x++)
                for (int y = 0; y < map[x].length; y++)
                    map[x][y] = new Tile(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, TileType.getTileType(layout[y][x]));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /*
    Get the tile based on integer slot values (array's x, and y values).
     */
    public Tile getTile(int x, int y)
    {
        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length)
        {
            Log.info("X: " + x + " and Y: " + y);
            return null;
        }
        return map[x][y];
    }

    /*
    Get the tile based on a pixel value (Screen width and height).
     */
    public Tile getTile(float x, float y)
    {
        int _x = (int) (x / 64);
        int _y  = (int) (y / 64);

        return getTile(_x, _y);
    }

    public void setTile(int x, int y, TileType tileType)
    {
        map[x][y] = new Tile(x * 64, y * 64, 64, 64, tileType);
    }

    public void draw()
    {
        for (int x = 0; x < map.length; x++)
            for (int y = 0; y < map[x].length; y++)
                map[x][y].draw();
    }
}
