package net.johnbrooks.fjg.level.levels;

import net.johnbrooks.fjg.Scheduler;
import net.johnbrooks.fjg.audio.AudioManager;
import net.johnbrooks.fjg.audio.Music;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.entities.EnemyTexture;
import net.johnbrooks.fjg.level.EnemyTemplate;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.level.Wave;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Level2Easy extends Level
{
    private static int[][] map =
            {
                    { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 0, 0, 0, 0, 0, 0  },
                    { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0  },
                    { 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                    { 2, 2, 2, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                    { 2, 2, 3, 3, 3, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1  },
                    { 2, 3, 3, 3, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0  },
                    { 2, 3, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 3  },
                    { 2, 3, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 3, 3  },
                    { 3, 3, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 3, 3, 3  },
                    { 3, 3, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 3, 3, 2  },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 3, 3, 2  },
                    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 3, 3, 3, 2, 2  },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 2, 2  },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 2, 2, 2  },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 0, 3, 3, 3, 3, 2, 2, 2, 2  }
            };

    public Level2Easy()
    {
        super("level02");
        //tileGrid = new TileGrid(map);
    }

    @Override
    public void init()
    {
        super.init();

        // Let's create our template to make our first wave.
        EnemyTemplate enemyTemplate = new EnemyTemplate(EnemyTexture.MOUSE, 10, 50);
        EnemyTemplate enemyTemplate2 = new EnemyTemplate(EnemyTexture.MOUSE, 20, 55);
        EnemyTemplate enemyTemplate3 = new EnemyTemplate(EnemyTexture.MOUSE, 30, 100);
        // Create a wave based on the template.
        Wave wave = new Wave(this, 3, enemyTemplate, 4);
        Wave wave2 = new Wave(this, 2, enemyTemplate, 6);
        Wave wave3 = new Wave(this, 1, enemyTemplate, 8);
        Wave wave4 = new Wave(this, 1, enemyTemplate2, 6);
        Wave wave5 = new Wave(this, 1, enemyTemplate2, 8);
        Wave wave6 = new Wave(this, 1, enemyTemplate3, 6);
        Wave wave7 = new Wave(this, 1, enemyTemplate3, 8);
        // Add and start the wave in the WaveManager.
        waveManager.addWaves(wave, wave2, wave3, wave4, wave5, wave6, wave7);

        //TODO: Display for how long until wave starts
        Scheduler.getInstance().doTaskLater(() -> waveManager.startWave(), 5);

        AudioManager.getInstance().play(Music.A_MINI_DISCOVERY, true);
    }

    @Override
    public void draw()
    {
        super.draw();
    }

    @Override
    public void update()
    {
        super.update();
    }
}
