package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.Player;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
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

    public void init()
    {
        clearButtons();

        Button settingsButton = new Button(DisplayManager.getScreenWidth() - 77, DisplayManager.getScreenHeight() - 75, Draw.loadTexture("res/hud/nav_settings.png"))
                .setOnClickEvent(() ->
                {
                    settingsGUI.setVisible(!settingsGUI.isVisible());
                });

        addButtons(settingsButton);

        if (level.getPlayer().getGameMode() == Player.GameMode.NORMAL)
        {
            initNormal();
        }
        else if (level.getPlayer().getGameMode() == Player.GameMode.EDIT)
        {
            initEdit();
        }
    }

    private void initNormal()
    {
        int buttonX = 448;
        for (int i = 0; i < TowerType.values().length; i++)
        {
            final int index = i;
            Button button = new ButtonPurchase(buttonX, DisplayManager.getScreenHeight() - 64, TowerType.values()[i].getTowerStats().getTextures(), level.getPlayer(), TowerType.values()[i].getTowerStats().getCost());
            button.setOnClickEvent(() ->
            {
                Tower tower = new Tower(TowerType.values()[index], level, level.getPlayer().getSelectedTile(), level.getWaveManager().getEnemyList());
                level.getPlayer().setTowerToPlace(tower);
            });
            addButtons(button);
            buttonX+=64;
        }
        Button pauseAndPlayButton = new ButtonToggle(DisplayManager.getScreenWidth() - 155, DisplayManager.getScreenHeight() - 75, Draw.loadTexture("res/hud/nav_pause.png"), Draw.loadTexture("res/hud/nav_play.png"))
                .setOnClickEvent(() -> Clock.pause());

        addButtons(pauseAndPlayButton);
    }

    public void initEdit()
    {

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
