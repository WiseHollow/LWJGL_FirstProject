package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.Draw;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.tiles.Tile;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 6/30/2017.
 */
public class TowerCannon extends Tower
{
    private Texture topTexture;
    private float rotation;

    public TowerCannon(Texture baseTexture, Texture topTexture, Tile tile, int damage, float warmUpTime)
    {
        super(baseTexture, tile, damage, warmUpTime);
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

        rotation += Clock.delta() * 3;

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
