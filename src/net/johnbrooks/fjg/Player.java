package net.johnbrooks.fjg;

import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.drawables.tiles.TileType;
import net.johnbrooks.fjg.drawables.tower.Tower;
import net.johnbrooks.fjg.drawables.tower.TowerCannon;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.level.TileGrid;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 6/30/2017.
 */
public class Player
{
    private Level level;
    private TileGrid tileGrid;
    private GameMode gameMode;
    private TileType brush;
    private List<Tower> towerList;

    public Player(Level level)
    {
        this.level = level;
        this.tileGrid = level.getTileGrid();
        this.gameMode = GameMode.NORMAL;
        this.brush = TileType.GRASS;
        this.towerList = new ArrayList<>();
        this.towerList.add(new TowerCannon(level, GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(), tileGrid.getTile(1, 1), 10, 3, 128, level.getWaveManager().getEnemyList()));
    }

    private Tower getTower(int slotX, int slotY)
    {
        for (Tower tower : towerList)
            if (tower.getSlotX() == slotX && tower.getSlotY() == slotY)
                return tower;
        return null;
    }

    public void setTile(TileType type)
    {
        tileGrid.setTile((int) (Mouse.getX() / 64f),
                (int) ((DisplayManager.getScreenHeight() - Mouse.getY() - 1f) / 64f), type);
    }

    public void update()
    {
        for (Tower tower : towerList)
            tower.update();

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
                else if (Keyboard.getEventKey() == Keyboard.KEY_LEFT)
                {
                    Clock.changeMultiplier(-0.2f);
                }
                else if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT)
                {
                    Clock.changeMultiplier(0.2f);
                }
            }
        }

        if (gameMode == GameMode.NORMAL)
        {
            if (Mouse.isButtonDown(0))
            {
                int x = (int) (Mouse.getX() / 64f);
                int y = (int) ((DisplayManager.getScreenHeight() - Mouse.getY() - 1f) / 64f);

                Tile tile = tileGrid.getTile(x, y);
                if (tile != null && tile.getTileType().isBuildable())
                {
                    if (getTower(x, y) == null)
                    {
                        TowerCannon cannon = new TowerCannon(level, GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(), tileGrid.getTile(x, y), 10, 3, 256, level.getWaveManager().getEnemyList());
                        towerList.add(cannon);
                    }
                }
            }
        }
        else if (gameMode == GameMode.CREATIVE)
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
        for (Tower tower : towerList)
            tower.draw();
    }

    public enum GameMode
    {
        NORMAL, CREATIVE
    }
}
