package net.johnbrooks.fjg.drawables.entities;

import net.johnbrooks.fjg.Scheduler;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.level.*;
import org.newdawn.slick.opengl.Texture;

import static net.johnbrooks.fjg.Clock.delta;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Enemy
{
    private Level level;
    private TileGrid tileGrid;
    private Texture texture;
    private int width, height, health;
    private float x, y, speed, slowMultiplier;

    private Direction direction;
    private boolean first = true, alive = true;
    private Checkpoint targetCheckpoint;
    private int targetCheckpointIndex;

    public Enemy(Level level, TileGrid tileGrid, Texture texture, int x, int y, int width, int height, int health, float speed)
    {
        this.level = level;
        this.tileGrid = tileGrid;
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.health = health;
        this.targetCheckpoint = level.getCheckpointList().get(0);
        this.targetCheckpointIndex = 0;
        this.direction = level.getCheckpointList().get(0).getDirection();
        this.slowMultiplier = 1f;
    }

    public Enemy(Enemy enemy)
    {
        this.level = enemy.level;
        this.tileGrid = enemy.tileGrid;
        this.texture = enemy.texture;
        this.x = enemy.x;
        this.y = enemy.y;
        this.width = enemy.width;
        this.height = enemy.height;
        this.speed = enemy.speed;
        this.health = enemy.health;
        this.targetCheckpoint = enemy.targetCheckpoint;
        this.targetCheckpointIndex = enemy.targetCheckpointIndex;
        this.direction = enemy.direction;
        this.slowMultiplier = enemy.slowMultiplier;
    }

    public Enemy(EnemyTemplate enemyTemplate, Level level, int x, int y)
    {
        this.level = level;
        this.tileGrid = level.getTileGrid();
        this.x = x;
        this.y = y;
        this.texture = enemyTemplate.getTexture();
        this.width = enemyTemplate.getWidth();
        this.height = enemyTemplate.getHeight();
        this.health = enemyTemplate.getHealth();
        this.speed = enemyTemplate.getSpeed();
        this.targetCheckpoint = level.getCheckpointList().get(0);
        this.targetCheckpointIndex = 0;
        this.direction = level.getCheckpointList().get(0).getDirection();
        this.slowMultiplier = 1f;
    }

    public int getX() { return (int) x; }
    public int getY() { return (int) y; }
    public int getTileX() { return (int) Math.floor( (x) * 0.015625f );}
    public int getTileY() { return (int) Math.floor( (y) * 0.015625f );}
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Texture getTexture() { return texture; }

    public void setTileX(int x)
    {
        this.x = x * 64;
    }
    public void setTileY(int y)
    {
        this.y = y * 64;
    }

    public void setSlowMultiplier(float slowMultiplier)
    {
        this.slowMultiplier = slowMultiplier;
        if (this.slowMultiplier != 1)
        {
            Scheduler.getInstance().doTaskLater(() ->
                    this.slowMultiplier = 1f, 5f);
        }
    }

    private boolean checkpointReached()
    {
        boolean reached = false;
        if (targetCheckpoint != null)
        {
            if (x > targetCheckpoint.getTile().getX() - 3 &&
                    x < targetCheckpoint.getTile().getX() + 3 &&
                    y > targetCheckpoint.getTile().getY() - 3 &&
                    y < targetCheckpoint.getTile().getY() + 3
                    )
            {
                reached = true;

                // Snap to grid
                x = targetCheckpoint.getTile().getX();
                y = targetCheckpoint.getTile().getY();
                direction = targetCheckpoint.getDirection();
            }
        }

        return reached;
    }

    public void update()
    {
        if (!first)
        {
            if (!checkpointReached())
            {
                x += delta() * direction.getX() * speed * slowMultiplier;
                y += delta() * direction.getY() * speed * slowMultiplier;

                if (x > DisplayManager.getScreenWidth() + width ||
                        x < -width ||
                        y > DisplayManager.getScreenHeight() + height ||
                        y < -height)
                {
                    remove();
                }
            }
            else
            {
                // Get next checkpoint
                targetCheckpointIndex++;
                if (targetCheckpointIndex < level.getCheckpointList().size())
                    targetCheckpoint = level.getCheckpointList().get(targetCheckpointIndex);
                else
                    targetCheckpoint = null;
            }
        }
        else
            first = false;
    }

    public void hurt(int damage)
    {
        health-=damage;
        if (health < 0)
            alive = false;
    }

    public void remove()
    {
        alive = false;
    }

    public void draw()
    {
        if (slowMultiplier >= 1f)
            Draw.drawTexture(texture, (int)x, (int)y, width, height);
        else
            Draw.drawTextureWithRGB(texture, (int)x, (int)y, 0f, 0.3f, 0.93f, 1f);
    }

    public boolean isAlive()
    {
        return alive;
    }
}
