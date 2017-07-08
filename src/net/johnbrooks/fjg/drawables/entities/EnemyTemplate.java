package net.johnbrooks.fjg.drawables.entities;

import net.johnbrooks.fjg.drawables.entities.EnemyTexture;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 6/30/2017.
 */
public enum EnemyTemplate
{
    FLY(EnemyTexture.FLY, 5, 55),
    MOUSE(EnemyTexture.MOUSE, 10, 45), MOUSE_FAST(EnemyTexture.MOUSE, 10, 80),
    FROG(EnemyTexture.FROG, 20, 50),
    LADY_BUG(EnemyTexture.LADY_BUG, 20, 75),
    BEE(EnemyTexture.BEE, 25, 80),
    SNAIL(EnemyTexture.SNAIL, 100, 20),
    GREEN_SLIME(EnemyTexture.SLIME, 40, 30), PINK_SLIME(EnemyTexture.SLIME_PINK, 60, 45), BLUE_SLIME(EnemyTexture.SLIME_BLUE, 100, 60),
    BARNACLE(EnemyTexture.BARNACLE, 60, 30),
    SPIDER(EnemyTexture.SPIDER, 80, 40),
    GHOST(EnemyTexture.GHOST, 100, 35),
    SPINNER(EnemyTexture.SPINNER, 300, 60);

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
