package net.johnbrooks.fjg.drawables.entities;

import net.johnbrooks.fjg.drawables.entities.EnemyTexture;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 6/30/2017.
 */
public enum EnemyTemplate
{
    GREEN_SLIME(EnemyTexture.SLIME, 25, 30), PINK_SLIME(EnemyTexture.SLIME_PINK, 40, 45), BLUE_SLIME(EnemyTexture.SLIME_BLUE, 80, 60),
    MOUSE(EnemyTexture.MOUSE, 10, 45), MOUSE_FAST(EnemyTexture.MOUSE, 10, 80),
    SPIDER(EnemyTexture.SPIDER, 40, 40),
    SPINNER(EnemyTexture.SPINNER, 200, 60);

    private Texture texture, altTexture, deadTexture;
    private int health;
    private float speed;

    EnemyTemplate(EnemyTexture enemyTexture, int health, float speed)
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
