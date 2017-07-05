package net.johnbrooks.fjg.drawables.entities;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.Scheduler;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.level.*;
import org.newdawn.slick.opengl.Texture;

import static net.johnbrooks.fjg.Clock.delta;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Enemy
{
    private static final float textureIncrementDelay = 0.5f;

    private Level level;
    private Texture[] textures;
    private Texture deathTexture;
    private Texture healthBackground, healthForeground, healthBorder;
    private int textureIndex;
    private float health, maxHealth, healthPercent, x, y, speed, slowMultiplier, sinceLastTextureIncrement;

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
    }

    public int getX() { return (int) x; }
    public int getY() { return (int) y; }
    public int getTileX() { return (int) Math.floor( (x) * 0.015625f );}
    public int getTileY() { return (int) Math.floor( (y) * 0.015625f );}
    public Texture[] getTextures() { return textures; }
    public Texture getTexture() { return textures[textureIndex]; }

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
            if (!checkpointReached())
            {
                x += delta() * direction.getX() * speed * slowMultiplier;
                y += delta() * direction.getY() * speed * slowMultiplier;

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
        health-=damage;
        if (health <= 0)
        {
            health = 0;
            level.getPlayer().modifyCoins((int) (maxHealth * 0.1f));
            if (deathTexture != null)
            {
                active = false;
                Scheduler.getInstance().doTaskLater(() ->
                        alive = false, 3);
            }
            else
                alive = false;
        }
        healthPercent = health / maxHealth;
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

        if (slowMultiplier >= 1f)
        {
            if (direction.getX() < 0 || direction.getY() < 0)
                Draw.drawTexture((active ? textures[textureIndex] : deathTexture), (int)x, (int)y, 0, true);
            else
                Draw.drawTexture((active ? textures[textureIndex] : deathTexture), (int)x, (int)y, 0, false);
        }
        else
            Draw.drawTexture((active ? textures[textureIndex] : deathTexture), (int)x, (int)y, 0f, false, 0.3f, 0.93f, 1f);
    }

    public boolean isAlive()
    {
        return alive;
    }
    public boolean isActive() { return active; }
}
