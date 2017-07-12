package net.johnbrooks.fjg.drawables.drops;

import net.johnbrooks.fjg.drawables.Draw;
import org.newdawn.slick.opengl.Texture;

import java.util.Random;

/**
 * Created by ieatl on 7/9/2017.
 */
public enum DropType
{
    COIN(Draw.loadTexture("res/drops/coin.png"), 1, 0),
    SUPER_COIN(Draw.loadTexture("res/drops/coin.png"), 10, 0),
    ENERGY(Draw.loadTexture("res/drops/energy.png"), 0, 1);

    private static Random random = new Random();
    public static DropType getRandom()
    {
        int rInt = random.nextInt(100);
        if (rInt < 15)
            return ENERGY;
        else if (rInt < 25)
            return SUPER_COIN;
        else
            return COIN;
    }

    private Texture texture;
    private int coins;
    private int charge;

    DropType(Texture texture, int coins, int charge)
    {
        this.texture = texture;
        this.coins = coins;
        this.charge = charge;
    }

    public Texture getTexture()
    {
        return texture;
    }

    public int getCoins()
    {
        return coins;
    }

    public int getCharge()
    {
        return charge;
    }
}
