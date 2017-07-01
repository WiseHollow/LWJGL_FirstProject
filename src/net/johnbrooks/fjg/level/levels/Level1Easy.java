package net.johnbrooks.fjg.level.levels;

import net.johnbrooks.fjg.Player;
import net.johnbrooks.fjg.drawables.GameTexture;
import net.johnbrooks.fjg.drawables.tower.TowerCannon;
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
                    { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
            };

    public Level1Easy()
    {
        super();
        tileGrid = new TileGrid(map);
        player = new Player(this);

        // Create our initial checkpoint for spawning enemies. MUST BE DONE HERE. NOT IN INIT.
        Checkpoint spawnCheckPoint = new Checkpoint(this, tileGrid.getTile(0, 2), Direction.RIGHT);
        checkpointList.add(spawnCheckPoint);
    }

    @Override
    public void init()
    {
        super.init();

        // Let's create our template to make our first wave.
        EnemyTemplate enemyTemplate = new EnemyTemplate(GameTexture.ENEMY.getTexture(), 64, 64, 20, 30);
        // Create a wave based on the template.
        Wave wave = new Wave(this, 3, enemyTemplate, 3);
        Wave wave2 = new Wave(this, 2, enemyTemplate, 5);
        // Add and start the wave in the WaveManager.
        waveManager.addWave(wave);
        waveManager.addWave(wave2);
        waveManager.startWave();
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
