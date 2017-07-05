package net.johnbrooks.fjg.drawables.tower.junk;

/**
 * Created by ieatl on 6/30/2017.
 */
public class IceTowerCannon// extends Tower
{
    /*private Texture topTexture;
    private float rotation, slowMultiplier;

    public IceTowerCannon(Level level, Texture baseTexture, Texture topTexture, Tile tile, int damage, int cost, float warmUpTime, float distanceView, List<Enemy> enemyList)
    {
        super(level, baseTexture, tile, damage, cost, warmUpTime, distanceView, enemyList);
        this.topTexture = topTexture;
        this.rotation = 0f;
        this.slowMultiplier = 0.1f;
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
    }*/
}
