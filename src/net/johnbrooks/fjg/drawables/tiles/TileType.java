package net.johnbrooks.fjg.drawables.tiles;

import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 6/29/2017.
 */
public enum TileType
{
    GRASS(TileTexture.GRASS.getTexture(), false, 0), DIRT(TileTexture.DIRT.getTexture(), false, 1),
    WATER(TileTexture.WATER.getTexture(), false, 2), SAND(TileTexture.SAND.getTexture(), false, 3),
    STONE(TileTexture.STONE.getTexture(), true, 4),

    DIRT_GRASS_SIDE_LEFT(TileTexture.DIRT_GRASS_LEFT.getTexture(), false, 50), DIRT_GRASS_SIDE_RIGHT(TileTexture.DIRT_GRASS_RIGHT.getTexture(), false, 51),
    DIRT_GRASS_SIDE_BOTTOM(TileTexture.DIRT_GRASS_BOTTOM.getTexture(), false, 52), DIRT_GRASS_SIDE_TOP(TileTexture.DIRT_GRASS_TOP.getTexture(), false, 53),
    DIRT_GRASS_SIDE_BOTTOM_LEFT(TileTexture.DIRT_GRASS_BOTTOM_LEFT.getTexture(), false, 54), DIRT_GRASS_SIDE_BOTTOM_RIGHT(TileTexture.DIRT_GRASS_BOTTOM_RIGHT.getTexture(), false, 55),
    DIRT_GRASS_SIDE_TOP_LEFT(TileTexture.DIRT_GRASS_TOP_LEFT.getTexture(), false, 56), DIRT_GRASS_SIDE_TOP_RIGHT(TileTexture.DIRT_GRASS_TOP_RIGHT.getTexture(), false, 57),
    DIRT_GRASS_CORNER_A(TileTexture.DIRT_GRASS_CORNER_A.getTexture(), false, 58), DIRT_GRASS_CORNER_B(TileTexture.DIRT_GRASS_CORNER_B.getTexture(), false, 59),
    DIRT_GRASS_CORNER_C(TileTexture.DIRT_GRASS_CORNER_C.getTexture(), false, 60), DIRT_GRASS_CORNER_D(TileTexture.DIRT_GRASS_CORNER_D.getTexture(), false, 61),

    SAND_GRASS_SIDE_LEFT(TileTexture.SAND_GRASS_LEFT.getTexture(), false, 62), SAND_GRASS_SIDE_RIGHT(TileTexture.SAND_GRASS_RIGHT.getTexture(), false, 63),
    SAND_GRASS_SIDE_BOTTOM(TileTexture.SAND_GRASS_BOTTOM.getTexture(), false, 64), SAND_GRASS_SIDE_TOP(TileTexture.SAND_GRASS_TOP.getTexture(), false, 65),
    SAND_GRASS_SIDE_BOTTOM_LEFT(TileTexture.SAND_GRASS_BOTTOM_LEFT.getTexture(), false, 66), SAND_GRASS_SIDE_BOTTOM_RIGHT(TileTexture.SAND_GRASS_BOTTOM_RIGHT.getTexture(), false, 67),
    SAND_GRASS_SIDE_TOP_LEFT(TileTexture.SAND_GRASS_TOP_LEFT.getTexture(), false, 68), SAND_GRASS_SIDE_TOP_RIGHT(TileTexture.SAND_GRASS_TOP_RIGHT.getTexture(), false, 69),
    SAND_GRASS_CORNER_A(TileTexture.SAND_GRASS_CORNER_A.getTexture(), false, 70), SAND_GRASS_CORNER_B(TileTexture.SAND_GRASS_CORNER_B.getTexture(), false, 71),
    SAND_GRASS_CORNER_C(TileTexture.SAND_GRASS_CORNER_C.getTexture(), false, 72), SAND_GRASS_CORNER_D(TileTexture.SAND_GRASS_CORNER_D.getTexture(), false, 73),

    SAND_WATER_SIDE_LEFT(TileTexture.SAND_WATER_LEFT.getTexture(), false, 74), SAND_WATER_SIDE_RIGHT(TileTexture.SAND_WATER_RIGHT.getTexture(), false, 75),
    SAND_WATER_SIDE_BOTTOM(TileTexture.SAND_WATER_BOTTOM.getTexture(), false, 76), SAND_WATER_SIDE_TOP(TileTexture.SAND_WATER_TOP.getTexture(), false, 77),
    SAND_WATER_SIDE_BOTTOM_LEFT(TileTexture.SAND_WATER_BOTTOM_LEFT.getTexture(), false, 78), SAND_WATER_SIDE_BOTTOM_RIGHT(TileTexture.SAND_WATER_BOTTOM_RIGHT.getTexture(), false, 79),
    SAND_WATER_SIDE_TOP_LEFT(TileTexture.SAND_WATER_TOP_LEFT.getTexture(), false, 80), SAND_WATER_SIDE_TOP_RIGHT(TileTexture.SAND_WATER_TOP_RIGHT.getTexture(), false, 81),
    SAND_WATER_CORNER_A(TileTexture.SAND_WATER_CORNER_A.getTexture(), false, 82), SAND_WATER_CORNER_B(TileTexture.SAND_WATER_CORNER_B.getTexture(), false, 83),
    SAND_WATER_CORNER_C(TileTexture.SAND_WATER_CORNER_C.getTexture(), false, 84), SAND_WATER_CORNER_D(TileTexture.SAND_WATER_CORNER_D.getTexture(), false, 85),

    STONE_GRASS_SIDE_LEFT(TileTexture.STONE_GRASS_LEFT.getTexture(), true, 86), STONE_GRASS_SIDE_RIGHT(TileTexture.STONE_GRASS_RIGHT.getTexture(), true, 87),
    STONE_GRASS_SIDE_BOTTOM(TileTexture.STONE_GRASS_BOTTOM.getTexture(), true, 88), STONE_GRASS_SIDE_TOP(TileTexture.STONE_GRASS_TOP.getTexture(), true, 89),
    STONE_GRASS_SIDE_BOTTOM_LEFT(TileTexture.STONE_GRASS_BOTTOM_LEFT.getTexture(), true, 90), STONE_GRASS_SIDE_BOTTOM_RIGHT(TileTexture.STONE_GRASS_BOTTOM_RIGHT.getTexture(), true, 91),
    STONE_GRASS_SIDE_TOP_LEFT(TileTexture.STONE_GRASS_TOP_LEFT.getTexture(), true, 92), STONE_GRASS_SIDE_TOP_RIGHT(TileTexture.STONE_GRASS_TOP_RIGHT.getTexture(), true, 93),
    STONE_GRASS_CORNER_A(TileTexture.STONE_GRASS_CORNER_A.getTexture(), true, 94), STONE_GRASS_CORNER_B(TileTexture.STONE_GRASS_CORNER_B.getTexture(), true, 95),
    STONE_GRASS_CORNER_C(TileTexture.STONE_GRASS_CORNER_C.getTexture(), true, 96), STONE_GRASS_CORNER_D(TileTexture.STONE_GRASS_CORNER_D.getTexture(), true, 97),

    GRASS_WATER_SIDE_LEFT(TileTexture.GRASS_WATER_LEFT.getTexture(), false, 98), GRASS_WATER_SIDE_RIGHT(TileTexture.GRASS_WATER_RIGHT.getTexture(), false, 99),
    GRASS_WATER_SIDE_BOTTOM(TileTexture.GRASS_WATER_BOTTOM.getTexture(), false, 100), GRASS_WATER_SIDE_TOP(TileTexture.GRASS_WATER_TOP.getTexture(), false, 101),
    GRASS_WATER_SIDE_BOTTOM_LEFT(TileTexture.GRASS_WATER_BOTTOM_LEFT.getTexture(), false, 102), GRASS_WATER_SIDE_BOTTOM_RIGHT(TileTexture.GRASS_WATER_BOTTOM_RIGHT.getTexture(), false, 103),
    GRASS_WATER_SIDE_TOP_LEFT(TileTexture.GRASS_WATER_TOP_LEFT.getTexture(), false, 104), GRASS_WATER_SIDE_TOP_RIGHT(TileTexture.GRASS_WATER_TOP_RIGHT.getTexture(), false, 105),
    GRASS_WATER_CORNER_A(TileTexture.GRASS_WATER_CORNER_A.getTexture(), false, 106), GRASS_WATER_CORNER_B(TileTexture.GRASS_WATER_CORNER_B.getTexture(), false, 107),
    GRASS_WATER_CORNER_C(TileTexture.GRASS_WATER_CORNER_C.getTexture(), false, 108), GRASS_WATER_CORNER_D(TileTexture.GRASS_WATER_CORNER_D.getTexture(), false, 109),


    ;

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
