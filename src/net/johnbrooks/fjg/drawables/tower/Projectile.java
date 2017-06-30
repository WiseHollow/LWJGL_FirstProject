package net.johnbrooks.fjg.drawables.tower;

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
    private float speed;
    private int x, y;

    public Projectile(Texture texture, Tile tile, float speed, int damage)
    {
        this.texture = texture;
        this.tile = tile;
        this.speed = speed;
        this.damage = damage;
        this.x = tile.getX();
        this.y = tile.getY();
    }

    public void draw()
    {
        Draw.drawTexture(texture, x, y);
    }

    public void update()
    {

    }
}
