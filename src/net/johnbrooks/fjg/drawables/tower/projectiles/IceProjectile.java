package net.johnbrooks.fjg.drawables.tower.projectiles;

import net.johnbrooks.fjg.drawables.entities.Enemy;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import net.johnbrooks.fjg.drawables.tower.Tower;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 6/30/2017.
 */
public class IceProjectile extends Projectile
{
    private float slowMultiplier;

    public IceProjectile(Tower shooter, Texture texture, Tile tile, float speed, int damage, Enemy target, float slowMultiplier)
    {
        super(shooter, texture, tile, speed, damage, target);
        this.slowMultiplier = slowMultiplier;
    }

    @Override
    public void draw()
    {
        super.draw();
    }

    @Override
    public void update()
    {
        super.update();

        if (colliding != null)
            colliding.setSlowMultiplier(slowMultiplier);
    }
}
