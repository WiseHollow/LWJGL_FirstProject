package net.johnbrooks.fjg.drawables;

import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 6/28/2017.
 */
public class Tile implements IDrawable
{
    private float x, y, width, height;
    private Texture texture;

    public Tile(float x, float y, float width, float height, Texture texture)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }

    @Override
    public void draw()
    {

    }

    @Override
    public void update()
    {

    }
}
