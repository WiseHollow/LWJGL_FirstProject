package net.johnbrooks.fjg;

import net.johnbrooks.fjg.drawables.IDrawable;
import net.johnbrooks.fjg.drawables.Tile;
import net.johnbrooks.fjg.drawables.TileType;

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

    private List<IDrawable> layer0 = new ArrayList<>();
    private List<IDrawable> layer1 = new ArrayList<>();
    private List<IDrawable> layer2 = new ArrayList<>();

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

        layer0.add(new Tile(0, 0, 64, 64, TileType.GRASS));
        layer0.add(new Tile(64, 0, 64, 64, TileType.DIRT));
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
        layer0.clear();
        layer1.clear();
        layer2.clear();
        displayManager.close();
    }

    public List<IDrawable> getLayer0() { return layer0; }
    public List<IDrawable> getLayer1() { return layer1; }
    public List<IDrawable> getLayer2() { return layer2; }
}
