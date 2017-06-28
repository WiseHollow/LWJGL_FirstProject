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

    private int WIDTH = 800;
    private int HEIGHT = 600;

    private List<IDrawable> drawableList = new ArrayList<>();

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

        // Create example drawables
        drawableList.add(new Rectangle(10, 20, 100, 30));
        drawableList.add(new Rectangle(15, 120, 100, 30));
        drawableList.add(new Line(200, 50, 200, 550));
        Rectangle rect = new Rectangle(300, 50, 100, 30);
        rect.setColor(1f, 0.3f, 0.1f);
        drawableList.add(rect);
    }

    public void draw()
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.3921568f, 0.5843137f, 0.9294117f, 1f);

        for (IDrawable drawable : drawableList)
            drawable.draw();
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
