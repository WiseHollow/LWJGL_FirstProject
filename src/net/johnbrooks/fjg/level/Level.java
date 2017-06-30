package net.johnbrooks.fjg.level;

/**
 * Created by ieatl on 6/29/2017.
 */
public abstract class Level
{
    protected TileGrid tileGrid;
    protected Wave wave;

    public Level()
    {

    }

    public void update()
    {
        wave.update();
    }

    public void draw()
    {
        tileGrid.draw();
        wave.draw();
    }
}
