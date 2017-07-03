package net.johnbrooks.fjg.ui;

import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
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

    private boolean isMouse1Down;

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
        this.isMouse1Down = false;

        Button buildBasicTower = new Button("basic", 0, 0, GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture()).setOnClickEvent(() ->
        {
            //TODO: Dynamic cannon stats
            final TowerCannon tower = new TowerCannon
                    (
                            level, GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(),
                            level.getPlayer().getSelectedTile(), 3, 3, 256, level.getWaveManager().getEnemyList()
                    );
            int cost = 10;
            if (level.getPlayer().getCoins() > cost && level.getPlayer().setTower(tower))
            {
                level.getPlayer().modifyCoins(-cost);
                visible = false;
                isMouse1Down = true;
            }
        });
        Button buildIceTower = new Button("ice", 0, 0, GameTexture.ICE_CANNON_BASE.getTexture(), GameTexture.ICE_CANNON_GUN.getTexture()).setOnClickEvent(() ->
        {
            //TODO: Dynamic cannon stats
            final IceTowerCannon tower = new IceTowerCannon
                    (
                        level, GameTexture.ICE_CANNON_BASE.getTexture(), GameTexture.ICE_CANNON_GUN.getTexture(),
                        level.getPlayer().getSelectedTile(), 1, 5, 256, level.getWaveManager().getEnemyList(), 0.1f
                    );

            int cost = 15;
            if (level.getPlayer().getCoins() > cost && level.getPlayer().setTower(tower))
            {
                level.getPlayer().modifyCoins(-cost);
                visible = false;
                isMouse1Down = true;
            }
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
        // Check if we should exit the BuildUI
        if (Mouse.isButtonDown(0) && !isMouse1Down)
        {
            // Get image and position info
            int padding = 32;
            int clickX = Mouse.getX();
            int clickY = DisplayManager.getScreenHeight() - Mouse.getY();

            if (visible)
            {
                if (clickX <= x + padding || clickX >= x + width - padding || clickY <= y + padding || clickY >= y + height - padding)
                    visible = false;
            }
        }

        // Check whether we need to display BuildUI and move all the elements accordingly.
        if (Mouse.isButtonDown(1) && !isMouse1Down)
        {
            // Get image and position info
            int padding = 32;
            int clickX = Mouse.getX();
            int clickY = DisplayManager.getScreenHeight() - Mouse.getY();

            // If not visible, lets make it so.
            if (!visible)
            {
                // Not visible. Update position and show as visible.
                x = clickX - padding + 3;
                y = clickY - padding + 3;

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

                if (clickX <= x + padding || clickX >= x + width - padding || clickY <= y + padding || clickY >= y + height - padding)
                    visible = false;
            }
        }

        // Check for click updates
        isMouse1Down = Mouse.isButtonDown(1);

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
