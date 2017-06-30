package net.johnbrooks.fjg;

import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import net.johnbrooks.fjg.levels.Level;
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
