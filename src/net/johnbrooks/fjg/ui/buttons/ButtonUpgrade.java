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

    public ButtonUpgrade(int x, int y, Texture texture, Level level)
    {
        super(x, y, texture);
        this.level = level;
    }

    @Override
    public void update()
    {
        super.update();
    }

    @Override
    public void draw()
    {
        super.draw();
    }
}
