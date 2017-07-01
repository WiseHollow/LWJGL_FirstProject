package net.johnbrooks.fjg.level;

import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 6/30/2017.
 */
public class EnemyTemplate
{
    private Texture texture;
    private int width, height, health;
    private float speed;

    public EnemyTemplate(Texture texture, int width, int height, int health, float speed)
    {
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.health = health;
        this.speed = speed;
    }

    public Texture getTexture()
    {
        return texture;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getHealth()
    {
        return health;
    }

    public float getSpeed()
    {
        return speed;
    }
}
