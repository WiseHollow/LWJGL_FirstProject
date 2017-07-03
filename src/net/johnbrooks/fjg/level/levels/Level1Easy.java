package net.johnbrooks.fjg.level.levels;

import net.johnbrooks.fjg.Player;
import net.johnbrooks.fjg.Scheduler;
import net.johnbrooks.fjg.audio.AudioManager;
import net.johnbrooks.fjg.audio.Music;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.level.*;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Level1Easy extends Level
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

    public Level1Easy()
    {
        super("level01");
        //tileGrid = new TileGrid(map);
    }

    @Override
    public void init()
    {
        super.init();

        // Let's create our template to make our first wave.
        EnemyTemplate enemyTemplate = new EnemyTemplate(GameTexture.ENEMY.getTexture(), 64, 64, 10, 45);
        EnemyTemplate enemyTemplate2 = new EnemyTemplate(GameTexture.ENEMY.getTexture(), 64, 64, 20, 50);
        EnemyTemplate enemyTemplate3 = new EnemyTemplate(GameTexture.ENEMY.getTexture(), 64, 64, 30, 100);
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

        save();
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
