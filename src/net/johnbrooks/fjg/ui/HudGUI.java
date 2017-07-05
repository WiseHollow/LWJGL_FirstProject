package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.tower.Tower;
import net.johnbrooks.fjg.drawables.tower.TowerType;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.ui.buttons.Button;
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

    private SettingsGUI settingsGUI;

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

        this.settingsGUI = new SettingsGUI(level);

        init();
    }

    private void init()
    {
        Texture[] basicTowerTextures = new Texture[] { GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture() };
        Button buildBasicTower = new ButtonPurchase("basic", 448, DisplayManager.getScreenHeight() - 64, basicTowerTextures, level.getPlayer(), TowerType.BASIC_TOWER.getTowerStats().getCost()).setOnClickEvent(() ->
        {
            //TODO: Dynamic cannon stats
            Tower tower = new Tower(TowerType.BASIC_TOWER, level, level.getPlayer().getSelectedTile(), level.getWaveManager().getEnemyList());
            level.getPlayer().setTowerToPlace(tower);
        });
        Texture[] iceTowerTextures = new Texture[] { GameTexture.ICE_CANNON_BASE.getTexture(), GameTexture.ICE_CANNON_GUN.getTexture() };
        Button buildIceTower = new ButtonPurchase("ice", 512, DisplayManager.getScreenHeight() - 64, iceTowerTextures, level.getPlayer(), TowerType.ICE_TOWER.getTowerStats().getCost()).setOnClickEvent(() ->
        {
            //TODO: Dynamic cannon stats
            Tower tower = new Tower(TowerType.ICE_TOWER, level, level.getPlayer().getSelectedTile(), level.getWaveManager().getEnemyList());
            level.getPlayer().setTowerToPlace(tower);
        });
        Texture[] speedTowerTextures = new Texture[] { GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture() };
        Button speedBasicTower = new ButtonPurchase("speed", 576, DisplayManager.getScreenHeight() - 64, speedTowerTextures, level.getPlayer(), TowerType.FAST_TOWER.getTowerStats().getCost()).setOnClickEvent(() ->
        {
            //TODO: Dynamic cannon stats
            Tower tower = new Tower(TowerType.FAST_TOWER, level, level.getPlayer().getSelectedTile(), level.getWaveManager().getEnemyList());
            level.getPlayer().setTowerToPlace(tower);
        });

        Button pauseAndPlayButton = new ButtonToggle("pausePlay", DisplayManager.getScreenWidth() - 155, DisplayManager.getScreenHeight() - 75, Draw.loadTexture("res/hud/nav_pause.png"), Draw.loadTexture("res/hud/nav_play.png"))
                .setOnClickEvent(() -> Clock.pause());

        Button settingsButton = new Button("settings", DisplayManager.getScreenWidth() - 77, DisplayManager.getScreenHeight() - 75, Draw.loadTexture("res/hud/nav_settings.png"))
                .setOnClickEvent(() ->
                {
                    settingsGUI.setVisible(!settingsGUI.isVisible());
                });

        addButtons(buildBasicTower, buildIceTower, speedBasicTower, pauseAndPlayButton, settingsButton);
    }

    @Override
    public void draw()
    {
        if (visible)
        {
            settingsGUI.draw();

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
            if (settingsGUI.visible)
            {
                settingsGUI.update();
            }

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
}
