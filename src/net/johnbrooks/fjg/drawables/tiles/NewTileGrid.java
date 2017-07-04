package net.johnbrooks.fjg.drawables.tiles;

/**
 * Created by ieatl on 7/4/2017.
 */
public class NewTileGrid
{
    private static final short TILE_WIDTH = 64;
    private static final short TILE_HEIGHT = 64;

    public static final short TILES_WIDE = 20;
    public static final short TILES_HIGH = 15;

    private Integer[][] map;

    public NewTileGrid()
    {
        map = new Integer[TILES_WIDE][TILES_HIGH];
    }

    public NewTileGrid(NewTileType baseTileType)
    {
        map = new Integer[TILES_WIDE][TILES_HIGH];
        for (int x = 0; x < map.length; x++)
            for (int y = 0; y < map[x].length; y++)
                map[x][y] = baseTileType.getId();
    }

    public NewTileGrid(int[][] layout)
    {
        try
        {
            map = new Integer[TILES_WIDE][TILES_HIGH];
            for (int x = 0; x < map.length; x++)
                for (int y = 0; y < map[x].length; y++)
                    map[x][y] = layout[y][x];
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /*
    Get the tile based on integer slot values (array's x, and y values).
     */
    public NewTile getTile(int x, int y)
    {
        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length)
        {
            System.out.println("TileGrid out of index. X: " + x + " and Y: " + y);
            return null;
        }
        return NewTile.getTile(map[x][y]);
    }

    /*
    Get the tile based on a pixel value (Screen width and height).
     */
    public NewTile getTile(float x, float y)
    {
        int _x = (int) (x / 64);
        int _y  = (int) (y / 64);

        return getTile(_x, _y);
    }

    public void setTile(int x, int y, NewTileType tileType)
    {
        map[x][y] = tileType.getId();
    }

    public void update()
    {

    }

    public void draw()
    {
        //for (int x = 0; x < map.length; x++)
        //    for (int y = 0; y < map[x].length; y++)
        //        map[x][y].draw();
    }
}
