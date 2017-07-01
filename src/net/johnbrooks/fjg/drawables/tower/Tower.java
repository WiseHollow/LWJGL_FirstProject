package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.level.Level;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ieatl on 6/30/2017.
 */
public abstract class Tower
{
    private static final short WIDTH = 64, HEIGHT = 64;

    protected Level level;
    protected Tile tile;
    protected float timeSinceLastShot, warmUpTime, distanceView;
    protected int x, y, width, height, damage;
    protected Texture baseTexture;
    protected Enemy target;

    protected List<Projectile> projectileList;
    protected List<Enemy> enemyList;

    public Tower(Level level, Texture baseTexture, Tile tile, int damage, float warmUpTime, float distanceView, List<Enemy> enemyList)
    {
        this.level = level;
        this.baseTexture = baseTexture;
        this.tile = tile;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.x = tile.getX();
        this.y = tile.getY();
        this.damage = damage;
        this.distanceView = distanceView;
        this.warmUpTime = warmUpTime;
        this.timeSinceLastShot = 0;
        this.projectileList = new ArrayList<>();
        this.enemyList = enemyList;
        this.target = null;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSlotX() { return tile.getXSlot(); }
    public int getSlotY() { return tile.getYSlot(); }

    public void update()
    {
        target = calculateEnemyTarget();
        timeSinceLastShot += Clock.delta();
        if (timeSinceLastShot > warmUpTime)
            shoot();
    }

    public void draw()
    {
        Draw.drawTexture(baseTexture, x, y, width, height);
    }

    protected float calculateAngleToTarget()
    {
        double tempAngle = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(tempAngle) + 90f;
    }

    protected Enemy calculateEnemyTarget()
    {
        HashMap<Float, Enemy> distanceMap = new HashMap<>();
        float closest = distanceView + 1;

        for (Enemy enemy : enemyList)
        {
            float distance = (float) Math.sqrt(Math.pow(x - enemy.getX(), 2) + Math.pow(y - enemy.getY(), 2));
            if (distance <= distanceView)
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

    protected abstract void shoot();
}
