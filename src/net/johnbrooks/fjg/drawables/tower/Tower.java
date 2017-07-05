package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.drawables.tower.projectiles.Projectile;
import net.johnbrooks.fjg.level.Level;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ieatl on 6/30/2017.
 */
public class Tower
{
    private static final short WIDTH = 64, HEIGHT = 64;

    protected Level level;
    protected Tile tile;
    protected List<Enemy> enemyList;
    protected TowerType towerType;

    protected float timeSinceLastShot, topTextureRotation;
    protected int x, y;
    protected Enemy target;
    protected List<Projectile> projectileList;

    public Tower(TowerType towerType, Level level, Tile tile, List<Enemy> enemyList)
    {
        this.level = level;
        this.tile = tile;
        this.enemyList = enemyList;
        this.towerType = towerType;

        this.x = tile.getX();
        this.y = tile.getY();

        this.topTextureRotation = 0;
        this.timeSinceLastShot = towerType.getWarmUp();
        this.target = null;
        this.projectileList = new ArrayList<>();
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSlotX() { return tile.getXSlot(); }
    public int getSlotY() { return tile.getYSlot(); }
    public Tile getTile() { return tile; }
    public int getCost() { return towerType.getCost(); }
    public TowerType getTowerType() { return towerType; }

    public List<Enemy> getEnemyList() { return enemyList; }

    public void setTile(Tile tile)
    {
        this.x = tile.getX();
        this.y = tile.getY();
        this.tile = tile;
    }

    public void update()
    {
        target = calculateEnemyTarget();
        timeSinceLastShot += Clock.delta();
        if (timeSinceLastShot > towerType.getWarmUp())
            shoot();

        for (int i = 0; i < projectileList.size(); i++)
        {
            Projectile projectile = projectileList.get(i);
            if (!projectile.isAlive())
            {
                projectileList.remove(projectile);
                i--;
            }
            else
            {
                projectile.update();
            }
        }

        if (towerType.getTopTexture() != null)
        {
            if (target == null)
                topTextureRotation += Clock.delta() * 30;
            else
                topTextureRotation = calculateAngleToTarget();
        }
    }

    public void draw()
    {
        if (towerType.getBaseTexture() != null)
            Draw.drawTexture(towerType.getBaseTexture(), x, y, WIDTH, HEIGHT);
        if (towerType.getTopTexture() != null)
            Draw.drawTexture(towerType.getTopTexture(), x, y, topTextureRotation);

        for (Projectile projectile : projectileList)
            projectile.draw();
    }

    protected float calculateAngleToTarget()
    {
        double tempAngle = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(tempAngle) + 90f;
    }

    protected Enemy calculateEnemyTarget()
    {
        HashMap<Float, Enemy> distanceMap = new HashMap<>();
        float closest = towerType.getViewDistance() + 1;

        for (Enemy enemy : enemyList)
        {
            float distance = (float) Math.sqrt(Math.pow(x - enemy.getX(), 2) + Math.pow(y - enemy.getY(), 2));
            if (distance <= towerType.getViewDistance())
            {
                distanceMap.put(distance, enemy);
                if (distance < closest)
                    closest = distance;
            }
        }

        if (!distanceMap.isEmpty())
            return distanceMap.get(closest);
        else
            return null;
    }

    protected void shoot()
    {
        if (target != null)
        {
            timeSinceLastShot = 0;
            projectileList.add(new Projectile(this, towerType.getProjectileTexture(), tile, towerType.getProjectileSpeed(), towerType.getDamage(), target));
        }
    }
}
