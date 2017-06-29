package net.johnbrooks.fjg.drawables;

/**
 * Created by ieatl on 6/29/2017.
 */
public enum TileType
{
    GRASS("grass", true), DIRT("dirt", true);

    String textureName;
    boolean buildable;

    TileType(String textureName, boolean buildable)
    {
        this.textureName = textureName;
        this.buildable = buildable;
    }

    public String getTextureName() { return textureName; }
    public boolean isBuildable() { return buildable; }
}
