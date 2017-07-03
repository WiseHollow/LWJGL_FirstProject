package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.tower.IceTowerCannon;
import net.johnbrooks.fjg.drawables.tower.TowerCannon;
import net.johnbrooks.fjg.level.Level;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/3/2017.
 */
public class HudGUI extends UI
{
    private Level level;
    private Texture backgroundTexture;
    private int x, y, width, height;
    private boolean visible;

    public HudGUI(Level level)
    {
        super();
        this.level = level;
        this.backgroundTexture = Draw.loadTexture("res/hud/ingameHud.png");
        this.x = 0;
        this.y = DisplayManager.getScreenHeight() - backgroundTexture.getImageHeight();
        this.width = backgroundTexture.getImageWidth();
        this.height = backgroundTexture.getImageHeight();
        this.visible = true;

        init();
    }

    private void init()
    {
        Button buildBasicTower = new Button("basic", 448, DisplayManager.getScreenHeight() - 64, GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture()).setOnClickEvent(() ->
        {
            //TODO: Dynamic cannon stats
            int cost = 10;
            final TowerCannon tower = new TowerCannon
                    (
                            level, GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(),
                            level.getPlayer().getSelectedTile(), 3, cost, 3, 256, level.getWaveManager().getEnemyList()
                    );
            if (level.getPlayer().getCoins() > cost)
            {
                level.getPlayer().setTowerToPlace(tower);
            }
        });
        Button buildIceTower = new Button("ice", 512, DisplayManager.getScreenHeight() - 64, GameTexture.ICE_CANNON_BASE.getTexture(), GameTexture.ICE_CANNON_GUN.getTexture()).setOnClickEvent(() ->
        {
            //TODO: Dynamic cannon stats
            int cost = 15;
            final IceTowerCannon tower = new IceTowerCannon
                    (
                            level, GameTexture.ICE_CANNON_BASE.getTexture(), GameTexture.ICE_CANNON_GUN.getTexture(),
                            level.getPlayer().getSelectedTile(), 1, cost, 5, 256, level.getWaveManager().getEnemyList(), 0.1f
                    );

            if (level.getPlayer().getCoins() > cost)
            {
                level.getPlayer().setTowerToPlace(tower);
            }
        });

        addButtons(buildBasicTower, buildIceTower);
    }

    @Override
    public void draw()
    {
        if (visible)
        {
            Draw.drawTexture(backgroundTexture, x, y, backgroundTexture.getImageWidth(), backgroundTexture.getImageHeight());
            String health = String.valueOf(level.getPlayer().getHealth());
            String coins = String.valueOf(level.getPlayer().getCoins());
            Draw.getFont().drawString(78, 893, health);
            Draw.getFont().drawString(215, 893, coins);

            super.draw();
        }
    }

    @Override
    public void update()
    {
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
