package net.johnbrooks.fjg.drawables.tiles;

/**
 * Created by ieatl on 7/4/2017.
 */
public enum NewTileType
{
    GRASS(0, true, 3);

    private int id;
    private int size;
    private boolean buildable;

    NewTileType(int id, boolean buildable, int size)
    {
        this.id = id;
        this.size = size;
        this.buildable = buildable;
    }

    public int getId()
    {
        return id;
    }

    public boolean isBuildable()
    {
        return buildable;
    }

    public int getSize() { return size; }
}
