package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.drawables.GameTexture;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/4/2017.
 */
public enum TowerType
{
    BASIC_TOWER(GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(), GameTexture.BULLET.getTexture(), 3, 10, 3, 256, 350, 1f),
    ICE_TOWER(GameTexture.ICE_CANNON_BASE.getTexture(), GameTexture.ICE_CANNON_GUN.getTexture(), GameTexture.BULLET.getTexture(), 1, 10, 5, 256, 400, 0.1f);

    private Texture baseTexture, topTexture, projectileTexture;
    private int damage, cost, warmUp, viewDistance;
    private float projectileSpeed, projectileHitSlowMultiplier;

    TowerType(Texture baseTexture, Texture topTexture, Texture projectileTexture, int damage, int cost, int warmUp, int viewDistance, float projectileSpeed, float projectileHitSlowMultiplier)
    {
        this.baseTexture = baseTexture;
        this.topTexture = topTexture;
        this.projectileTexture = projectileTexture;
        this.damage = damage;
        this.cost = cost;
        this.warmUp = warmUp;
        this.viewDistance = viewDistance;
        this.projectileSpeed = projectileSpeed;
        this.projectileHitSlowMultiplier = projectileHitSlowMultiplier;
    }
    public Texture getBaseTexture()
    {
        return baseTexture;
    }
    public Texture getTopTexture()
    {
        return topTexture;
    }
    public int getDamage()
    {
        return damage;
    }
    public int getWarmUp()
    {
        return warmUp;
    }
    public int getViewDistance()
    {
        return viewDistance;
    }
    public int getCost()
    {
        return cost;
    }
    public float getProjectileSpeed()
    {
        return projectileSpeed;
    }

    public Texture getProjectileTexture()
    {
        return projectileTexture;
    }

    public float getProjectileHitSlowMultiplier()
    {
        return projectileHitSlowMultiplier;
    }
}
