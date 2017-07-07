package net.johnbrooks.fjg;

import net.johnbrooks.fjg.achievements.Achievement;
import net.johnbrooks.fjg.achievements.AchievementManager;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.drawables.tiles.TileType;
import net.johnbrooks.fjg.drawables.tower.Tower;
import net.johnbrooks.fjg.drawables.tower.TowerType;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.drawables.tiles.TileGrid;
import net.johnbrooks.fjg.level.levels.LevelEditor;
import net.johnbrooks.fjg.ui.elements.ImageBox;
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
    private AchievementManager achievementManager;

    private Level level;
    private TileGrid tileGrid;
    private GameMode gameMode;
    private TileType brush;
    private List<Tower> towerList;
    private int health, coins;
    private Texture gridSelectionTexture, gridIllegalSelectionTexture, viewDistanceTexture, viewDistanceIllegalPlacement;
    private Tile selectedTile;

    private Tower towerToPlace, selectedTower;
    private List<ImageBox> imageBoxList;

    private List<TowerType> towerBuildHistory;

    public Player(Level level)
    {
        this.achievementManager = new AchievementManager(this);

        this.level = level;
        this.tileGrid = level.getTileGrid();
        this.gameMode = GameMode.NORMAL;
        this.brush = TileType.GRASS;
        this.towerList = new ArrayList<>();
        this.health = 10;
        this.coins = 50;
        this.gridSelectionTexture = Draw.loadTexture("res/general/gridSelection.png");
        this.gridIllegalSelectionTexture = Draw.loadTexture("res/general/gridIllegalSelection.png");
        this.viewDistanceTexture = GameTexture.VIEW_DISTANCE.getTexture();
        this.viewDistanceIllegalPlacement = GameTexture.VIEW_DISTANCE_ILLEGAL.getTexture();
        this.selectedTile = null;
        this.towerToPlace = null;
        this.selectedTower = null;
        this.imageBoxList = new ArrayList<>();
        this.towerBuildHistory = new ArrayList<>();
        //this.buildUI = new BuildUI(level, 0, 0);
        //this.towerList.add(new TowerCannon(level, GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(), tileGrid.getTile(1, 1), 10, 3, 128, level.getWaveManager().getEnemyList()));
    }

    public AchievementManager getAchievementManager()
    {
        return achievementManager;
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

        modifyCoins(-tower.getCost());
        towerList.add(tower);
        tower.setTile(selectedTile);

        // Keep a record of all towers built.
        if (!towerBuildHistory.contains(tower.getTowerType()))
            towerBuildHistory.add(tower.getTowerType());

        // Check for achievement of building a tracking tower.
        if (tower.getTowerType() == TowerType.TRACKING_TOWER && achievementManager.giveAchievement(Achievement.BLASTER))
            addImageBoxDisplay(new ImageBox(Draw.loadTexture("res/achievements/blaster.png"), 2, tower.getX(), tower.getY()));

        // Check for achievement of building all the kinds of towers.
        if (towerBuildHistory.size() == TowerType.values().length && achievementManager.giveAchievement(Achievement.TOWER_O_PLENTY))
            addImageBoxDisplay(new ImageBox(Draw.loadTexture("res/achievements/towersOPlenty.png"), 2, tower.getX(), tower.getY()));

        return true;
    }

    public void paint()
    {
        tileGrid.setTile((int) (Mouse.getX() / 64f),
                (int) ((DisplayManager.getScreenHeight() - Mouse.getY() - 1f) / 64f), brush);
    }

    public void setTowerToPlace(Tower tower)
    {
        this.towerToPlace = tower;
    }
    public Tower getSelectedTower() { return selectedTower; }
    public void setSelectedTower(Tower tower)
    {
        this.selectedTower = tower;
        this.level.getHudGUI().setTowerButtons(tower);
    }

    public void setGameMode(GameMode gameMode)
    {
        this.gameMode = gameMode;
        this.level.getHudGUI().init();
    }

    public void update()
    {
        // Update temporary image boxes
        for (int i = 0; i <imageBoxList.size(); i++)
        {
            ImageBox imageBox = imageBoxList.get(i);
            if (!imageBox.isAlive())
            {
                imageBoxList.remove(imageBox);
                i--;
            }
            else
                imageBox.update();
        }
        // Select which tile to highlight blue.
        selectedTile = tileGrid.getTile((float) Mouse.getX(), (float) (DisplayManager.getScreenHeight() - Mouse.getY()));

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
                    setGameMode(GameMode.values()[ordinal]);
                    Log.info("Set GameMode to: " + gameMode.name());
                }
                else if (Keyboard.getEventKey() == Keyboard.KEY_F1)
                {
                    level.getHudGUI().setVisible(!level.getHudGUI().isVisible());
                }
                else if (Keyboard.getEventKey() == Keyboard.KEY_C)
                {
                    modifyCoins(10);
                }
                else if (Keyboard.getEventKey() == Keyboard.KEY_S)
                {
                    if (level instanceof LevelEditor)
                        level.save();
                    else
                        level.setComplete(true);
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
            // Update each tower
            for (int i = 0; i < towerList.size(); i++)
            {
                Tower tower = towerList.get(i);
                if (tower.isToRemove())
                {
                    if (selectedTower == tower)
                        setSelectedTower(null);
                    towerList.remove(tower);
                    i--;
                }
                else
                    tower.update();
            }
            // Remove towerToPlace if right click.
            if (Mouse.isButtonDown(1))
                towerToPlace = null;
            else if (GameInput.getInstance().isButtonDown(0) && towerToPlace == null)
            {
                // We are clicking without wanting to place a tower.

                // Check if we are clicking on a tower...

                boolean found = false;
                for (Tower tower : towerList)
                {
                    if (tower.getTile() == selectedTile)
                    {
                        // Found a tower we are clicking on...
                        setSelectedTower(tower);
                        found = true;
                        break;
                    }
                }
                // if we aren't clicking on a tower, deselect.
                if (found == false)
                    setSelectedTower(null);
            }
            else if (GameInput.getInstance().isButtonDown(0) && towerToPlace != null)
            {
                // Place a tower if we have one to place.
                int x = (int) (Mouse.getX() / 64f);
                int y = (int) ((DisplayManager.getScreenHeight() - Mouse.getY() - 1f) / 64f);

                if (coins >= towerToPlace.getCost() && getTower(x, y) == null && y < TileGrid.TILES_HIGH - 1)
                {
                    if (setTower(towerToPlace))
                        towerToPlace = null;
                }
            }

            if (towerToPlace != null)
            {
                towerToPlace.setTile(selectedTile);
            }
        }
        else if (gameMode == GameMode.EDIT)
        {
            if (Mouse.isButtonDown(0))
                paint();
            else if (GameInput.getInstance().isButtonDown(1))
                brush = selectedTile.getTileType();
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


        if (selectedTower != null)
        {
            int size = selectedTower.getTotalViewDistance() * 2;

            Draw.drawTexture(viewDistanceTexture, (int) (selectedTower.getX() - (size * 0.5f) + 32), (int) (selectedTower.getY() - (size * 0.5f) + 32),
                    size, size);
        }
        else if (selectedTile != null && level.getHudGUI().isVisible())
        {
            if (towerToPlace == null)
                Draw.drawTexture((selectedTile.getYSlot() < TileGrid.TILES_HIGH - 1 && selectedTile.getTileType().isBuildable())
                        ? gridSelectionTexture : gridIllegalSelectionTexture, selectedTile.getX(), selectedTile.getY(), 0);
            else
            {
                int size = towerToPlace.getTowerType().getTowerStats().getViewDistance() * 2;

                Draw.drawTexture(towerToPlace.getTile().getTileType().isBuildable() ? viewDistanceTexture : viewDistanceIllegalPlacement, towerToPlace.getX() - towerToPlace.getTowerType().getTowerStats().getViewDistance() + 32, towerToPlace.getY() - towerToPlace.getTowerType().getTowerStats().getViewDistance() + 32,
                        size, size);

                towerToPlace.draw();
            }
        }

        for (ImageBox imageBox : imageBoxList)
            imageBox.draw();
    }

    public void setBrush(TileType tileType)
    {
        this.brush = tileType;
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

    public GameMode getGameMode() { return gameMode; }

    public void addImageBoxDisplay(ImageBox imageBox)
    {
        Scheduler.getInstance().doTaskLater(() -> imageBoxList.add(imageBox), 0.01f);
    }

    public enum GameMode
    {
        NORMAL, EDIT
    }
}
