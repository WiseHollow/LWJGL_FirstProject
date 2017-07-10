package net.johnbrooks.fjg;

import net.johnbrooks.fjg.audio.AudioManager;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.state.StateManager;
import org.lwjgl.opengl.Display;

/**
 * Created by ieatl on 6/26/2017.
 */
public class Main
{
    private static AudioManager audioManager;
    private static DisplayManager displayManager;
    private static StateManager stateManager;
    private static Scheduler scheduler;
    private static GameInput gameInput;

    public static boolean debug = false;

    public static void main(String[] args)
    {
        init();

        while (!Display.isCloseRequested())
        {
            update();
            draw();
        }

        audioManager.cleanUp();
        displayManager.close();
    }

    private static void init()
    {
        Updater.checkForUpdates();
        displayManager = new DisplayManager(1280, 960);
        displayManager.init();
        gameInput = GameInput.getInstance();
        scheduler = Scheduler.getInstance();
        stateManager = StateManager.getInstance();
        audioManager = AudioManager.getInstance();
    }

    private static void update()
    {
        Clock.update();
        gameInput.update();

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
