package net.johnbrooks.fjg.audio;

/**
 * Created by ieatl on 7/3/2017.
 */
public enum Music
{
    //Accredit http://www.nosoapradio.us for railJet.wav
    //OpenGameArt.org for towerDefenseTheme.wav
    //

    TOWER_DEFENSE_THEME("res/music/towerDefenseTheme.wav"),
    RAIL_JET("res/music/railJet.wav"),
    ERH_BLUE_BEAT("res/music/erh_blueBeat.wav");

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
