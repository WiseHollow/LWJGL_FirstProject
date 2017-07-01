package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import org.newdawn.slick.util.Log;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Wave
{
    private Level level;
    private float timeSinceLastSpawn, timeUntilSpawn;
    private EnemyTemplate enemyTemplate;
    private int amountToSpawn, amountSpawned;
    private boolean active, finished;

    public Wave(Level level, float timeUntilSpawn, EnemyTemplate enemyTemplate, int amountToSpawn)
    {
        this.level = level;
        this.enemyTemplate = enemyTemplate;
        this.timeUntilSpawn = timeUntilSpawn;
        this.timeSinceLastSpawn = timeUntilSpawn;
        this.amountToSpawn = amountToSpawn;
        this.amountSpawned = 0;
        this.active = false;
        this.finished = false;
    }

    public void update()
    {
        if (active && !finished)
        {
            if (amountSpawned < amountToSpawn)
            {
                timeSinceLastSpawn += Clock.delta();
                if (timeSinceLastSpawn > timeUntilSpawn)
                {
                    spawn();
                    amountSpawned++;
                    timeSinceLastSpawn = 0;
                }
            }
            else
            {
                active = false;
                finished = true;
            }
        }
    }

    public void draw()
    {

    }

    private void spawn()
    {
        Enemy enemy = new Enemy(enemyTemplate, level, level.getCheckpointList().get(0).getTile().getX(), level.getCheckpointList().get(0).getTile().getY());
        //enemy.setTileX(level.getCheckpointList().get(0).getTile().getXSlot());
        //enemy.setTileY(level.getCheckpointList().get(0).getTile().getYSlot());
        level.getWaveManager().getEnemyList().add(enemy);
        Log.info("Enemy spawned!");
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public Level getLevel()
    {
        return level;
    }
}
