package net.johnbrooks.fjg.state.states;

import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.level.levels.Level1Easy;
import net.johnbrooks.fjg.state.IGameState;

/**
 * Created by ieatl on 6/28/2017.
 */
public class Game implements IGameState
{
    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;

    private static Game instance;
    public static Game getInstance()
    {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    private Level level1Easy;

    private Game()
    {
        init();
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
    @Override
    public void draw()
    {
        level1Easy.draw();
    }

    /**
     * Loop through all drawable objects in the game, and use the update method.
     */
    @Override
    public void update()
    {
        level1Easy.update();
    }

    /**
     * Cleanup all resources
     */
    @Override
    public void exit()
    {

    }
}
