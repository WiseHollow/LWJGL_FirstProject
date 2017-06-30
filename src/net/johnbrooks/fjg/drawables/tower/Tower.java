package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.tiles.Tile;
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

    protected Tile tile;
    protected float timeSinceLastShot, warmUpTime;
    protected int x, y, width, height, damage;
    protected Texture baseTexture;

    protected List<Projectile> projectileList;

    public Tower(Texture baseTexture, Tile tile, int damage, float warmUpTime)
    {
        this.baseTexture = baseTexture;
        this.tile = tile;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.x = tile.getX();
        this.y = tile.getY();
        this.damage = damage;
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

    protected abstract void shoot();
}
