package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.level.levels.Level1Easy;
import net.johnbrooks.fjg.level.levels.Level2Easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ieatl on 7/5/2017.
 */
public class LevelManager
{
    private Level currentLevel;
    private List<Level> levelList;
    private int levelIndex;

    public LevelManager(Level level)
    {
        this.currentLevel = level;
        this.levelList = new ArrayList<>();
        this.levelList.add(level);
        this.levelIndex = 0;
    }

    public LevelManager()
    {
        this.levelList = new ArrayList<>();
        this.levelList.add(new Level1Easy());
        this.levelList.add(new Level2Easy());
        this.currentLevel = levelList.get(0);
        this.levelIndex = 0;
    }

    public Level getCurrentLevel() { return currentLevel; }

    public boolean nextLevel()
    {
        if (levelIndex+1 >= levelList.size())
            return false;
        else
            currentLevel = levelList.get(++levelIndex);
        return true;
    }
}
