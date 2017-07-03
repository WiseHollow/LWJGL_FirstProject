package net.johnbrooks.fjg;

import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.state.StateManager;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.util.Log;

/**
 * Created by ieatl on 6/26/2017.
 */
public class Main
{
    private static DisplayManager displayManager;
    private static StateManager stateManager;
    private static Scheduler scheduler;

    public static void main(String[] args)
    {
        init();

        while (!Display.isCloseRequested())
        {
            update();
            draw();
        }

        displayManager.close();
    }

    private static void init()
    {
        scheduler = Scheduler.getInstance();
        displayManager = new DisplayManager(1280, 960);
        displayManager.init();
        stateManager = StateManager.getInstance();

        Scheduler.getInstance().doTaskLater(() -> Log.info("5 seconds"), 5f);
    }

    private static void update()
    {
        scheduler.update();
        displayManager.update();
        stateManager.update();
    }

    private static void draw()
    {
        displayManager.draw();
        stateManager.draw();
    }
}
