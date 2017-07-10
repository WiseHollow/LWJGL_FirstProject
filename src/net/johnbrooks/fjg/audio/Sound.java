package net.johnbrooks.fjg.audio;

/**
 * Created by ieatl on 7/5/2017.
 */
public enum Sound
{
    CANNON_FIRE("res/sounds/cannon-fire.wav"), COIN_REWARD("res/sounds/coin-reward.wav"), EXPLOSION("res/sounds/explosion.wav"), PICKUP("res/sounds/itemPickup.wav");

    String path;

    Sound(String path)
    {
        this.path = path;
    }

    @Override
    public String toString()
    {
        return path;
    }
}
