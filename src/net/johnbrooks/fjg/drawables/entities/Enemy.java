package net.johnbrooks.fjg.drawables.entities;

import net.johnbrooks.fjg.drawables.Draw;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Enemy
{
    private Texture texture;
    private int x, y, width, height, health;
    private float speed;

    public Enemy(Texture texture, int x, int y, int width, int height, int health, float speed)
    {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.health = health;
    }

    public void draw()
    {
        Draw.drawTexture(texture, x, y, width, height);
    }
}
