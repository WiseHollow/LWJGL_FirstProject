package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.level.Level;
import org.newdawn.slick.opengl.Texture;

import java.util.List;

/**
 * Created by ieatl on 7/4/2017.
 */
public enum TowerType
{
    BASIC_TOWER(GameTexture.CANNON_BASE.getTexture(), GameTexture.CANNON_GUN.getTexture(), 3, 10, 3, 256),
    ICE_TOWER(GameTexture.ICE_CANNON_BASE.getTexture(), GameTexture.ICE_CANNON_GUN.getTexture(), 1, 10, 5, 256);

    private Texture baseTexture, topTexture;
    private int damage, cost, warmUp, viewDistance;

    TowerType(Texture baseTexture, Texture topTexture, int damage, int cost, int warmUp, int viewDistance)
    {
        this.baseTexture = baseTexture;
        this.topTexture = topTexture;
        this.damage = damage;
        this.cost = cost;
        this.warmUp = warmUp;
        this.viewDistance = viewDistance;
    }

    public int getCost()
    {
        return cost;
    }

    public Tower createTower(Level level, Tile tile, List<Enemy> enemyList)
    {
        Tower tower = null;

        switch (this)
        {
            case BASIC_TOWER:
                tower = new TowerCannon(level, baseTexture, topTexture, tile, damage, cost, warmUp, viewDistance, enemyList);
                break;
            case ICE_TOWER:
                tower = new IceTowerCannon(level, baseTexture, topTexture, tile, damage, cost, warmUp, viewDistance, enemyList);
                break;
        }

        return tower;
    }
}
