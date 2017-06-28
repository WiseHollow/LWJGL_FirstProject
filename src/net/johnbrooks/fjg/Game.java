package net.johnbrooks.fjg;

import net.johnbrooks.fjg.drawables.IDrawable;

import java.util.ArrayList;
import java.util.List;

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
    private List<IDrawable> drawables = new ArrayList<>();

    private Game()
    {
        displayManager = new DisplayManager(1280, 960);
    }

    public void init()
    {
        displayManager.init();
    }

    /**
     * Loop through all drawable objects in the game, and use the draw method.
     */
    public void draw()
    {
        displayManager.draw();
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
        drawables.clear();
        displayManager.close();
    }

    public List<IDrawable> getDrawables() { return drawables; }
}
