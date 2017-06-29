package net.johnbrooks.fjg;

import net.johnbrooks.fjg.levels.Level1Easy;

/**
 * Created by ieatl on 6/28/2017.
 */
public class Game
{
    private static Game game;
    public static Game getGame()
    {
        if (game == null)
            game = new Game();
        return game;
    }

    private DisplayManager displayManager;

    private Level1Easy level1Easy;

    private Game()
    {
        displayManager = new DisplayManager(1280, 960);
    }

    /**
     * Setup variables and needed GUI for game-play. This should only be called once.
     */
    public void init()
    {
        displayManager.init();
        level1Easy = new Level1Easy();
    }

    /**
     * Loop through all drawable objects in the game, and use the draw method.
     */
    public void draw()
    {
        displayManager.draw();
        level1Easy.draw();
    }

    /**
     * Loop through all drawable objects in the game, and use the update method.
     */
    public void update()
    {
        displayManager.update();
    }

    /**
     * Cleanup all resources and proceed by removing the display.
     */
    public void exit()
    {
        displayManager.close();
    }
}
