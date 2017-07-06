package net.johnbrooks.fjg.drawables.tiles;

import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 6/29/2017.
 */
public enum TileType
{
    GRASS(TileTexture.GRASS.getTexture(), true, 0), DIRT(TileTexture.DIRT.getTexture(), true, 1),
    WATER(TileTexture.WATER.getTexture(), false, 2), SAND(TileTexture.SAND.getTexture(), true, 3),
    STONE(TileTexture.STONE.getTexture(), true, 4),

    DIRT_GRASS_SIDE_LEFT(TileTexture.DIRT_GRASS_LEFT.getTexture(), true, 50), DIRT_GRASS_SIDE_RIGHT(TileTexture.DIRT_GRASS_RIGHT.getTexture(), true, 51),
    DIRT_GRASS_SIDE_BOTTOM(TileTexture.DIRT_GRASS_BOTTOM.getTexture(), true, 52), DIRT_GRASS_SIDE_TOP(TileTexture.DIRT_GRASS_TOP.getTexture(), true, 53),
    DIRT_GRASS_SIDE_BOTTOM_LEFT(TileTexture.DIRT_GRASS_BOTTOM_LEFT.getTexture(), true, 54), DIRT_GRASS_SIDE_BOTTOM_RIGHT(TileTexture.DIRT_GRASS_BOTTOM_RIGHT.getTexture(), true, 55),
    DIRT_GRASS_SIDE_TOP_LEFT(TileTexture.DIRT_GRASS_TOP_LEFT.getTexture(), true, 56), DIRT_GRASS_SIDE_TOP_RIGHT(TileTexture.DIRT_GRASS_TOP_RIGHT.getTexture(), true, 57),
    DIRT_GRASS_CORNER_A(TileTexture.DIRT_GRASS_CORNER_A.getTexture(), true, 58), DIRT_GRASS_CORNER_B(TileTexture.DIRT_GRASS_CORNER_B.getTexture(), true, 59),
    DIRT_GRASS_CORNER_C(TileTexture.DIRT_GRASS_CORNER_C.getTexture(), true, 60), DIRT_GRASS_CORNER_D(TileTexture.DIRT_GRASS_CORNER_D.getTexture(), true, 61);

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
