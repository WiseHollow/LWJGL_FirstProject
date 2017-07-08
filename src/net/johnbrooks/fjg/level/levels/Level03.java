package net.johnbrooks.fjg.level.levels;

import net.johnbrooks.fjg.Scheduler;
import net.johnbrooks.fjg.drawables.entities.EnemyTemplate;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.level.Wave;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Level03 extends Level
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

    public Level03()
    {
        super("level03");
        //tileGrid = new TileGrid(map);
    }

    @Override
    public void init()
    {
        super.init();

        // Add and start the wave in the WaveManager.
        waveManager.addWaves
                (
                        new Wave(this, 2.0f, EnemyTemplate.BARNACLE, 2),
                        new Wave(this, 1.5f, EnemyTemplate.GREEN_SLIME, 4),
                        new Wave(this, 1.5f, EnemyTemplate.BEE, 6),
                        new Wave(this, 1.5f, EnemyTemplate.GREEN_SLIME, 8),
                        new Wave(this, 1.0f, EnemyTemplate.MOUSE_FAST, 8),
                        new Wave(this, 1.5f, EnemyTemplate.PINK_SLIME, 8),
                        new Wave(this, 1.0f, EnemyTemplate.PINK_SLIME, 8),
                        new Wave(this, 2.0f, EnemyTemplate.SPIDER, 10),
                        new Wave(this, 1.0f, EnemyTemplate.PINK_SLIME, 14),
                        new Wave(this, 2.0f, EnemyTemplate.SPIDER, 16),
                        new Wave(this, 1.0f, EnemyTemplate.BLUE_SLIME, 10),
                        new Wave(this, 1.0f, EnemyTemplate.BLUE_SLIME, 14),
                        new Wave(this, 1.0f, EnemyTemplate.SPIDER, 20),
                        new Wave(this, 1.0f, EnemyTemplate.SPIDER, 25),
                        new Wave(this, 3.0f, EnemyTemplate.SPINNER, 3)
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
