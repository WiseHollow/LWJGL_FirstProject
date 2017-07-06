package net.johnbrooks.fjg.level.levels;

import net.johnbrooks.fjg.Scheduler;
import net.johnbrooks.fjg.audio.AudioManager;
import net.johnbrooks.fjg.audio.Music;
import net.johnbrooks.fjg.drawables.entities.EnemyTemplate;
import net.johnbrooks.fjg.drawables.entities.EnemyTexture;
import net.johnbrooks.fjg.level.*;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Level01 extends Level
{
    private static int[][] map =
            {
                    { 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2 },
                    { 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 2, 2, 2, 2 },
                    { 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2 },
                    { 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 2, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                    { 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 2, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
                    { 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
            };

    public Level01()
    {
        super("level01");
        //tileGrid = new TileGrid(map);
    }

    @Override
    public void init()
    {
        super.init();

        // Add and start the wave in the WaveManager.
        waveManager.addWaves(new Wave(this, 3.0f, EnemyTemplate.MOUSE, 6),
                new Wave(this, 2.5f, EnemyTemplate.GREEN_SLIME, 4),
                new Wave(this, 3.0f, EnemyTemplate.PINK_SLIME, 3),
                new Wave(this, 1.0f, EnemyTemplate.MOUSE, 12),
                new Wave(this, 3.0f, EnemyTemplate.MOUSE_FAST, 6),
                new Wave(this, 2.0f, EnemyTemplate.SPIDER, 4),
                new Wave(this, 2.0f, EnemyTemplate.PINK_SLIME, 8),
                new Wave(this, 2.0f, EnemyTemplate.BLUE_SLIME, 8),
                new Wave(this, 1.0f, EnemyTemplate.BLUE_SLIME, 14),
                new Wave(this, 1.0f, EnemyTemplate.SPIDER, 12),
                new Wave(this, 3.0f, EnemyTemplate.SPINNER, 3));

        //TODO: Display for how long until wave starts
        Scheduler.getInstance().doTaskLater(() -> waveManager.startWave(), 5);

        AudioManager.getInstance().play(Music.TOWER_DEFENSE_THEME, true);
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
