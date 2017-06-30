package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.drawables.tiles.TileType;

/**
 * Created by ieatl on 6/29/2017.
 */
public class TileGrid
{
    private static short TILE_WIDTH = 64;
    private static short TILE_HEIGHT = 64;

    private Tile[][] map;

    public TileGrid(TileType baseTileType)
    {
        map = new Tile[20][15];
        for (int x = 0; x < map.length; x++)
            for (int y = 0; y < map[x].length; y++)
                map[x][y] = new Tile(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, baseTileType);
    }

    public TileGrid(int[][] layout)
    {
        try
        {
            map = new Tile[20][15];
            for (int x = 0; x < map.length; x++)
                for (int y = 0; y < map[x].length; y++)
                    map[x][y] = new Tile(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, TileType.getTileType(layout[y][x]));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public Tile getTile(int x, int y)
    {
        return map[x][y];
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
