package net.johnbrooks.fjg;

import org.lwjgl.opengl.Display;

/**
 * Created by ieatl on 6/26/2017.
 */
public class Main
{
    public static Game game;

    public static void main(String[] args)
    {
        game = new Game();
        game.init();

        while (!Display.isCloseRequested())
        {
            game.draw();
            game.update();
        }

        game.exit();
    }
}
