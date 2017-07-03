package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.drawables.tower.IceTowerCannon;
import net.johnbrooks.fjg.drawables.tower.TowerCannon;
import net.johnbrooks.fjg.level.Level;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/3/2017.
 */
public class BuildUI extends UI
{
    private Level level;
    private Texture backgroundTexture;
    private int x, y, width, height;
    private boolean visible;

    private boolean isMouse0Down;

    public BuildUI(Level level, int x, int y)
    {
        super();
        this.level = level;
        this.backgroundTexture = Draw.loadTexture("res/hud/buildWindow.png");
        this.x = x;
        this.y = y;
        this.width = backgroundTexture.getImageWidth();
        this.height = backgroundTexture.getImageHeight();
        this.visible = false;
        this.isMouse0Down = false;

        Button buildBasicTower = new Button("basic", GameTexture.CANNON_BASE.getTexture(), 0, 0).setOnClickEvent(() ->
        {
            System.out.println("Clicked basic cannon");
            //TODO: Dynamic cannon stats
            final TowerCannon tower = new TowerCannon
                    (level, GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(),
                            level.getPlayer().getSelectedTile(), 10, 3, 256, level.getWaveManager().getEnemyList());

            if (level.getPlayer().setTower(tower) && level.getPlayer().modifyCoins(-20))
                visible = false;
        });
        Button buildIceTower = new Button("ice", GameTexture.ICE_CANNON_BASE.getTexture(), 0, 0).setOnClickEvent(() ->
        {
            //TODO: Dynamic cannon stats
            final IceTowerCannon tower = new IceTowerCannon
                    (level, GameTexture.ICE_CANNON_BASE.getTexture(), GameTexture.ICE_CANNON_GUN.getTexture(),
                            level.getPlayer().getSelectedTile(), 3, 3, 256, level.getWaveManager().getEnemyList(), 0.1f);

            if (level.getPlayer().setTower(tower) && level.getPlayer().modifyCoins(-30))
                visible = false;
        });



        addButtons(buildBasicTower, buildIceTower);
    }

    @Override
    public void draw()
    {
        if (visible)
        {
            if (backgroundTexture != null)
                Draw.drawTexture(backgroundTexture, x, y, backgroundTexture.getImageWidth(), backgroundTexture.getImageHeight());
            super.draw();
        }
    }

    @Override
    public void update()
    {
        // Check whether we need to display BuildUI and move all the elements accordingly.
        if (Mouse.isButtonDown(0) && !isMouse0Down)
        {
            // Get image and position info
            int padding = 32;
            int clickX = Mouse.getX();
            int clickY = DisplayManager.getScreenHeight() - Mouse.getY();

            // If not visible, lets make it so.
            if (!visible)
            {
                // Not visible. Update position and show as visible.
                Tile tile = level.getTileGrid().getTile((float) clickX, (float) clickY);
                x = tile.getX() + 64;
                y = tile.getY();

                for (int i = 0; i < buttonList.size(); i++)
                {
                    Button button = buttonList.get(i);
                    button.setY(y + padding);
                    button.setX(x + (i * 64) + padding);
                }

                visible = true;
            }
            else
            {
                // Visible. Check if within UI border, if not, mark as invisible.

                if (clickX < x || clickX > x + width || clickY < y || clickY > y + height)
                    visible = false;
            }
        }

        // Check for click updates
        isMouse0Down = Mouse.isButtonDown(0);

        if (visible)
        {
            super.update();
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public boolean isVisible()
    {
        return visible;
    }
}
