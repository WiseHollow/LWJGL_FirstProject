package net.johnbrooks.fjg;

import org.lwjgl.opengl.Display;

/**
 * Created by ieatl on 6/26/2017.
 */
public class Main
{
    public static void main(String[] args)
    {
        Game game = Game.getGame();
        game.init();

        while (!Display.isCloseRequested())
        {
            game.draw();
            game.update();
        }

        game.exit();
    }
}
