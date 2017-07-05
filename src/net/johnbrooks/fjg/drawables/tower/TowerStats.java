package net.johnbrooks.fjg.drawables.tower;

import org.newdawn.slick.opengl.Texture;

/**
 * Created by ieatl on 7/4/2017.
 */
public class TowerStats
{
    private Texture baseTexture, topTexture;
    private int cost, viewDistance;
    private float warmUp;

    public TowerStats(Texture baseTexture, Texture topTexture, int cost, float warmUp, int viewDistance)
    {
        this.baseTexture = baseTexture;
        this.topTexture = topTexture;
        this.cost = cost;
        this.warmUp = warmUp;
        this.viewDistance = viewDistance;
    }

    public Texture getBaseTexture()
    {
        return baseTexture;
    }

    public Texture getTopTexture()
    {
        return topTexture;
    }

    public int getCost()
    {
        return cost;
    }

    public float getWarmUp()
    {
        return warmUp;
    }

    public int getViewDistance()
    {
        return viewDistance;
    }
}
