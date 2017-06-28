package net.johnbrooks.fjg.shapes;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 * Created by ieatl on 6/28/2017.
 */
public class Line implements IDrawable
{
    float fromX, fromY, toX, toY;
    float r, g, b;

    public Line(float fromX, float fromY, float toX, float toY)
    {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    public void setColor(float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void draw()
    {
        glColor3f(r, g, b);
        glBegin(GL_LINES);
        glVertex2f(fromX, fromY);
        glVertex2f(toX, toY);
        glEnd();
    }

    @Override
    public void update()
    {

    }
}
