package net.johnbrooks.fjg.drawables.tiles;

import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.IDrawable;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

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
        this.texture = Draw.loadTileTexture(tileType.textureName);
    }

    @Override
    public void draw()
    {
        Draw.drawTexture(texture, x, y);
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
