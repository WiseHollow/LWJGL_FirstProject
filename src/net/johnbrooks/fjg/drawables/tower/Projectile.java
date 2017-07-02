package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.Clock;
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
    private Texture texture;
    private Tile tile;
    private int damage;
    private float x, y, speed, xVelocity, yVelocity;
    private Enemy target;
    private boolean alive;

    public Projectile(Texture texture, Tile tile, float speed, int damage, Enemy target)
    {
        this.texture = texture;
        this.tile = tile;
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
        Draw.drawTexture(texture, (int) x, (int) y);
    }

    public void update()
    {
        x += xVelocity * speed * Clock.delta();
        y += yVelocity * speed * Clock.delta();

        if (x < -texture.getTextureWidth() || x > DisplayManager.getScreenWidth() + texture.getTextureWidth() ||
                y < -texture.getTextureHeight() || y > DisplayManager.getScreenHeight() + texture.getTextureHeight())
            alive = false;
    }

    public boolean isAlive()
    {
        return alive;
    }
}
