package net.johnbrooks.fjg.drawables.entities;

import net.johnbrooks.fjg.drawables.Draw;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.Log;

import static net.johnbrooks.fjg.Clock.delta;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Enemy
{
    private Texture texture;
    private int width, height, health;
    private float x, y, speed;

    private boolean first = true;

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

    public Enemy(Enemy enemy)
    {
        this.texture = enemy.texture;
        this.x = enemy.x;
        this.y = enemy.y;
        this.width = enemy.width;
        this.height = enemy.height;
        this.speed = enemy.speed;
        this.health = enemy.health;
    }

    public void update()
    {
        if (!first)
            x += delta() * speed;
        else
            first = false;
    }

    public void draw()
    {
        Draw.drawTexture(texture, (int)x, (int)y, width, height);
    }
}
