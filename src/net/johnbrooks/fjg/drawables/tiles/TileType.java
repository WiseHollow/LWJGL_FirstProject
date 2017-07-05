package net.johnbrooks.fjg.drawables.tiles;

import net.johnbrooks.fjg.drawables.TileTexture;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 6/29/2017.
 */
public enum TileType
{
    GRASS(TileTexture.GRASS.getTexture(), true, 0), DIRT(TileTexture.DIRT.getTexture(), true, 1),
    WATER(TileTexture.WATER.getTexture(), false, 2), SAND(TileTexture.SAND.getTexture(), true, 3),
    STONE(TileTexture.STONE.getTexture(), true, 4);

    public static TileType getTileType(final int id) throws Exception
    {
        for (TileType type : TileType.values())
            if (type.getId() == id)
                return type;
        throw new Exception("Invalid TileType id (" + id + ").");
    }

    Texture texture;
    boolean buildable;
    int id;

    TileType(Texture texture, boolean buildable, int id)
    {
        this.texture = texture;
        this.buildable = buildable;
        this.id = id;
    }

    public Texture getTexture() { return texture; }
    public boolean isBuildable() { return buildable; }
    public int getId() { return id; }
}
