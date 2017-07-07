package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.Clock;
import net.johnbrooks.fjg.Player;
import net.johnbrooks.fjg.drawables.entities.Checkpoint;
import net.johnbrooks.fjg.drawables.entities.Direction;
import net.johnbrooks.fjg.drawables.tiles.TileGrid;
import net.johnbrooks.fjg.drawables.tiles.TileType;
import net.johnbrooks.fjg.state.states.Game;
import net.johnbrooks.fjg.ui.HudGUI;
import net.johnbrooks.fjg.ui.LevelClearedGUI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 6/29/2017.
 */
public abstract class Level
{
    protected String name;
    protected TileGrid tileGrid;
    protected WaveManager waveManager;
    protected Player player;
    protected boolean complete;

    protected List<Checkpoint> checkpointList;

    protected HudGUI hudGUI;
    protected LevelClearedGUI levelClearedGUI;

    public Level(String name)
    {
        this.name = name;
        checkpointList = new ArrayList<>();
        load(name);
        //init();
    }

    public void init()
    {
        Clock.setPaused(true);
        waveManager = new WaveManager(this);
        player = new Player(this);
        this.hudGUI = new HudGUI(this);
        this.complete = false;
    }

    public Player getPlayer() { return player; }
    public WaveManager getWaveManager() { return waveManager; }
    public List<Checkpoint> getCheckpointList() { return checkpointList; }
    public HudGUI getHudGUI() { return hudGUI; }
    public void setComplete(boolean complete)
    {
        this.complete = complete;
        if (complete)
            levelClearedGUI = new LevelClearedGUI(this);
        else
            levelClearedGUI = null;
    }

    public boolean isLastLevel()
    {
        return Game.getInstance().getLevelManager().getLevelIndex() + 1 >= Game.getInstance().getLevelManager().getLevels();
    }

    public void calculateWaypoints()
    {
        // Only let us search for paths, if we haven't already before.
        if (checkpointList.size() == 1)
        {
            System.out.println("[" + name + "] Calculating checkpoints...");
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
        else
            System.out.println("[" + name + "] Not able to calculate checkpoints... " + checkpointList.size());
    }

    public void reset()
    {
        System.out.println("Resetting level!");
        init();
    }

    public TileGrid getTileGrid()
    {
        return tileGrid;
    }

    public void update()
    {
        tileGrid.update();
        if (!complete)
        {
            waveManager.update();
            player.update();
            hudGUI.update();
        }
        else if (levelClearedGUI != null)
            levelClearedGUI.update();
    }

    public void draw()
    {
        tileGrid.draw();
        waveManager.draw();
        player.draw();
        hudGUI.draw();

        if (complete && levelClearedGUI != null)
            levelClearedGUI.draw();
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
                data += tileGrid.getTile(x, y).getTileType().getId() + " ";
            }
            data += '\n';
        }

        return data;
    }

    public boolean load(String levelName)
    {
        File file = new File("levels/" + levelName + ".dat");
        if (!file.exists())
        {
            System.out.println("Could not locate file: " + levelName + ".dat");
            return false;
        }
        else
            System.out.println("Loading level: " + levelName);

        tileGrid = new TileGrid();

        int spawnX = -1;
        int spawnY = -1;
        Direction direction = null;
        TileType pathType = null;

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
                    if (line.equals("Spawn Tile="))
                        continue;;
                    String[] spawnPoint = line.replace("Spawn Tile=", "").split(",");

                    spawnX = Integer.parseInt(spawnPoint[0]);
                    spawnY = Integer.parseInt(spawnPoint[1]);
                    continue;
                }
                else if (line.startsWith("Spawn Direction="))
                {
                    if (line.equals("Spawn Direction="))
                        continue;
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

        checkpointList.clear();
        if (spawnX != -1 && spawnY != -1 && direction != null)
        {
            checkpointList.add(new Checkpoint(this, tileGrid.getTile(spawnX, spawnY), direction));
            System.out.println("Loaded spawn point at: " + spawnX + "," + spawnY + " facing: " + direction.name());
        }

        return true;
    }

    public boolean save()
    {
        File file = new File("levels/" + name + ".dat");
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
