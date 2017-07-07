package net.johnbrooks.fjg.level.levels;

import net.johnbrooks.fjg.Scheduler;
import net.johnbrooks.fjg.audio.AudioManager;
import net.johnbrooks.fjg.audio.Music;
import net.johnbrooks.fjg.drawables.entities.EnemyTemplate;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.level.Wave;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Level02 extends Level
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

    public Level02()
    {
        super("level02");
        //tileGrid = new TileGrid(map);
    }

    @Override
    public void init()
    {
        super.init();

        // Add and start the wave in the WaveManager.
        waveManager.addWaves
                (
                        new Wave(this, 1.2f, EnemyTemplate.MOUSE_FAST, 6),
                        new Wave(this, 1.0f, EnemyTemplate.MOUSE_FAST, 10),
                        new Wave(this, 2.0f, EnemyTemplate.GREEN_SLIME, 10),
                        new Wave(this, 2.0f, EnemyTemplate.PINK_SLIME, 6),
                        new Wave(this, 1.5f, EnemyTemplate.PINK_SLIME, 8),
                        new Wave(this, 1.0f, EnemyTemplate.MOUSE_FAST, 20),
                        new Wave(this, 2.0f, EnemyTemplate.SPIDER, 10),
                        new Wave(this, 1.0f, EnemyTemplate.PINK_SLIME, 10),
                        new Wave(this, 1.0f, EnemyTemplate.BLUE_SLIME, 6),
                        new Wave(this, 1.0f, EnemyTemplate.SPIDER, 14),
                        new Wave(this, 3.0f, EnemyTemplate.SPINNER, 6)
        );

        //TODO: Display for how long until wave starts
        Scheduler.getInstance().doTaskLater(() -> waveManager.startWave(), 5);
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
