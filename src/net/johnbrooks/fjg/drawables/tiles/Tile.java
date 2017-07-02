package net.johnbrooks.fjg.drawables.tiles;

import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.IDrawable;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 6/28/2017.
 */
public class Tile implements IDrawable
{
    private int x, y, width, height;
    private TileType tileType;
    private Texture texture;

    public Tile(int x, int y, int width, int height, TileType tileType)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tileType = tileType;
        this.texture = tileType.getTexture();
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getXSlot() { return x / 64; }
    public int getYSlot() { return y / 64; }

    @Override
    public void draw()
    {
        Draw.drawTexture(texture, x, y, 0);
    }

    @Override
    public void update()
    {

    }

    public TileType getTileType()
    {
        return tileType;
    }
}
