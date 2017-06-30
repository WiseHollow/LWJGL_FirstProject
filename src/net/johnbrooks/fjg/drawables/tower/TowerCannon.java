package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.Log;

/**
 * Created by ieatl on 6/30/2017.
 */
public class TowerCannon extends Tower
{
    private Texture topTexture;

    public TowerCannon(Texture baseTexture, Texture topTexture, Tile tile, int damage, float warmUpTime)
    {
        super(baseTexture, tile, damage, warmUpTime);
        this.topTexture = topTexture;
    }

    @Override
    public void draw()
    {
        super.draw();
        if (topTexture != null)
            Draw.drawTexture(topTexture, x, y, width, height);

        for (Projectile projectile : projectileList)
            projectile.draw();
    }

    @Override
    public void update()
    {
        super.update();

        for (Projectile projectile : projectileList)
            projectile.update();
    }

    @Override
    protected void shoot()
    {
        timeSinceLastShot = 0;
        Projectile projectile = new Projectile(GameTexture.BULLET.getTexture(), tile, 20f, damage);
        projectileList.add(projectile);
    }
}
