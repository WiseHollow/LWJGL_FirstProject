package net.johnbrooks.fjg.drawables;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by ieatl on 6/28/2017.
 */
public class Tile implements IDrawable
{
    public static Texture loadTexture(String name)
    {
        Texture texture = null;

        InputStream inputStream = ResourceLoader.getResourceAsStream("res/tiles/" + name);
        try
        {
            texture = TextureLoader.getTexture("PNG", inputStream);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return texture;
    }

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
        texture.bind();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex2f(0, 0);

        glTexCoord2f(1, 0);
        glVertex2f(width, 0);

        glTexCoord2f(1, 1);
        glVertex2f(width, height);

        glTexCoord2f(0, 1);
        glVertex2f(0, height);

        glEnd();
        glLoadIdentity();
    }

    @Override
    public void update()
    {

    }
}
