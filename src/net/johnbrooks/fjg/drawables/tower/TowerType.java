package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.drawables.GameTexture;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/4/2017.
 */
public enum TowerType
{
    BASIC_TOWER(GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(), GameTexture.BULLET_STANDARD.getTexture(), 5, 10, 3, 256, 350, 1f, false),
    ICE_TOWER(GameTexture.ICE_CANNON_BASE.getTexture(), GameTexture.ICE_CANNON_GUN.getTexture(), GameTexture.BULLET_ICE.getTexture(), 2, 15, 5, 256, 400, 0.4f, false),
    FAST_TOWER(GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(), GameTexture.BULLET_LITE.getTexture(), 1, 50, 0.1f, 320, 900, 1f, false),
    FIRE_TOWER(GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(), GameTexture.BULLET_BURN.getTexture(), 6, 30, 3, 256, 350, 1.2f, false),
    TRACKING_TOWER(GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(), GameTexture.BULLET_TRACKING.getTexture(), 8, 100, 5f, 512, 240, 1f, true);

    private TowerStats towerStats;
    private ProjectileStats projectileStats;

    TowerType(Texture baseTexture, Texture topTexture, Texture projectileTexture, int damage, int cost, float warmUp, int viewDistance, float projectileSpeed, float projectileHitSlowMultiplier, boolean tracking)
    {
        this.towerStats = new TowerStats(baseTexture, topTexture, cost, warmUp, viewDistance);
        this.projectileStats = new ProjectileStats(projectileTexture, damage, projectileSpeed, projectileHitSlowMultiplier, tracking);
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
