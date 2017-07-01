package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 6/29/2017.
 */
public abstract class Level
{
    protected TileGrid tileGrid;
    protected WaveManager waveManager;
    protected Player player;

    protected List<Checkpoint> checkpointList;

    public Level()
    {
        waveManager = new WaveManager();
        checkpointList = new ArrayList<>();
    }

    public List<Checkpoint> getCheckpointList() { return checkpointList; }

    public void init()
    {
        // Only let us search for paths, if we haven't already before.
        if (checkpointList.size() == 1)
        {
            // Keep track of if we have found the end of the path.
            boolean foundEnd = false;
            Checkpoint startingCheckpoint = checkpointList.get(0);
            Checkpoint futureCheckpoint = startingCheckpoint;
            while (!foundEnd)
            {
                // Grab the very next checkpoint.
                futureCheckpoint = futureCheckpoint.calculateNextCheckpoint();
                if (futureCheckpoint != null)
                    checkpointList.add(futureCheckpoint);
                else
                    foundEnd = true;
                // Record if found, exit loop if hit end.
            }
        }
    }

    public TileGrid getTileGrid()
    {
        return tileGrid;
    }

    public void update()
    {
        tileGrid.update();
        waveManager.update();
        player.update();
    }

    public void draw()
    {
        tileGrid.draw();
        waveManager.draw();
        player.draw();
    }
}
