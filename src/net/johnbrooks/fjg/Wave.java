package net.johnbrooks.fjg;

import net.johnbrooks.fjg.drawables.entities.Enemy;
import org.newdawn.slick.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 6/29/2017.
 */
public class Wave
{
    private float timeSinceLastSpawn, timeUntilSpawn;
    private Enemy enemyToSpawn;
    private List<Enemy> enemyList;

    public Wave(float timeUntilSpawn, Enemy enemyToSpawn)
    {
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
        enemyList.add(new Enemy(enemyToSpawn));
    }
}
