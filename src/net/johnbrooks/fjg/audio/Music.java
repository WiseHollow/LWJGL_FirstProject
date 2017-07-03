package net.johnbrooks.fjg.audio;

/**
 * Created by ieatl on 7/3/2017.
 */
public enum Music
{
    A_MINI_DISCOVERY("res/music/aMiniDiscovery.wav"), COUNTRY_ADVENTURE("res/music/countryAdventure.wav"), COUNTRY_JOY("res/music/countryJoy.wav");

    String path;

    Music(String path)
    {
        this.path = path;
    }

    @Override
    public String toString()
    {
        return path;
    }
}
