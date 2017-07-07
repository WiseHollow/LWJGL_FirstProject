package net.johnbrooks.fjg.level;

import net.johnbrooks.fjg.level.levels.Level01;
import net.johnbrooks.fjg.level.levels.Level02;

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
        this.levelList.add(new Level01());
        this.levelList.add(new Level02());
        this.currentLevel = levelList.get(0);
        this.levelIndex = 0;
    }

    public Level getCurrentLevel() { return currentLevel; }

    public boolean nextLevel()
    {
        if (levelIndex+1 >= levelList.size())
            return false;
        else
        {
            currentLevel = levelList.get(++levelIndex);
            currentLevel.init();
        }
        return true;
    }

    public int getLevelIndex()
    {
        return levelIndex;
    }

    public int getLevels()
    {
        return levelList.size();
    }
}
