package net.johnbrooks.fjg.drawables.effects;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.Draw;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/9/2017.
 */
public class Effect
{
    private int x, y, width, height, index;
    private boolean alive;
    private EffectType effectType;
    private float interval;

    public Effect(EffectType effectType, int x, int y)
    {
        this.effectType = effectType;
        this.x = x;
        this.y = y;
        this.width = effectType.getTextures()[0].getTextureWidth() * 2;
        this.height = effectType.getTextures()[0].getTextureHeight() * 2;
        this.interval = 0.0f;
        this.alive = true;
    }

    public void draw()
    {
        if (alive)
            Draw.drawTexture(effectType.getTextures()[index], (int) (x - (width * 0.5f)), (int) (y - (height * 0.5f)), width, height);
    }

    public void update()
    {
        if (alive)
        {
            interval+= Clock.delta();
            if (interval >= 0.1f)
            {
                index++;
                interval = 0f;
            }
            if (index >= effectType.getTextures().length)
                alive = false;
        }
    }

    public boolean isAlive()
    {
        return alive;
    }
}
