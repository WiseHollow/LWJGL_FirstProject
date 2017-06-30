package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.Player;

/**
 * Created by ieatl on 6/29/2017.
 */
public abstract class Level
{
    protected TileGrid tileGrid;
    protected Wave wave;
    protected Player player;

    public Level()
    {

    }

    public void update()
    {
        wave.update();
        player.update();
    }

    public void draw()
    {
        tileGrid.draw();
        wave.draw();
        player.draw();
    }
}
