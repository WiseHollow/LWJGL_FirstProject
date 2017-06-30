package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.entities.Enemy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Wave
{
    private Level level;
    private float timeSinceLastSpawn, timeUntilSpawn;
    private Enemy enemyToSpawn;
    private List<Enemy> enemyList;

    public Wave(Level level, float timeUntilSpawn, Enemy enemyToSpawn)
    {
        this.level = level;
        this.enemyList = new ArrayList<>();
        this.enemyToSpawn = enemyToSpawn;
        this.timeUntilSpawn = timeUntilSpawn;
        this.timeSinceLastSpawn = 0;
    }

    public void update()
    {
        timeSinceLastSpawn += Clock.delta();
        if (timeSinceLastSpawn > timeUntilSpawn)
        {
            spawn();
            timeSinceLastSpawn = 0;
        }

        for (Enemy e : enemyList)
            e.update();
    }

    public void draw()
    {
        for (Enemy e : enemyList)
            e.draw();
    }

    public void spawn()
    {
        if (enemyList.isEmpty())
        {
            Enemy enemy = new Enemy(enemyToSpawn);
            enemy.setTileX(level.getCheckpointList().get(0).getTile().getXSlot());
            enemy.setTileY(level.getCheckpointList().get(0).getTile().getYSlot());
            enemyList.add(enemy);
        }
    }

    public Level getLevel()
    {
        return level;
    }
}
