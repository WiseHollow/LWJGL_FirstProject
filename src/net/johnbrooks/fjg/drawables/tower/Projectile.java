package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.tower.Tower;
import net.johnbrooks.fjg.state.states.Game;
import net.johnbrooks.fjg.drawables.DisplayManager;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 6/30/2017.
 */
public class Projectile
{
    private Tower shooter;
    private Texture texture;
    private int damage, width, height;
    private float x, y, speed, xVelocity, yVelocity;
    private boolean alive;
    protected Enemy target, colliding;

    public Projectile(Tower shooter, Texture texture, Tile tile, float speed, int damage, Enemy target)
    {
        this.shooter = shooter;
        this.texture = texture;
        this.width = texture.getImageWidth();
        this.height = texture.getImageHeight();
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.xVelocity = 0f;
        this.yVelocity = 0f;
        this.alive = true;
        this.x = tile.getX() + 16;
        this.y = tile.getY() + 16;
        this.calculateDirection();
    }

    private void calculateDirection()
    {
        float xDistance = Math.abs(target.getX() - x - Game.TILE_WIDTH * 0.25f + Game.TILE_WIDTH * 0.5f);
        float yDistance = Math.abs(target.getY() - y - Game.TILE_HEIGHT * 0.25f + Game.TILE_HEIGHT * 0.5f);
        float totalDistance = xDistance + yDistance;

        xVelocity = xDistance / totalDistance;
        yVelocity = 1f - xVelocity;

        if (target.getX() < x)
            xVelocity = -xVelocity;
        if (target.getY() < y)
            yVelocity = -yVelocity;
    }

    public void draw()
    {
        Draw.drawTexture(texture, (int) x, (int) y, 0);
    }

    public void update()
    {
        x += xVelocity * speed * Clock.delta();
        y += yVelocity * speed * Clock.delta();

        if (x < -texture.getTextureWidth() || x > DisplayManager.getScreenWidth() + texture.getTextureWidth() ||
                y < -texture.getTextureHeight() || y > DisplayManager.getScreenHeight() + texture.getTextureHeight())
        {
            alive = false;
            return;
        }

        calculateColliding();
        if (colliding != null && colliding.isActive() && colliding.isAlive())
        {
            colliding.hurt(damage);
            colliding.setSlowMultiplier(shooter.getTowerType().getProjectileStats().getHitSlowMultiplier());
            alive = false;
        }
    }

    public boolean isAlive()
    {
        return alive;
    }

    private Enemy calculateColliding()
    {
        for (Enemy enemy : shooter.getEnemyList())
        {
            int enemyX = enemy.getX();
            int enemyY = enemy.getY();
            int enemyWidth = enemy.getTexture().getImageWidth();
            int enemyHeight = enemy.getTexture().getImageHeight();

            if (x + width > enemyX && x < enemyX + enemyWidth && y + height > enemyY && y < enemyY + enemyHeight)
            {
                colliding = enemy;
                return enemy;
            }
        }

        return null;
    }
}
