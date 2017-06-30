package net.johnbrooks.fjg;

import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.tiles.TileType;
import net.johnbrooks.fjg.level.TileGrid;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.util.Log;

/**
 * Created by ieatl on 6/30/2017.
 */
public class Player
{
    private TileGrid tileGrid;
    private GameMode gameMode;
    private TileType brush;

    public Player(TileGrid tileGrid)
    {
        this.tileGrid = tileGrid;
        this.gameMode = GameMode.CREATIVE;
        this.brush = TileType.GRASS;
    }

    public void setTile(TileType type)
    {
        tileGrid.setTile((int) (Mouse.getX() / 64f),
                (int) ((DisplayManager.getScreenHeight() - Mouse.getY() - 1f) / 64f), type);
    }

    public void update()
    {
        while (Keyboard.next())
        {
            if (Keyboard.getEventKeyState())
            {
                if (Keyboard.getEventKey() == Keyboard.KEY_M)
                {
                    int ordinal = gameMode.ordinal();
                    ordinal++;
                    if (ordinal >= GameMode.values().length)
                        ordinal = 0;
                    gameMode = GameMode.values()[ordinal];
                    Log.info("Set GameMode to: " + gameMode.name());
                }
                else if (Keyboard.getEventKey() == Keyboard.KEY_P)
                {
                    Clock.pause();
                }
            }
        }

        if (gameMode == GameMode.CREATIVE)
        {
            if (Mouse.isButtonDown(0))
                setTile(brush);
            int dWheel = Mouse.getDWheel();
            if (dWheel != 0)
            {
                int ordinal = TileType.valueOf(brush.name()).ordinal();
                if (dWheel > 0)
                    ordinal++;
                else if (dWheel < 0)
                    ordinal--;
                if (ordinal >= TileType.values().length)
                    ordinal = 0;
                else if (ordinal < 0)
                    ordinal = TileType.values().length - 1;

                brush = TileType.values()[ordinal];
                Log.info("Selecting: " + brush.name());
            }
        }
    }

    public void draw()
    {

    }

    public enum GameMode
    {
        NORMAL, CREATIVE
    }
}
