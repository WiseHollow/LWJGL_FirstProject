package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.Player;
import net.johnbrooks.fjg.drawables.tiles.TileType;

import java.io.*;
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

    public Player getPlayer() { return player; }
    public WaveManager getWaveManager() { return waveManager; }
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

        //save();
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

    @Override
    public String toString()
    {
        String data = "";

        data+="Spawn Tile=" + (getCheckpointList().isEmpty() ? "" : getCheckpointList().get(0).getTile().getXSlot() + "," + getCheckpointList().get(0).getTile().getYSlot()) + '\n';
        data+="Spawn Direction=" + (getCheckpointList().isEmpty() ? "" : getCheckpointList().get(0).getDirection().name()) + '\n';

        for (int y = 0; y < TileGrid.TILES_HIGH; y++)
        {
            for (int x = 0; x < TileGrid.TILES_WIDE; x++)
            {
                data += tileGrid.getTile(x, y).getTileType().ordinal() + " ";
            }
            data += '\n';
        }

        return data;
    }

    public boolean load(String levelName)
    {
        File file = new File(levelName);
        if (!file.exists())
            return false;

        int spawnX = -1;
        int spawnY = -1;
        Direction direction = null;

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int y = 0;
            while ((line = reader.readLine()) != null)
            {
                int x = 0;
                // Parse line

                if (line.startsWith("Spawn Tile="))
                {
                    String[] spawnPoint = line.replace("Spawn Tile=", "").split(",");

                    spawnX = Integer.parseInt(spawnPoint[0]);
                    spawnY = Integer.parseInt(spawnPoint[1]);

                    continue;
                }
                else if (line.startsWith("Spawn Direction="))
                {
                    line = line.replace("Spawn Direction=", "");
                    direction = Direction.valueOf(line);
                    continue;
                }

                String[] elements = line.split(" ");
                for (String key : elements)
                {
                    if (!key.equals(""))
                    {
                        int id = Integer.parseInt(key);
                        TileType tileType = TileType.getTileType(id);
                        tileGrid.setTile(x, y, tileType);
                        x++;
                    }
                }
                y++;
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        getCheckpointList().clear();
        if (spawnX != -1 && spawnY != -1 && direction != null)
        {
            getCheckpointList().add(new Checkpoint(this, getTileGrid().getTile(spawnX, spawnY), direction));
            System.out.println("Loaded spawn point at: " + spawnX + "," + spawnY + " facing: " + direction.name());
        }

        return true;
    }

    public boolean save()
    {
        File file = new File("test.dat");
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(this.toString());
            writer.close();
            return true;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
