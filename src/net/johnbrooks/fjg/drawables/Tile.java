package net.johnbrooks.fjg.drawables;

import org.newdawn.slick.opengl.Texture;
import static org.lwjgl.opengl.GL11.*;

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

        glLoadIdentity();
        glEnd();
    }

    @Override
    public void update()
    {

    }
}
