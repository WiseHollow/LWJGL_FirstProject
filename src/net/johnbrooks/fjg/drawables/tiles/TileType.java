package net.johnbrooks.fjg.drawables.tiles;

/**
 * Created by ieatl on 6/29/2017.
 */
public enum TileType
{
    GRASS("grass", true, 0), DIRT("dirt", true, 1), WATER("water", false, 2);

    public static TileType getTileType(final int id) throws Exception
    {
        for (TileType type : TileType.values())
            if (type.getId() == id)
                return type;
        throw new Exception("Invalid TileType id (" + id + ").");
    }

    String textureName;
    boolean buildable;
    int id;

    TileType(String textureName, boolean buildable, int id)
    {
        this.textureName = textureName;
        this.buildable = buildable;
        this.id = id;
    }

    public String getTextureName() { return textureName; }
    public boolean isBuildable() { return buildable; }
    public int getId() { return id; }
}
