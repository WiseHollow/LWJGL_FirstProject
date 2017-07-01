package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.level.Level;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.Log;

import java.util.List;

/**
 * Created by ieatl on 6/30/2017.
 */
public class TowerCannon extends Tower
{
    private Texture topTexture;
    private float rotation;

    public TowerCannon(Level level, Texture baseTexture, Texture topTexture, Tile tile, int damage, float warmUpTime, float distanceView, List<Enemy> enemyList)
    {
        super(level, baseTexture, tile, damage, warmUpTime, distanceView, enemyList);
        this.topTexture = topTexture;
        this.rotation = 0f;
    }

    @Override
    public void draw()
    {
        super.draw();
        if (topTexture != null)
            Draw.drawTextureRotation(topTexture, x, y, rotation);

        for (Projectile projectile : projectileList)
            projectile.draw();
    }

    @Override
    public void update()
    {
        super.update();

        if (target == null)
            rotation += Clock.delta() * 30;
        else
            rotation = calculateAngleToTarget();

        for (int i = 0; i < projectileList.size(); i++)
        {
            Projectile projectile = projectileList.get(i);
            if (projectile.isAlive())
                projectile.update();
            else
            {
                projectileList.remove(projectile);
                i--;
            }
        }
    }

    @Override
    protected void shoot()
    {
        if (target != null)
        {
            timeSinceLastShot = 0;
            Projectile projectile = new Projectile(GameTexture.BULLET.getTexture(), tile, 150f, damage, target);
            projectileList.add(projectile);
        }
    }
}
