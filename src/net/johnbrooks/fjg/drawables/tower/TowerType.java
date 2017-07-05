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

    private TowerStats towerStats;
    private ProjectileStats projectileStats;

    TowerType(Texture baseTexture, Texture topTexture, Texture projectileTexture, int damage, int cost, int warmUp, int viewDistance, float projectileSpeed, float projectileHitSlowMultiplier)
    {
        this.towerStats = new TowerStats(baseTexture, topTexture, cost, warmUp, viewDistance);
        this.projectileStats = new ProjectileStats(projectileTexture, damage, projectileSpeed, projectileHitSlowMultiplier);
    }

    public TowerStats getTowerStats()
    {
        return towerStats;
    }

    public ProjectileStats getProjectileStats()
    {
        return projectileStats;
    }
}
