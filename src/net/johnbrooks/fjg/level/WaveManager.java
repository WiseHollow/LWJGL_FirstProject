package net.johnbrooks.fjg.level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 7/1/2017.
 */
public class WaveManager
{
    private int waveIndex;
    private List<Wave> waveList;

    public WaveManager()
    {
        this.waveIndex = 0;
        this.waveList = new ArrayList<>();
    }

    public void addWave(Wave wave)
    {
        waveList.add(wave);
    }

    public boolean startWave()
    {
        if (waveIndex >= waveList.size())
        {
            return false;
        }
        else
        {
            Wave wave = waveList.get(waveIndex);
            wave.setActive(true);
            waveIndex++;
            return true;
        }
    }

    public void update()
    {
        for (Wave wave : waveList)
            wave.update();
    }

    public void draw()
    {
        for (Wave wave : waveList)
            wave.draw();
    }
}
