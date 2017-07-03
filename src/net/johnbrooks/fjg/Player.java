package net.johnbrooks.fjg;

import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.drawables.tiles.TileType;
import net.johnbrooks.fjg.drawables.tower.Tower;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.level.TileGrid;
import net.johnbrooks.fjg.ui.HudGUI;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
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
    private int health, coins;
    private Texture gridSelectionTexture;
    private Tile selectedTile;

    //private BuildUI buildUI;
    private HudGUI hudGUI;
    private Tower towerToPlace;

    public Player(Level level)
    {
        this.level = level;
        this.tileGrid = level.getTileGrid();
        this.gameMode = GameMode.NORMAL;
        this.brush = TileType.GRASS;
        this.towerList = new ArrayList<>();
        this.health = 10;
        this.coins = 50;
        this.gridSelectionTexture = Draw.loadTexture("res/general/gridSelection.png");
        this.selectedTile = null;
        this.towerToPlace = null;
        //this.buildUI = new BuildUI(level, 0, 0);
        this.hudGUI = new HudGUI(level);
        //this.towerList.add(new TowerCannon(level, GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(), tileGrid.getTile(1, 1), 10, 3, 128, level.getWaveManager().getEnemyList()));
    }

    private Tower getTower(int slotX, int slotY)
    {
        for (Tower tower : towerList)
            if (tower.getSlotX() == slotX && tower.getSlotY() == slotY)
                return tower;
        return null;
    }

    public boolean setTower(Tower tower)
    {
        if (!tower.getTile().getTileType().isBuildable())
            return false;

        for (Tower towerCheck : towerList)
            if (towerCheck.getSlotX() == tower.getSlotX() && towerCheck.getSlotY() == tower.getSlotY())
                return false;

        towerList.add(tower);
        return true;
    }

    public void setTile(TileType type)
    {
        tileGrid.setTile((int) (Mouse.getX() / 64f),
                (int) ((DisplayManager.getScreenHeight() - Mouse.getY() - 1f) / 64f), type);
    }

    public void setTowerToPlace(Tower tower)
    {
        this.towerToPlace = tower;
    }

    public void setGameMode(GameMode gameMode)
    {
        this.gameMode = gameMode;
    }

    public void update()
    {
        // Remove towerToPlace if right click.
        if (Mouse.isButtonDown(1) && towerToPlace != null)
            towerToPlace = null;
        else if (GameInput.getInstance().isButtonDown(0) && towerToPlace != null)
        {
            // Place a tower if we have one to place.
            int x = (int) (Mouse.getX() / 64f);
            int y = (int) ((DisplayManager.getScreenHeight() - Mouse.getY() - 1f) / 64f);

            if (coins >= towerToPlace.getCost() && getTower(x, y) == null)
            {
                if (setTower(towerToPlace))
                    towerToPlace = null;
            }
        }


        // Select which tile to highlight blue.
        //if (!buildUI.isVisible())
        selectedTile = tileGrid.getTile((float) Mouse.getX(), (float) (DisplayManager.getScreenHeight() - Mouse.getY()));
        if (towerToPlace != null)
        {
            towerToPlace.setTile(selectedTile);
        }

        // Update each tower
        for (Tower tower : towerList)
            tower.update();

        // Update the small window that lets you build new towers.
        //buildUI.update();
        hudGUI.update();

        // Check for keyboard input
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
                        //TowerCannon cannon = new TowerCannon
                        //        (level, GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(),
                        //                tileGrid.getTile(x, y), 10, 3, 256, level.getWaveManager().getEnemyList());

                        //Tower cannon = new IceTowerCannon
                        //        (level, GameTexture.ICE_CANNON_BASE.getTexture(), GameTexture.ICE_CANNON_GUN.getTexture(),
                        //                tileGrid.getTile(x, y), 3, 3, 256, level.getWaveManager().getEnemyList(), 0.1f);
                        //towerList.add(cannon);


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

        if (selectedTile != null)
        {
            Draw.drawTexture(gridSelectionTexture, selectedTile.getX(), selectedTile.getY(), 0);
            if (towerToPlace != null)
                towerToPlace.draw();
        }

        hudGUI.draw();
    }

    public boolean modifyCoins(int coins)
    {
        if (this.coins + coins >= 0)
        {
            this.coins += coins;
            System.out.println("Coins remaining: " + this.coins);
            return true;
        }
        return false;
    }

    public int getCoins()
    {
        return coins;
    }

    public void modifyHealth(int health)
    {
        this.health += health;
        System.out.println("Player health: " + this.health);
    }

    public int getHealth()
    {
        return health;
    }

    public Tile getSelectedTile()
    {
        return selectedTile;
    }

    public enum GameMode
    {
        NORMAL, CREATIVE
    }
}
