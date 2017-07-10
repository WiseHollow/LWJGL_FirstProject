package net.johnbrooks.fjg.drawables.drops;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.audio.AudioManager;
import net.johnbrooks.fjg.audio.Sound;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.level.Level;
import org.lwjgl.input.Mouse;

/**
 * Created by ieatl on 7/9/2017.
 */
public class Drop
{
    private Level level;
    private int x, y;
    private DropType type;
    private float lifetime;
    private boolean alive;

    public Drop(Level level, DropType type, int x, int y)
    {
        this.level = level;
        this.type = type;
        this.x = x;
        this.y = y;
        this.lifetime = 0f;
        this.alive = true;
    }

    public void update()
    {
        if (lifetime < 3)
            lifetime += Clock.delta();
        else
            alive = false;

        if (isHovered())
        {
            alive = false;
            level.getPlayer().modifyCoins(type.getCoins());
            if (type.getCoins() > 0)
                AudioManager.getInstance().play(Sound.COIN_REWARD);
            level.getPlayer().modifyCharge(type.getCharge());
            if (type.getCharge() > 0)
                AudioManager.getInstance().play(Sound.PICKUP);
        }
    }

    public boolean isAlive()
    {
        return alive;
    }

    public void draw()
    {
        if (alive)
            Draw.drawTexture(type.getTexture(), x, y, 0);
    }

    public boolean isHovered()
    {
        int clickX = Mouse.getX();
        int clickY = Mouse.getY();

        if (clickX >= x && clickX <= x + type.getTexture().getImageWidth() &&
                DisplayManager.getScreenHeight() - clickY >= y && DisplayManager.getScreenHeight() - clickY <= y + type.getTexture().getImageHeight())
            return true;

        return false;
    }
}
