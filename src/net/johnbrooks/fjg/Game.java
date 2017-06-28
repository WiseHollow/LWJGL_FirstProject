package net.johnbrooks.fjg;

/**
 * Created by ieatl on 6/28/2017.
 */
public class Game
{
    private DisplayManager displayManager;

    public Game()
    {
        displayManager = new DisplayManager(1280, 960);
    }

    public void init()
    {
        displayManager.init();
    }

    public void draw()
    {
        displayManager.draw();
    }

    public void update()
    {
        displayManager.update();
    }

    public void exit()
    {
        displayManager.close();
    }
}
