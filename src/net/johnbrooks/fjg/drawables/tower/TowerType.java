package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.drawables.GameTexture;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/4/2017.
 */
public enum TowerType
{
    BASIC_TOWER(GameTexture.TOWER_BASE_A.getTexture(), GameTexture.TOWER_CANNON_STANDARD.getTexture(), GameTexture.BULLET_STANDARD.getTexture(), 5, 10, 3, 256, 350, 1f, false),
    ICE_TOWER(GameTexture.TOWER_BASE_A.getTexture(), GameTexture.TOWER_CANNON_ICE.getTexture(), GameTexture.BULLET_ICE.getTexture(), 2, 15, 5, 256, 400, 0.4f, false),
    FIRE_TOWER(GameTexture.TOWER_BASE_C.getTexture(), GameTexture.TOWER_CANNON_BURN.getTexture(), GameTexture.BULLET_BURN.getTexture(), 8, 30, 3, 256, 350, 1.2f, false),
    FAST_TOWER(GameTexture.TOWER_BASE_B.getTexture(), GameTexture.TOWER_CANNON_FAST.getTexture(), GameTexture.BULLET_LITE.getTexture(), 1, 60, 0.1f, 320, 900, 1f, false),
    TRACKING_TOWER(GameTexture.TOWER_BASE_D.getTexture(), GameTexture.TOWER_CANNON_TRACKING.getTexture(), GameTexture.BULLET_TRACKING.getTexture(), 35, 120, 5f, 512, 240, 1f, true);

    private String towerName;
    private TowerStats towerStats;
    private ProjectileStats projectileStats;

    TowerType(Texture baseTexture, Texture topTexture, Texture projectileTexture, int damage, int cost, float warmUp, int viewDistance, float projectileSpeed, float projectileHitSlowMultiplier, boolean tracking)
    {
        this.towerName = name().replace("_", " ");
        this.towerStats = new TowerStats(baseTexture, topTexture, cost, warmUp, viewDistance);
        this.projectileStats = new ProjectileStats(projectileTexture, damage, projectileSpeed, projectileHitSlowMultiplier, tracking);
    }

    public String getTowerName()
    {
        return towerName;
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
