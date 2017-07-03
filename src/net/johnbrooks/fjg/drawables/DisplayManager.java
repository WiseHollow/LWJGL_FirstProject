package net.johnbrooks.fjg.drawables;

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

    private static int WIDTH;
    private static int HEIGHT;

    public DisplayManager(int WIDTH, int HEIGHT)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public static int getScreenHeight() { return HEIGHT; }
    public static int getScreenWidth() { return WIDTH; }

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

        // Enable blending
        glEnable(GL_BLEND);
        // Allow for alpha channel
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Load fonts
        Draw.initFonts();
    }

    public void draw()
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.3921568f, 0.5843137f, 0.9294117f, 1f);
    }

    public void update()
    {
        Display.sync(FPS_LIMIT);
        Display.update();
    }

    public void close()
    {
        Display.destroy();
    }
}
