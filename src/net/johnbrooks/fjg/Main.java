package net.johnbrooks.fjg;

import org.lwjgl.opengl.Display;

/**
 * Created by ieatl on 6/26/2017.
 */
public class Main
{
    private static DisplayManager displayManager;
    private static Game game;
    public static void main(String[] args)
    {
        init();

        while (!Display.isCloseRequested())
        {
            update();
            draw();
        }

        game.exit();
        displayManager.close();
    }

    private static void init()
    {
        displayManager = new DisplayManager(1280, 960);
        displayManager.init();
        game = Game.getGame();
        game.init();
    }

    private static void update()
    {
        displayManager.update();
        game.update();
    }

    private static void draw()
    {
        displayManager.draw();
        game.draw();
    }
}
