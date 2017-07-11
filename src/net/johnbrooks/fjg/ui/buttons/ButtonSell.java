package net.johnbrooks.fjg.ui.buttons;

import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.tower.Tower;
import net.johnbrooks.fjg.level.Level;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/7/2017.
 */
public class ButtonSell extends Button
{
    private Level level;

    public ButtonSell(int x, int y, Texture texture, Level level)
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
        if (level.getPlayer().getSelectedTower() != null)
        {
            for (Texture texture : textures)
            {
                if (!isHovered())
                    Draw.drawTexture(texture, x, y, width, height);
                else
                    Draw.drawTexture(texture, x, y, (int) (width * sizePercent), (int) (height * sizePercent), 0, 0.8f, 0.8f, 0.8f);
            }
        }
        else
        {
            for (Texture texture : textures)
                Draw.drawTexture(texture, x, y, (int) (width * sizePercent), (int) (height * sizePercent), 0, 0.3f, 0.3f, 0.3f);
        }

    }
}
