package net.johnbrooks.fjg;

import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.level.levels.Level1Easy;

/**
 * Created by ieatl on 6/28/2017.
 */
public class Game
{
    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;

    private static Game game;
    public static Game getGame()
    {
        if (game == null)
            game = new Game();
        return game;
    }

    private Level level1Easy;

    private Game()
    {

    }

    /**
     * Setup variables and needed GUI for game-play. This should only be called once.
     */
    public void init()
    {
        level1Easy = new Level1Easy();
        level1Easy.init();
    }

    /**
     * Loop through all drawable objects in the game, and use the draw method.
     */
    public void draw()
    {
        level1Easy.draw();
    }

    /**
     * Loop through all drawable objects in the game, and use the update method.
     */
    public void update()
    {
        Clock.update();
        level1Easy.update();
    }

    /**
     * Cleanup all resources
     */
    public void exit()
    {

    }
}
