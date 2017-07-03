package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.tower.IceTowerCannon;
import net.johnbrooks.fjg.drawables.tower.TowerCannon;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.ui.buttons.Button;
import net.johnbrooks.fjg.ui.buttons.ButtonLayered;
import net.johnbrooks.fjg.ui.buttons.ButtonPurchase;
import net.johnbrooks.fjg.ui.buttons.ButtonToggle;
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
        final int basicTowerCost = 10;
        Texture[] basicTowerTextures = new Texture[] { GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture() };
        Button buildBasicTower = new ButtonPurchase("basic", 448, DisplayManager.getScreenHeight() - 64, basicTowerTextures, level.getPlayer(), basicTowerCost).setOnClickEvent(() ->
        {
            //TODO: Dynamic cannon stats
            final TowerCannon tower = new TowerCannon
                    (
                            level, GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(),
                            level.getPlayer().getSelectedTile(), 3, basicTowerCost, 3, 256, level.getWaveManager().getEnemyList()
                    );
            if (level.getPlayer().getCoins() >= basicTowerCost)
            {
                level.getPlayer().setTowerToPlace(tower);
            }
        });
        final int iceTowerCost = 10;
        Texture[] iceTowerTextures = new Texture[] { GameTexture.ICE_CANNON_BASE.getTexture(), GameTexture.ICE_CANNON_GUN.getTexture() };
        Button buildIceTower = new ButtonPurchase("ice", 512, DisplayManager.getScreenHeight() - 64, iceTowerTextures, level.getPlayer(), iceTowerCost).setOnClickEvent(() ->
        {
            //TODO: Dynamic cannon stats
            final IceTowerCannon tower = new IceTowerCannon
                    (
                            level, GameTexture.ICE_CANNON_BASE.getTexture(), GameTexture.ICE_CANNON_GUN.getTexture(),
                            level.getPlayer().getSelectedTile(), 1, iceTowerCost, 5, 256, level.getWaveManager().getEnemyList(), 0.1f
                    );

            if (level.getPlayer().getCoins() > iceTowerCost)
            {
                level.getPlayer().setTowerToPlace(tower);
            }
        });

        Button pauseAndPlayButton = new ButtonToggle("pausePlay", DisplayManager.getScreenWidth() - 100, DisplayManager.getScreenHeight() - 100, Draw.loadTexture("res/hud/nav_pause.png"), Draw.loadTexture("res/hud/nav_play.png"))
                .setOnClickEvent(() -> Clock.pause());

        addButtons(buildBasicTower, buildIceTower, pauseAndPlayButton);
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
