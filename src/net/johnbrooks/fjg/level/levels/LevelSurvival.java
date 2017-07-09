package net.johnbrooks.fjg.level.levels;

import net.johnbrooks.fjg.Scheduler;
import net.johnbrooks.fjg.drawables.entities.EnemyTemplate;
import net.johnbrooks.fjg.level.Level;
import net.johnbrooks.fjg.level.Wave;

/**
 * Created by ieatl on 6/29/2017.
 */
public class LevelSurvival extends Level
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

    public LevelSurvival()
    {
        super("level01");
        name = "survival";
        //tileGrid = new TileGrid(map);
    }

    @Override
    public void init()
    {
        super.init();

        // Add and start the wave in the WaveManager.
        waveManager.addWaves
                (
                        new Wave(this, 1.0f, EnemyTemplate.FLY, 3),
                        new Wave(this, 1.0f, EnemyTemplate.MOUSE, 3),
                        new Wave(this, 1.0f, EnemyTemplate.FROG, 3),
                        new Wave(this, 1.0f, EnemyTemplate.MOUSE_FAST, 3),
                        new Wave(this, 1.0f, EnemyTemplate.LADY_BUG, 3),
                        new Wave(this, 1.0f, EnemyTemplate.BEE, 3),
                        new Wave(this, 1.0f, EnemyTemplate.GREEN_SLIME, 3),
                        new Wave(this, 1.0f, EnemyTemplate.BARNACLE, 3),
                        new Wave(this, 1.0f, EnemyTemplate.PINK_SLIME, 3),
                        new Wave(this, 1.0f, EnemyTemplate.SPIDER, 3),
                        new Wave(this, 1.0f, EnemyTemplate.BLUE_SLIME, 3),
                        new Wave(this, 1.0f, EnemyTemplate.GHOST, 3),
                        new Wave(this, 1.0f, EnemyTemplate.SPINNER, 3)
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
