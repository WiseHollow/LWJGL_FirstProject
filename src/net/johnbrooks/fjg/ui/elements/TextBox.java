package net.johnbrooks.fjg.ui.elements;

import net.johnbrooks.fjg.Clock;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 * Created by ieatl on 7/5/2017.
 */
public class TextBox
{
    private TrueTypeFont font;
    private String text;
    private float alive, duration;
    private int x, y;
    private Color color;

    public TextBox(TrueTypeFont font, String text, Color color, float duration, int x, int y)
    {
        this.font = font;
        this.text = text;
        this.color = color;
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
            font.drawString(x, y, text, color);
    }

    public boolean isAlive()
    {
        return alive < duration;
    }
}
