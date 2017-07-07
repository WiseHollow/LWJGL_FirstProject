package net.johnbrooks.fjg.ui.buttons;

import net.johnbrooks.fjg.drawables.tower.Tower;
import net.johnbrooks.fjg.level.Level;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/7/2017.
 */
public class ButtonUpgrade extends Button
{
    private Level level;
    private Tower tower;

    public ButtonUpgrade(int x, int y, Texture texture, Level level, Tower tower)
    {
        super(x, y, texture);
        this.level = level;
        this.tower = tower;
    }

    @Override
    public void update()
    {
        if (level.getPlayer().getSelectedTower() == tower && tower.getPower() < 4)
            super.update();
    }

    @Override
    public void draw()
    {
        if (level.getPlayer().getSelectedTower() == tower && tower.getPower() < 4)
            super.draw();
    }
}
