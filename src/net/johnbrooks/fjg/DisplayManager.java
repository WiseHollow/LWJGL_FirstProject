package net.johnbrooks.fjg;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by ieatl on 6/26/2017.
 */
public class DisplayManager
{
    private static final int FPS_LIMIT = 60;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public static void create()
    {
        DisplayMode displayMode = new DisplayMode(WIDTH, HEIGHT);

        try
        {
            Display.setDisplayMode(displayMode);
            Display.create();
        } catch (LWJGLException e)
        {
            e.printStackTrace();
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    }

    public static void draw()
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glColor3f(0.8f, 0.3f, 0.9f);

        glBegin(GL_LINES);
        glVertex2f(10, 10);
        glVertex2f(50, 10);
        glEnd();

        glBegin(GL_QUADS);
        glVertex2f(100, 100);
        glVertex2f(150, 100);
        glVertex2f(150, 150);
        glVertex2f(100, 150);
        glEnd();
    }

    public static void update()
    {
        Display.sync(FPS_LIMIT);
        Display.update();
    }

    public static void close()
    {
        Display.destroy();
    }
}
