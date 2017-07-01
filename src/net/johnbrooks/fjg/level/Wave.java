package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import org.newdawn.slick.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Wave
{
    private Level level;
    private float timeSinceLastSpawn, timeUntilSpawn;
    private EnemyTemplate enemyTemplate;
    private List<Enemy> enemyList;
    private int amountToSpawn, amountSpawned;
    private boolean active, finished;

    public Wave(Level level, float timeUntilSpawn, EnemyTemplate enemyTemplate, int amountToSpawn)
    {
        this.level = level;
        this.enemyList = new ArrayList<>();
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
        if (active)
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

        for (int i = 0; i < enemyList.size(); i++)
        {
            Enemy enemy = enemyList.get(i);
            enemy.update();
            if (!enemy.isAlive())
            {
                enemyList.remove(enemy);
                i--;
                Log.info("Enemy removed. Wave contains " + enemyList.size() + " remaining enemies.");
            }
        }
    }

    public void draw()
    {
        for (Enemy e : enemyList)
            e.draw();
    }

    private void spawn()
    {
        Enemy enemy = new Enemy(enemyTemplate, level, level.getCheckpointList().get(0).getTile().getX(), level.getCheckpointList().get(0).getTile().getY());
        //enemy.setTileX(level.getCheckpointList().get(0).getTile().getXSlot());
        //enemy.setTileY(level.getCheckpointList().get(0).getTile().getYSlot());
        enemyList.add(enemy);
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
