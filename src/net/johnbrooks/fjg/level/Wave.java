package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.drawables.entities.Enemy;
import net.johnbrooks.fjg.drawables.entities.EnemyTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Wave
{
    private Level level;
    private float timeSinceLastSpawn, timeUntilSpawn;
    private EnemyTemplate[] enemyTemplates;
    private int amountToSpawn, amountSpawned;
    private boolean active, finished;

    public Wave(Level level, float timeUntilSpawn, int amountToSpawn, EnemyTemplate... enemyTemplates)
    {
        this.level = level;
        this.enemyTemplates = enemyTemplates;
        this.timeUntilSpawn = timeUntilSpawn;
        this.timeSinceLastSpawn = timeUntilSpawn;
        this.amountToSpawn = amountToSpawn;
        this.amountSpawned = 0;
        this.active = false;
        this.finished = false;
    }

    public int getAmountToSpawn()
    {
        return amountToSpawn;
    }

    public void setAmountToSpawn(int amountToSpawn)
    {
        this.amountToSpawn = amountToSpawn;
    }

    public void reset()
    {
        this.finished = false;
        this.amountSpawned = 0;
    }

    public void addEnemies(EnemyTemplate... enemyTemplates)
    {
        //int length = this.enemyTemplates.length + enemyTemplates.length;
        //EnemyTemplate[] enemyTemplates1 = new EnemyTemplate[length];
        //int index = 0;
        //for (; index < this.enemyTemplates.length; index++)
        //{
        //    enemyTemplates1[index] = this.enemyTemplates[index];
        //}
        //for (; index < length; index++)
        //{
        //    int i = index - this.enemyTemplates.length;
        //    enemyTemplates1[index] = enemyTemplates[i];
        //}

        List<EnemyTemplate> enemyTemplateList = new ArrayList<>();
        for (EnemyTemplate enemyTemplate : this.enemyTemplates)
            enemyTemplateList.add(enemyTemplate);
        for (EnemyTemplate enemyTemplate : enemyTemplates)
            enemyTemplateList.add(enemyTemplate);

        this.enemyTemplates = enemyTemplateList.toArray(new EnemyTemplate[enemyTemplateList.size()]);
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
        for (int i = 0; i < enemyTemplates.length; i++)
        {
            Enemy enemy = new Enemy(enemyTemplates[i], level, level.getCheckpointList().get(0).getTile().getX(), level.getCheckpointList().get(0).getTile().getY());
            //enemy.setTileX(level.getCheckpointList().get(0).getTile().getXSlot());
            //enemy.setTileY(level.getCheckpointList().get(0).getTile().getYSlot());
            level.getWaveManager().getEnemyList().add(enemy);
        }
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public boolean isFinished()
    {
        return finished;
    }
    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }

    public Level getLevel()
    {
        return level;
    }
}
