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

    private int amount;

    public LevelSurvival()
    {
        super("level01");
        amount = 1;
        name = "survival";
        //tileGrid = new TileGrid(map);
    }

    @Override
    public void init()
    {
        super.init();

        // Add and start the wave in the WaveManager.
        setupWaves();

        //TODO: Display for how long until wave starts
        Scheduler.getInstance().doTaskLater(() -> waveManager.startWave(), 5);
    }

    private void setupWaves()
    {
        waveManager.addWaves
                (
                        new Wave(this, 1.0f, amount, EnemyTemplate.FLY),
                        new Wave(this, 1.0f, amount, EnemyTemplate.FLY, EnemyTemplate.MOUSE),
                        new Wave(this, 1.0f, amount, EnemyTemplate.FLY, EnemyTemplate.MOUSE, EnemyTemplate.FROG),
                        new Wave(this, 1.0f, amount, EnemyTemplate.FLY, EnemyTemplate.MOUSE, EnemyTemplate.FROG, EnemyTemplate.MOUSE_FAST),
                        new Wave(this, 1.0f, amount, EnemyTemplate.FLY, EnemyTemplate.MOUSE, EnemyTemplate.FROG, EnemyTemplate.MOUSE_FAST, EnemyTemplate.LADY_BUG),
                        new Wave(this, 1.0f, amount, EnemyTemplate.FLY, EnemyTemplate.MOUSE, EnemyTemplate.FROG, EnemyTemplate.MOUSE_FAST, EnemyTemplate.LADY_BUG, EnemyTemplate.BEE),
                        new Wave(this, 1.0f, amount, EnemyTemplate.FLY, EnemyTemplate.MOUSE, EnemyTemplate.FROG, EnemyTemplate.MOUSE_FAST, EnemyTemplate.LADY_BUG, EnemyTemplate.BEE, EnemyTemplate.GREEN_SLIME),
                        new Wave(this, 1.0f, amount, EnemyTemplate.FLY, EnemyTemplate.MOUSE, EnemyTemplate.FROG, EnemyTemplate.MOUSE_FAST, EnemyTemplate.LADY_BUG, EnemyTemplate.BEE, EnemyTemplate.GREEN_SLIME, EnemyTemplate.BARNACLE),
                        new Wave(this, 1.0f, amount, EnemyTemplate.FLY, EnemyTemplate.MOUSE, EnemyTemplate.FROG, EnemyTemplate.MOUSE_FAST, EnemyTemplate.LADY_BUG, EnemyTemplate.BEE, EnemyTemplate.GREEN_SLIME, EnemyTemplate.BARNACLE, EnemyTemplate.PINK_SLIME),
                        new Wave(this, 1.0f, amount, EnemyTemplate.FLY, EnemyTemplate.MOUSE, EnemyTemplate.FROG, EnemyTemplate.MOUSE_FAST, EnemyTemplate.LADY_BUG, EnemyTemplate.BEE, EnemyTemplate.GREEN_SLIME, EnemyTemplate.BARNACLE, EnemyTemplate.PINK_SLIME, EnemyTemplate.SPIDER),
                        new Wave(this, 1.0f, amount, EnemyTemplate.FLY, EnemyTemplate.MOUSE, EnemyTemplate.FROG, EnemyTemplate.MOUSE_FAST, EnemyTemplate.LADY_BUG, EnemyTemplate.BEE, EnemyTemplate.GREEN_SLIME, EnemyTemplate.BARNACLE, EnemyTemplate.PINK_SLIME, EnemyTemplate.SPIDER, EnemyTemplate.BLUE_SLIME),
                        new Wave(this, 1.0f, amount, EnemyTemplate.FLY, EnemyTemplate.MOUSE, EnemyTemplate.FROG, EnemyTemplate.MOUSE_FAST, EnemyTemplate.LADY_BUG, EnemyTemplate.BEE, EnemyTemplate.GREEN_SLIME, EnemyTemplate.BARNACLE, EnemyTemplate.PINK_SLIME, EnemyTemplate.SPIDER, EnemyTemplate.BLUE_SLIME, EnemyTemplate.GHOST),
                        new Wave(this, 1.0f, amount, EnemyTemplate.FLY, EnemyTemplate.MOUSE, EnemyTemplate.FROG, EnemyTemplate.MOUSE_FAST, EnemyTemplate.LADY_BUG, EnemyTemplate.BEE, EnemyTemplate.GREEN_SLIME, EnemyTemplate.BARNACLE, EnemyTemplate.PINK_SLIME, EnemyTemplate.SPIDER, EnemyTemplate.BLUE_SLIME, EnemyTemplate.GHOST, EnemyTemplate.SPINNER)
                );

        amount += 1;
    }

    @Override
    public void softReset()
    {
        super.softReset();
        setupWaves();
    }

    @Override
    public void setComplete(boolean complete)
    {
        if (complete)
            softReset();
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
