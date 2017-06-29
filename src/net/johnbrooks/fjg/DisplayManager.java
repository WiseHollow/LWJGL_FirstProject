package net.johnbrooks.fjg;

import net.johnbrooks.fjg.drawables.IDrawable;
import net.johnbrooks.fjg.drawables.Line;
import net.johnbrooks.fjg.drawables.Rectangle;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by ieatl on 6/26/2017.
 */
public class DisplayManager
{
    private static final int FPS_LIMIT = 60;

    private int WIDTH;
    private int HEIGHT;

    public DisplayManager(int WIDTH, int HEIGHT)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public void init()
    {
        // Create display
        DisplayMode displayMode = new DisplayMode(WIDTH, HEIGHT);

        try
        {
            Display.setDisplayMode(displayMode);
            Display.create();
        } catch (LWJGLException e)
        {
            e.printStackTrace();
        }

        // Begin session
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
    }

    public void draw()
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.3921568f, 0.5843137f, 0.9294117f, 1f);

        for (IDrawable drawable : Game.getGame().getLayer0())
            drawable.draw();
        for (IDrawable drawable : Game.getGame().getLayer1())
            drawable.draw();
        for (IDrawable drawable : Game.getGame().getLayer2())
            drawable.draw();
    }

    public void update()
    {
        Display.sync(FPS_LIMIT);
        Display.update();

        for (IDrawable drawable : Game.getGame().getLayer0())
            drawable.update();
        for (IDrawable drawable : Game.getGame().getLayer1())
            drawable.update();
        for (IDrawable drawable : Game.getGame().getLayer2())
            drawable.update();
    }

    public void close()
    {
        Display.destroy();
    }
}
