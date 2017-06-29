package net.johnbrooks.fjg;

import net.johnbrooks.fjg.drawables.Tile;
import net.johnbrooks.fjg.drawables.TileType;

/**
 * Created by ieatl on 6/29/2017.
 */
public class TileGrid
{
    private Tile[][] map;

    public TileGrid(TileType baseTileType)
    {
        map = new Tile[20][15];
        for (int x = 0; x < map.length; x++)
            for (int y = 0; y < map[x].length; y++)
                map[x][y] = new Tile(x * 64, y * 64, 64, 64, baseTileType);
    }

    public TileGrid(int[][] layout)
    {
        try
        {
            map = new Tile[20][15];
            for (int x = 0; x < map.length; x++)
                for (int y = 0; y < map[x].length; y++)
                    map[x][y] = new Tile(x * 64, y * 64, 64, 64, TileType.getTileType(layout[y][x]));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void draw()
    {
        for (int x = 0; x < map.length; x++)
            for (int y = 0; y < map[x].length; y++)
                map[x][y].draw();
    }
}
