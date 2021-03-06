package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.Main;
import net.johnbrooks.fjg.Player;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.tiles.TileType;
import net.johnbrooks.fjg.drawables.tower.Tower;
import net.johnbrooks.fjg.drawables.tower.TowerType;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.level.levels.LevelEditor;
import net.johnbrooks.fjg.ui.buttons.*;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 7/3/2017.
 */
public class HudGUI extends UI
{
    private Level level;
    private Texture backgroundTexture;
    private int x, y, width, height;

    private int editorTilePage = 0;

    private SettingsGUI settingsGUI;

    private List<List<Button>> editorPages;
    private List<Button> editorPage;

    private Button upgradeButton, sellButton;

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
        this.editorPages = new ArrayList<>();
        this.editorPage = new ArrayList<>();

        this.upgradeButton = null;
        this.sellButton = null;

        init();
    }

    public void init()
    {
        clearButtons();
        editorPages.clear();

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
            ((ButtonPurchase)button).setPurchaseType(TowerType.values()[i]);
            button.setOnClickEvent(() ->
            {
                level.getPlayer().setSelectedTower(null);
                Tower tower = new Tower(TowerType.values()[index], level, level.getPlayer().getSelectedTile(), level.getWaveManager().getEnemyList());
                level.getPlayer().setTowerToPlace(tower);
            });
            addButtons(button);
            buttonX+=64;
        }
        Texture pause = Draw.loadTexture("res/hud/nav_pause.png");
        Texture play = Draw.loadTexture("res/hud/nav_play.png");
        boolean paused = Clock.isPaused();
        Button pauseAndPlayButton = new ButtonToggle(DisplayManager.getScreenWidth() - 155, DisplayManager.getScreenHeight() - 75, !paused ? pause : play, !paused ? play : pause)
                .setOnClickEvent(() -> Clock.pause());

        upgradeButton = new ButtonUpgrade(915, DisplayManager.getScreenHeight() - 48, GameTexture.UPGRADE_BUTTON.getTexture(), level);
        upgradeButton.setOnClickEvent(() ->
        {
            if (level.getPlayer().getSelectedTower() != null)
                level.getPlayer().getSelectedTower().upgradeTower();
        });
        sellButton = new ButtonSell(955, DisplayManager.getScreenHeight() - 48, GameTexture.SELL_BUTTON.getTexture(), level);
        sellButton.setOnClickEvent(() ->
        {
            if (level.getPlayer().getSelectedTower() != null)
            {
                level.getPlayer().getSelectedTower().sellTower();
            }
        });

        addButtons(pauseAndPlayButton);
    }

    private void initEdit()
    {
        int previousX = 450;
        int nextX = 929;

        Button previous = new Button(previousX, DisplayManager.getScreenHeight() - 58, Draw.loadTexture("res/buttons/previous.png"));
        previous.setOnClickEvent(() ->
        {
            editorTilePage--;
            if (editorTilePage < 0)
                editorTilePage = 0;
            refreshEditorToolbar();
        });
        Button next = new Button(nextX, DisplayManager.getScreenHeight() - 58, Draw.loadTexture("res/buttons/next.png"));
        next.setOnClickEvent(() ->
        {
            editorTilePage++;
            if (editorTilePage >= editorPages.size())
                editorTilePage = editorPages.size() - 1;
            refreshEditorToolbar();
        });


        int buttonX = 520;

        //int pageIndex = 0;
        List<Button> page = new ArrayList<>();
        for (int i = 0; i < TileType.values().length; i++)
        {
            final int index = i;
            Button button = new Button(buttonX, DisplayManager.getScreenHeight() - 58, TileType.values()[i].getTexture());
            button.setSizePercent(0.85f);
            button.setOnClickEvent(() ->
            {
                level.getPlayer().setBrush(TileType.values()[index]);
            });

            page.add(button);
            if (page.size() == 7 || page.size() + (editorPages.size() * 7) == TileType.values().length)
            {
                List<Button> buttons = new ArrayList<>();
                buttons.addAll(page);
                editorPages.add(buttons);
                page = new ArrayList<>();
                buttonX = 520;
            }
            else
                buttonX+=58;
        }

        refreshEditorToolbar();

        Button saveButton = new Button(DisplayManager.getScreenWidth() - 155, DisplayManager.getScreenHeight() - 75, Draw.loadTexture("res/hud/nav_save.png"))
                .setOnClickEvent(() -> System.out.println(level.save() ? "Level saved successfully!" : "Failed to save level."));

        addButtons(saveButton, previous, next);
    }

    private void refreshEditorToolbar()
    {
        editorPage = editorPages.get(editorTilePage);
    }

    @Override
    public void draw()
    {
        //for (TextBox textBox : textBoxList)
        //    textBox.draw();
        if (visible)
        {
            settingsGUI.draw();

            Draw.drawTexture(backgroundTexture, x, y, backgroundTexture.getImageWidth(), backgroundTexture.getImageHeight());
            String health = String.valueOf(level.getPlayer().getHealth());
            String coins = String.valueOf(level.getPlayer().getCoins());
            String charge = String.valueOf(level.getPlayer().getCharge());
            Draw.getLargeFont().drawString(78, 893, health);
            Draw.getLargeFont().drawString(215, 893, coins);
            Draw.getLargeFont().drawString(352, 893, charge);

            if (Main.debug)
            {
                Draw.getSmallFont().drawString(0, 0, "FPS: " + Clock.getFps());
                Draw.getSmallFont().drawString(0, 22, "Enemies: " + level.getWaveManager().getEnemyList().size());
            }

            super.draw();

            for (Button button : editorPage)
                button.draw();

            if (!(level instanceof LevelEditor))
            {
                upgradeButton.draw();
                sellButton.draw();
            }
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
            for (Button button : editorPage)
                button.update();

            super.update();

            if (!(level instanceof LevelEditor))
            {
                upgradeButton.update();
                sellButton.update();
            }
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

    public boolean isPressingHudButton()
    {
        if (level.getPlayer().getGameMode() == Player.GameMode.EDIT)
        {
            for (Button button : editorPage)
                if (button.isClicked())
                    return true;
        }
        else
        {
            for (Button button : buttonList)
                if (button.isClicked())
                    return true;
        }
        if (sellButton.isClicked())
            return true;
        else if (upgradeButton.isClicked())
            return true;
        return false;
    }

    public boolean isHoveringHudButton()
    {
        if (level.getPlayer().getGameMode() == Player.GameMode.EDIT)
        {
            for (Button button : editorPage)
                if (button.isHovered())
                    return true;
        }
        else
        {
            for (Button button : buttonList)
                if (button.isHovered())
                    return true;
        }
        if (sellButton.isHovered())
            return true;
        else if (upgradeButton.isHovered())
            return true;
        return false;
    }

    public boolean isPressingTowerButton()
    {
        if (sellButton != null && upgradeButton != null)
        {
            if (sellButton.isClicked() || upgradeButton.isClicked())
                return true;
        }
        return false;
    }

    /*public List<TextBox> getTextBoxList()
    {
        return textBoxList;
    }*/
}
