package net.johnbrooks.fjg.drawables.tiles;

import org.newdawn.slick.opengl.Texture;

import java.util.HashMap;

/**
 * Created by ieatl on 7/4/2017.
 */
public class NewTile
{
    private static HashMap<Integer, NewTile> tileHashMap = new HashMap<>();
    public static NewTile getTile(int id) { return tileHashMap.get(id); }

    private NewTileType tileType;
    private Texture[][] textures;

    public NewTile(NewTileType tileType)
    {
        this.tileType = tileType;
        this.textures = new Texture[tileType.getSize()][tileType.getSize()];
    }

    public Texture calculateTexture(NewTileGrid tileGrid, int x, int y)
    {
        int tileId = tileGrid.getTile(x, y).tileType.getId();

        int idUp = tileGrid.getTile(x, y-1).tileType.getId();
        int idDown = tileGrid.getTile(x, y+1).tileType.getId();
        int idLeft = tileGrid.getTile(x-1, y).tileType.getId();
        int idRight = tileGrid.getTile(x+1, y).tileType.getId();


    }
}
