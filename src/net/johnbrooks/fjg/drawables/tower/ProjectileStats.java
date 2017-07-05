package net.johnbrooks.fjg.drawables.tower;

import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/4/2017.
 */
public class ProjectileStats
{
    private Texture projectileTexture;
    private int damage;
    private float speed, hitSlowMultiplier;

    public ProjectileStats(Texture projectileTexture, int damage, float speed, float hitSlowMultiplier)
    {
        this.projectileTexture = projectileTexture;
        this.damage = damage;
        this.speed = speed;
        this.hitSlowMultiplier = hitSlowMultiplier;
    }

    public Texture getProjectileTexture()
    {
        return projectileTexture;
    }

    public int getDamage()
    {
        return damage;
    }

    public float getSpeed()
    {
        return speed;
    }

    public float getHitSlowMultiplier()
    {
        return hitSlowMultiplier;
    }
}
