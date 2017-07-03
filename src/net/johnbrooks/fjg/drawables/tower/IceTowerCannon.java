package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.drawables.tower.projectiles.IceProjectile;
import net.johnbrooks.fjg.drawables.tower.projectiles.Projectile;
import net.johnbrooks.fjg.level.Level;
import org.newdawn.slick.opengl.Texture;

import java.util.List;

/**
 * Created by ieatl on 6/30/2017.
 */
public class IceTowerCannon extends Tower
{
    private Texture topTexture;
    private float rotation, slowMultiplier;

    public IceTowerCannon(Level level, Texture baseTexture, Texture topTexture, Tile tile, int damage, int cost, float warmUpTime, float distanceView, List<Enemy> enemyList, float slowMultiplier)
    {
        super(level, baseTexture, tile, damage, cost, warmUpTime, distanceView, enemyList);
        this.topTexture = topTexture;
        this.rotation = 0f;
        this.slowMultiplier = slowMultiplier;
    }

    @Override
    public void draw()
    {
        super.draw();
        if (topTexture != null)
            Draw.drawTexture(topTexture, x, y, rotation);
    }

    @Override
    public void update()
    {
        super.update();

        if (target == null)
            rotation += Clock.delta() * 30;
        else
            rotation = calculateAngleToTarget();
    }

    @Override
    protected void shoot()
    {
        if (target != null)
        {
            timeSinceLastShot = 0;
            projectileList.add(new IceProjectile(this, GameTexture.BULLET.getTexture(), tile, 400f, damage, target, slowMultiplier));
        }
    }
}
