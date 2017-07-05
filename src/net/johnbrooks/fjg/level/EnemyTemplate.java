package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.drawables.entities.EnemyTexture;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 6/30/2017.
 */
public class EnemyTemplate
{
    private Texture texture, altTexture, deadTexture;
    private int health;
    private float speed;

    public EnemyTemplate(EnemyTexture enemyTexture, int health, float speed)
    {
        this.texture = enemyTexture.getTexture();
        this.altTexture = enemyTexture.getTextureAlt();
        this.deadTexture = enemyTexture.getTextureDead();
        this.health = health;
        this.speed = speed;
    }

    public Texture getTexture()
    {
        return texture;
    }

    public int getHealth()
    {
        return health;
    }

    public float getSpeed()
    {
        return speed;
    }

    public Texture getAltTexture()
    {
        return altTexture;
    }

    public Texture getDeadTexture() { return deadTexture; }
}
