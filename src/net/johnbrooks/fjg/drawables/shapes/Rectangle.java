package net.johnbrooks.fjg.drawables.shapes;

import net.johnbrooks.fjg.drawables.IDrawable;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by ieatl on 6/27/2017.
 */
public class Rectangle implements IDrawable
{
    private float x, y, width, height;
    private float r, g, b;

    public Rectangle(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setColor(float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return height;
    }

    @Override
    public void draw()
    {
        glColor3f(r, g, b);
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + width, y);
        glVertex2f(x + width, y + height);
        glVertex2f(x, y + height);
        glEnd();
    }

    @Override
    public void update()
    {

    }
}
