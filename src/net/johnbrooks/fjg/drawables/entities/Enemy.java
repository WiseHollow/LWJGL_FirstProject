package net.johnbrooks.fjg.drawables.entities;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.Scheduler;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.drops.Drop;
import net.johnbrooks.fjg.drawables.drops.DropType;
import net.johnbrooks.fjg.level.*;
import org.newdawn.slick.opengl.Texture;

import java.util.Random;

import static net.johnbrooks.fjg.Clock.delta;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Enemy
{
    private static final Random random = new Random();
    private static final float textureIncrementDelay = 0.5f;

    private Level level;
    private Texture[] textures;
    private Texture deathTexture;
    private Texture healthBackground, healthForeground, healthBorder;
    private int textureIndex;
    private float health, maxHealth, healthPercent, x, y, speed, slowMultiplier, sinceLastTextureIncrement, sinceHurt, travelled;



    private Direction direction;
    private boolean first = true, alive = true, active = true;
    private Checkpoint targetCheckpoint;
    private int targetCheckpointIndex;

    public Enemy(EnemyTemplate enemyTemplate, Level level, int x, int y)
    {
        this.textureIndex = 0;
        this.level = level;
        this.x = x;
        this.y = y;
        if (enemyTemplate.getAltTexture() == null)
            textures = new Texture[] { enemyTemplate.getTexture() };
        else
            textures = new Texture[] { enemyTemplate.getTexture(), enemyTemplate.getAltTexture() };
        this.deathTexture = enemyTemplate.getDeadTexture();
        this.healthBackground = GameTexture.HEALTH_BACKGROUND.getTexture();
        this.healthForeground = GameTexture.HEALTH_FOREGROUND.getTexture();
        this.healthBorder = GameTexture.HEATH_BORDER.getTexture();
        this.health = enemyTemplate.getHealth();
        this.maxHealth = health;
        this.healthPercent = 1;
        this.speed = enemyTemplate.getSpeed();
        this.targetCheckpoint = level.getCheckpointList().get(0);
        this.targetCheckpointIndex = 0;
        this.direction = level.getCheckpointList().get(0).getDirection();
        this.slowMultiplier = 1f;
        this.sinceLastTextureIncrement = textureIncrementDelay;
        this.sinceHurt = 1f;
    }

    public int getX() { return (int) x; }
    public int getY() { return (int) y; }
    public int getTileX() { return (int) Math.floor( (x) * 0.015625f );}
    public int getTileY() { return (int) Math.floor( (y) * 0.015625f );}
    public Texture[] getTextures() { return textures; }
    public Texture getTexture() { return textures[textureIndex]; }
    public float getSlowMultiplier()
    {
        return slowMultiplier;
    }

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
        if (!active)
            return;

        sinceHurt += Clock.delta();

        if (textures.length > 0 && sinceLastTextureIncrement >= textureIncrementDelay)
        {
            textureIndex++;
            if (textureIndex >= textures.length)
                textureIndex = 0;
            sinceLastTextureIncrement = 0;
        }
        sinceLastTextureIncrement += Clock.delta();

        if (!first)
        {
            if (!checkpointReached() && isAlive())
            {
                float xDir = delta() * direction.getX() * speed * slowMultiplier;
                float yDir = delta() * direction.getY() * speed * slowMultiplier;
                x += xDir;
                y += yDir;

                travelled += xDir + yDir;

                if (x > DisplayManager.getScreenWidth() + 64 ||
                        x < -64 ||
                        y > DisplayManager.getScreenHeight() + 64 ||
                        y < -64)
                {
                    level.getPlayer().modifyHealth(-1);
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
        sinceHurt = 0;
        health-=damage;
        if (health <= 0)
        {
            // enemy is now dead

            if (random.nextInt(100) < 15)
            {
                // Create reward drop
                Drop drop = new Drop(level, DropType.getRandom(), (int)x, (int)y);
                level.addDrop(drop);
            }

            health = 0;
            level.getPlayer().modifyCoins((int) (maxHealth * 0.1f));
            if (deathTexture != null)
            {
                active = false;
                Scheduler.getInstance().doTaskLater(() ->
                {
                    alive = false;
                }, 2);
            }
            else
                alive = false;
        }
        healthPercent = health / maxHealth;
    }

    public float distance(float x, float y)
    {
        return (float) Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    public void remove()
    {
        alive = false;
    }

    public void draw()
    {
        if (health < maxHealth && health > 0)
        {
            Draw.drawTexture(healthBackground, (int)x + (textures[textureIndex].getImageWidth() * 0.5f - (healthBackground.getImageWidth() * 0.5f)), (int)y + textures[textureIndex].getImageHeight(), 0, false);
            Draw.drawTexture(healthForeground, (int)(x + (textures[textureIndex].getImageWidth() * 0.5f - (healthForeground.getImageWidth() * 0.5f))), (int)y + textures[textureIndex].getImageHeight(), (int) (healthForeground.getImageWidth() * healthPercent), healthForeground.getImageHeight());
            Draw.drawTexture(healthBorder, (int)x + (textures[textureIndex].getImageWidth() * 0.5f - (healthBorder.getImageWidth() * 0.5f)), (int)y + textures[textureIndex].getImageHeight(), 0, false);
        }

        if (slowMultiplier == 1f)
        {
            if (direction.getX() < 0 || direction.getY() < 0)
                Draw.drawTexture((active ? textures[textureIndex] : deathTexture), (int)x, (int)y, 0, true, 1, (!active || sinceHurt > 0.3f ? 1 : 0), (!active || sinceHurt > 0.3f ? 1 : 0));
            else
                Draw.drawTexture((active ? textures[textureIndex] : deathTexture), (int)x, (int)y, 0, false, 1, (!active || sinceHurt > 0.3f ? 1 : 0), (!active || sinceHurt > 0.3f ? 1 : 0));
        }
        else if (slowMultiplier < 1f)
            Draw.drawTexture((active ? textures[textureIndex] : deathTexture), (int)x, (int)y, 0f, false, 0.3f, 0.93f, 1f);
        else if (slowMultiplier > 1f)
            Draw.drawTexture((active ? textures[textureIndex] : deathTexture), (int)x, (int)y, 0f, false, 0.61f, 0.164f, 0f);
    }

    public boolean isAlive()
    {
        return alive;
    }
    public boolean isActive() { return active; }

    public float getTravelled()
    {
        return travelled;
    }
}
