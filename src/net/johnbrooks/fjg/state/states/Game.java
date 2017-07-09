package net.johnbrooks.fjg.state.states;

import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.level.LevelManager;
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

    private LevelManager levelManager;

    /**
     * Setup variables and needed GUI for game-play.
     */
    private Game()
    {
        levelManager = new LevelManager();
    }

    public LevelManager getLevelManager() { return levelManager; }
    public void resetLevelManager() { levelManager = new LevelManager(); }
    public void resetLevelManager(Level level) { levelManager = new LevelManager(level); }

    /**
     * Loop through all drawable objects in the game, and use the draw method.
     */
    @Override
    public void draw()
    {
        levelManager.getCurrentLevel().draw();
    }

    /**
     * Loop through all drawable objects in the game, and use the update method.
     */
    @Override
    public void update()
    {
        levelManager.getCurrentLevel().update();
    }

    /**
     * Cleanup all resources
     */
    @Override
    public void exit()
    {

    }
}
