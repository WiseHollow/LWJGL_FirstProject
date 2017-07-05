package net.johnbrooks.fjg.ui.elements;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.Draw;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/5/2017.
 */
public class ImageBox
{
    private Texture texture;
    private float alive, duration;
    private int x, y;

    public ImageBox(Texture texture, float duration, int x, int y)
    {
        this.texture = texture;
        this.duration = duration;
        this.alive = 0;
        this.x = x;
        this.y = y;
    }

    public void update()
    {
        alive += Clock.delta();
    }

    public void draw()
    {
        if (isAlive())
            Draw.drawTexture(texture, x, y, 0f);
    }

    public boolean isAlive()
    {
        return alive < duration;
    }
}
