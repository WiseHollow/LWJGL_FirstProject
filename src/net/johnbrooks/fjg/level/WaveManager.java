package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.drawables.entities.Enemy;
import org.newdawn.slick.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 7/1/2017.
 */
public class WaveManager
{
    private int waveIndex;
    private List<Wave> waveList;
    private List<Enemy> enemyList;

    public WaveManager()
    {
        this.waveIndex = 0;
        this.waveList = new ArrayList<>();
        this.enemyList = new ArrayList<>();
    }

    public void addWave(Wave wave)
    {
        waveList.add(wave);
    }
    public void addWaves(Wave... waves)
    {
        for (Wave w : waves)
            addWave(w);
    }
    public Wave getWave(int index) { return waveList.get(index); }

    public boolean startWave()
    {
        if (waveIndex >= waveList.size())
        {
            return false;
        }
        else
        {
            Log.info("Start a new wave!");
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
        for (Enemy enemy : enemyList)
            enemy.update();

        for (int i = 0; i < enemyList.size(); i++)
        {
            Enemy enemy = enemyList.get(i);
            enemy.update();
            if (!enemy.isAlive())
            {
                enemyList.remove(enemy);
                i--;
                if (enemyList.isEmpty())
                    startWave();
            }
        }
    }

    public void draw()
    {
        for (Wave wave : waveList)
            wave.draw();
        for (Enemy enemy : enemyList)
            enemy.draw();
    }

    public List<Enemy> getEnemyList()
    {
        return enemyList;
    }
}
