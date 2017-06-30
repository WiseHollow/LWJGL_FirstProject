package net.johnbrooks.fjg.drawables.tower;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.Draw;
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
    private float x, y, speed;

    public Projectile(Texture texture, Tile tile, float speed, int damage)
    {
        this.texture = texture;
        this.tile = tile;
        this.speed = speed;
        this.damage = damage;
        //TODO: Needs to change to the tip of the gun
        this.x = tile.getX() + 16;
        this.y = tile.getY() + 16;
    }

    public void draw()
    {
        Draw.drawTexture(texture, (int) x, (int) y);
    }

    public void update()
    {
        x += Clock.delta() * speed;
    }
}
