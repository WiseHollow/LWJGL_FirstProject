package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.level.Level;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.Log;

import java.util.ArrayList;
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

    protected List<Projectile> projectileList;

    public Tower(Level level, Texture baseTexture, Tile tile, int damage, float warmUpTime, float distanceView)
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
    }

    public void update()
    {
        timeSinceLastShot += Clock.delta();
        if (timeSinceLastShot > warmUpTime)
            shoot();
    }

    public void draw()
    {
        Draw.drawTexture(baseTexture, x, y, width, height);
    }

    protected Enemy calculateEnemyTarget()
    {
        List<Enemy> withinView = new ArrayList<>();

        for (Enemy enemy : level.getWaveManager().getEnemyList())
        {
            float distance = (float) Math.sqrt(Math.pow(x - enemy.getX(), 2) + Math.pow(y - enemy.getY(), 2));
            if (distance <= distanceView)
            {
                withinView.add(enemy);
                return enemy;
            }
        }

        //TODO: Get the one furthest along.

        if (withinView.isEmpty())
            return null;
        else
            return withinView.get(0);
    }

    protected abstract void shoot();
}
