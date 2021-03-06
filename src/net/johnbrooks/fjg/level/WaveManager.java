package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.Scheduler;
import net.johnbrooks.fjg.audio.AudioManager;
import net.johnbrooks.fjg.audio.Sound;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import net.johnbrooks.fjg.drawables.entities.EnemyTemplate;
import net.johnbrooks.fjg.state.states.Game;
import org.newdawn.slick.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 7/1/2017.
 */
public class WaveManager
{
    private Level level;
    private int nextWaveIndex;
    private List<Wave> waveList;
    private List<Enemy> enemyList;
    private boolean started;

    public WaveManager(Level level)
    {
        this.level = level;
        this.started = false;
        this.nextWaveIndex = 0;
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

    public void restart()
    {
        this.started = false;
        this.nextWaveIndex = 0;
        for (Wave wave : waveList)
            wave.reset();
        //this.waveList = new ArrayList<>();
    }

    /**
     * Add one of each enemy type to the current set of waves. This is only planned to be used in LevelSurvival.
     */
    public void stackAllEnemies()
    {
        for (Wave wave : waveList)
            wave.addEnemies(EnemyTemplate.FLY, EnemyTemplate.MOUSE, EnemyTemplate.FROG, EnemyTemplate.MOUSE_FAST, EnemyTemplate.LADY_BUG, EnemyTemplate.BEE, EnemyTemplate.GREEN_SLIME, EnemyTemplate.BARNACLE, EnemyTemplate.PINK_SLIME, EnemyTemplate.SPIDER, EnemyTemplate.BLUE_SLIME, EnemyTemplate.GHOST, EnemyTemplate.SPINNER);

    }

    public boolean startWave()
    {
        started = true;
        if (nextWaveIndex >= waveList.size())
        {
            return false;
        }
        else
        {
            System.out.println("Starting a new wave!");
            Wave wave = waveList.get(nextWaveIndex);
            wave.setActive(true);
            return true;
        }
    }

    public void update()
    {
        for (Wave wave : waveList)
            wave.update();
        for (Enemy enemy : enemyList)
            enemy.update();

        if (started)
        {
            for (int i = 0; i < enemyList.size(); i++)
            {
                Enemy enemy = enemyList.get(i);
                enemy.update();
                if (!enemy.isAlive())
                {
                    enemyList.remove(enemy);
                    i--;
                }
            }

            if (enemyList.isEmpty() && waveList.get(nextWaveIndex).isFinished())
            {
                if (nextWaveIndex + 1 >= waveList.size())
                {
                    completeLevel();
                    return;
                }

                // Reward players for the amount of health they have remaining.
                level.getPlayer().modifyCoins(level.getPlayer().getHealth() + ((int) (level.getPlayer().getCoins() * 0.1f)));
                AudioManager.getInstance().play(Sound.COIN_REWARD);

                nextWaveIndex++;
                Scheduler.getInstance().doTaskLater(() -> startWave(), 5);
                System.out.println("Starting next wave in 5 seconds!");
            }
        }
    }

    private void completeLevel()
    {
        started = false;
        System.out.println("You've completed the level!");
        level.setComplete(true);

        //boolean isNextLevel = Game.getInstance().getLevelManager().nextLevel();
        //if (!isNextLevel)
        //    System.out.println("You win the game!");
        //else
        //    level.setComplete(true);
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
